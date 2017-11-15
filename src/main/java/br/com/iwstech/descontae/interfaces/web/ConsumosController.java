package br.com.iwstech.descontae.interfaces.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.iwstech.descontae.Application;
import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.descontae.domain.model.serializer.DataDeserializer;
import br.com.iwstech.descontae.domain.service.ClienteService;
import br.com.iwstech.descontae.domain.service.ConsumoService;
import br.com.iwstech.descontae.interfaces.web.dto.ConsumoDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardConsumosDTO;
import br.com.iwstech.util.infraestructure.dto.Token;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/consumos")
public class ConsumosController extends RestFullEndpoint<Consumo, Long> {

    @Autowired
    private Token token;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    public ConsumosController(ConsumoService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.PUT, path="/oferta")
    public ResponseEntity<?> addOrUpdateEntity(
        HttpServletRequest request,
        UriComponentsBuilder b,
        @RequestBody ConsumoDTO input) {
        try {

            if(input.getOferta() == null || input.getOferta().getId() == null ) {
                return new ResponseEntity<>("oferta-invalida", HttpStatus.BAD_REQUEST);
            }

            if(input.getUnidade() == null || input.getUnidade().getId() == null || input.getUnidade().getSenhaValidacao() == null) {
                return new ResponseEntity<>("unidade-senha-invalidas", HttpStatus.BAD_REQUEST);
            }

            input.setEmail(token.getUsername());
            Consumo entity = ((ConsumoService)service).add(input);

            String cp = request.getContextPath();
            String location = request.getRequestURI() + "/{id}";
            location = location.substring(location.indexOf(cp) + cp.length());

            UriComponents uric = b.path(location).buildAndExpand(entity.getId());
            return ResponseEntity.created(uric.toUri()).build();

       } catch (NegocioException ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

       } catch (Exception ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

    @RequestMapping(method = RequestMethod.GET, path="/dashboard")
    public ResponseEntity<?> getDashboardTotals(
        HttpServletRequest request,
        @RequestParam(required=false) Long idCliente,
        @RequestParam(required=false) Long idCidade,
        @RequestParam(required=false) String inicio,
        @RequestParam(required=false) String fim) {
        try {

            // RECUPERANDO O IDCLIENTE DO TOKEN
            String accessToken = request.getHeader("Authorization");
            Token token = Application.readToken(accessToken);

            Cliente c = null;
            if(token != null) {
                c = clienteService.findOneByPessoa(token);
                if(c != null) {
                    idCliente = c.getId();
                }
            } else {
                c = clienteService.findOne(idCliente);
            }

            // CIDADE PADRÃO É A CIDADE DO CLIENTE
            if(idCidade == null) {
                if(c != null) {
                    idCidade = c.getEndereco().getCidade().getId();
                } else {
                    idCidade = 1L;
                }
            }

            // DATAS
            GregorianCalendar cal = new GregorianCalendar();
            Date df = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -30);
            Date di = cal.getTime();

            if(inicio != null) {
                di = DataDeserializer.getParser().parse(inicio);
            }

            if(fim != null) {
                df = DataDeserializer.getParser().parse(fim);
            }

            DashboardConsumosDTO out = ((ConsumoService)service).getDashboard(idCliente, idCidade, di, df);
            out.setCliente(c);

            return new ResponseEntity<>(out, HttpStatus.OK);

       } catch (NegocioException ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

       } catch (Exception ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}

