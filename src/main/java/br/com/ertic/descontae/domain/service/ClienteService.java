package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Cliente;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ClienteRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class ClienteService extends RestFullService<Cliente, Long> {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente save(Cliente e) {

        if(e.getId() == null) {
            e.setPessoa(pessoaService.findByEmail(e.getPessoa().getEmail()));
            e.setDataCadastro(new Date(System.currentTimeMillis()));
            e.setDataAlteracao(new Date(System.currentTimeMillis()));
        }

        return super.save(e);
    }
}
