package br.com.iwstech.descontae.domain.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Assinatura;
import br.com.iwstech.descontae.domain.model.Cidade;
import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.descontae.domain.model.OfertaUnidade;
import br.com.iwstech.descontae.domain.model.Pessoa;
import br.com.iwstech.descontae.domain.model.SituacaoAtivo;
import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ConsumoCustomRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ConsumoRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.OfertaCustomRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.OfertaUnidadeRepository;
import br.com.iwstech.descontae.interfaces.web.dto.ConsumoDTO;
import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardConsumosDTO;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class ConsumoService extends RestFullService<Consumo, Long> {

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private OfertaUnidadeRepository ofertaUnidadeRepository;

    @Autowired
    private OfertaCustomRepository ofertaCustom;

    @Autowired
    private ConsumoCustomRepository consumoCustom;

    @Autowired
    public ConsumoService(ConsumoRepository repository) {
        super(repository);
    }

    public Consumo add(ConsumoDTO e) throws NegocioException {

        Unidade u = unidadeService.findOne(e.getUnidade().getId());
        if(u == null) {
            throw new NegocioException("unidade-nao-encontrada");
        } else if(!u.getSenhaValidacao().equals(e.getUnidade().getSenhaValidacao())) {
            throw new NegocioException("senha-unidade-nao-confere");
        }

        OfertaUnidade o = ofertaUnidadeRepository.findByOfertaUnidade(e.getOferta().getId(), u.getId());
        if(o == null) {
            throw new NegocioException("oferta-nao-encontrada-para-unidade");
        } else if(o.getOferta().getSituacao() == SituacaoAtivo.I) {
            throw new NegocioException("oferta-invalida");
        }

        Pessoa p = pessoaService.findByEmail(e.getEmail());
        if(p == null) {
            throw new NegocioException("usuario-nao-encontrado");
        }

        Assinatura a = pessoaService.findAssinaturaVigente(e.getEmail());
        Date agora = new Date(System.currentTimeMillis());
        if(a == null) {
            throw new NegocioException("assinatura-nao-encontrada");
        } else {
            if( !(agora.getTime() >= a.getInicioVigencia().getTime() && agora.getTime() <= a.getFimVigencia().getTime()) ) {
                throw new NegocioException("assinatura-fora-prazo");
            }
        }

        Consumo uc = ofertaCustom.findUltimoConsumo(o.getOferta().getId(), p.getId());
        Date limite = new Date(agora.getTime() - 1000*60*60*24);
        if(uc != null && uc.getData().after(limite)) {            throw new NegocioException("oferta-utilizada-24h");
        }

        Consumo c = new Consumo();
        c.setAssinatura(a);
        c.setOferta(o.getOferta());
        c.setUnidade(o.getUnidade());
        c.setData(agora);
        c = repository.save(c);
        return c;

    }

    public List<ConsumoUsuarioDTO> findConsumosUsuario(String email) throws NegocioException {
        Pessoa p = pessoaService.findByEmail(email);
        return consumoCustom.findByUsuario(p.getId());
    }

    public DashboardConsumosDTO getDashboard(String emailUsuario, Long idCliente, Long idCidade, Date dataInicio, Date dataFim) throws NegocioException {

        try {

            DashboardConsumosDTO dto = new DashboardConsumosDTO();

            // PESQUISANDO O CLIENTE PELO ID INFORMADO
            if(idCliente != null) {
                dto.setCliente(clienteService.findOne(idCliente));

            // ENCONTRANDO O CLIENTE ATRAVÉS DO EMAIL
            } else if(emailUsuario != null) {
                dto.setCliente(clienteService.findOneByPessoa(emailUsuario));
            }

            // SE NÃO INFORMAR A CIDADE
            if(idCidade == null) {

                // A CIDADE PADRÃO É A DO ENDEREÇO DO CLIENTE
                if(dto.getCliente() != null) {
                    idCidade = dto.getCliente().getEndereco().getCidade().getId();

                // SEM O CLIENTE, CIDADE PADRÃO É BRASILIA
                } else {
                    idCidade = Cidade.BRASILIA.getId();
                }

            }
            dto.setCidade(cidadeService.findOne(idCidade));

            // O PERIODO PADRÃO SÃO OS ÚLTIMOS 30 DIAS
            if(dataInicio == null || dataFim == null) {
                GregorianCalendar cal = new GregorianCalendar();
                dataFim = cal.getTime();
                cal.add(Calendar.DAY_OF_MONTH, -30);
                dataInicio = cal.getTime();
            }
            dto.setDataInicio(dataInicio);
            dto.setDataFim(dataFim);


            dto.setCartoesTotais(consumoCustom.findCartoesTotais(idCliente));
            dto.setConsumosTotais(consumoCustom.findConsumosTotais(idCliente, dataInicio, dataFim));

            DecimalFormat df = new DecimalFormat("##.##%");
            double[] total = new double[] {0D};

            dto.setCartoesAtivos(consumoCustom.findCartoesAtivos(idCliente).stream()
                .map(item -> {
                    total[0] = total[0] + item.getTotal();
                    return item;
                }).collect(Collectors.toList()).stream()
                .map(item -> {
                    double p = item.getTotal() / total[0];
                    item.setPercentual(df.format(p));
                    return item;
                }).collect(Collectors.toList()));

            total[0] = 0D;
            dto.setConsumosTotaisByCategoria(consumoCustom.findConsumosTotaisByCategoria(idCliente, idCidade, dataInicio, dataFim).stream()
                .map(item -> {
                    total[0] = total[0] + item.getTotal();
                    return item;
                }).collect(Collectors.toList()).stream()
                .map(item -> {
                    double p = item.getTotal() / total[0];
                    item.setPercentual(df.format(p));
                    return item;
                }).collect(Collectors.toList()));

            total[0] = 0D;
            dto.setConsumosTotaisByCidade(consumoCustom.findTotaisByCidade(idCliente, dataInicio, dataFim).stream()
                .map(item -> {
                    total[0] = total[0] + item.getTotal();
                    return item;
                }).collect(Collectors.toList()).stream()
                .map(item -> {
                    double p = item.getTotal() / total[0];
                    item.setPercentual(df.format(p));
                    return item;
                }).collect(Collectors.toList()));

            total[0] = 0D;
            dto.setConsumosTotaisByBairro(consumoCustom.findTotaisByBairro(idCliente, idCidade, dataInicio, dataFim).stream()
                .map(item -> {
                    total[0] = total[0] + item.getTotal();
                    return item;
                }).collect(Collectors.toList()).stream()
                .map(item -> {
                    double p = item.getTotal() / total[0];
                    item.setPercentual(df.format(p));
                    return item;
                }).collect(Collectors.toList()));

            return dto;

        } catch(Exception ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

}
