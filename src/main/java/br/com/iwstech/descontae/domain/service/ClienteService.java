package br.com.iwstech.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.descontae.domain.model.Telefone;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ClienteRepository;
import br.com.iwstech.util.infraestructure.dto.Token;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class ClienteService extends RestFullService<Cliente, Long> {

    @Autowired
    private Token token;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Cliente save(Cliente e) throws NegocioException {

        if(e.getId() == null) {
            e.setPessoa(pessoaService.findByEmail(token.getUsername()));
            e.setDataCadastro(new Date(System.currentTimeMillis()));
        } else {
            e.setPessoa(pessoaService.findOne(e.getPessoa().getId()));
        }

        if(e.getEndereco() != null && e.getEndereco().getCep() != null)  {
            e.setEndereco(enderecoService.addOrUpdate(e.getEndereco()));
        } else {
            e.setEndereco(null);
        }

        for(Telefone t : e.getTelefones()) {
            t.setCliente(e);
        }

        e.setDataAlteracao(new Date(System.currentTimeMillis()));
        return repository.save(e);
    }
}
