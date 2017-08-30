package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.domain.service.PessoaService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pessoas")
public class PessoasController extends RestFullEndpoint<Pessoa, Long> {

    @Autowired
    public PessoasController(PessoaService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/login")
    public ResponseEntity<Pessoa> getByEmail(
        @RequestParam(name="email", required=true) String email) {

        Pessoa p = ((PessoaService)service).findByEmail(email);

        if(p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path="/verificar")
    public ResponseEntity<Pessoa> verificar(
        @RequestParam(name="email", required=false) String email,
        @RequestParam(name="cpf", required=false) String cpf) {

        if(cpf == null && email == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean encontrado = ((PessoaService)service).verificar(cpf, email);

        if(!encontrado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
