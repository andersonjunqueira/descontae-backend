package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Cliente;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ClienteRepository;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

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

        if(e.getEndereco().getLogradouro() == null || e.getEndereco().getLogradouro().isEmpty())  {
            if(e.getEndereco().getId() != null) {
                enderecoService.delete(e.getEndereco().getId());
            }
            e.setEndereco(null);
        } else {
            e.setEndereco(enderecoService.save(e.getEndereco()));
        }

        e.setDataAlteracao(new Date(System.currentTimeMillis()));
        return super.save(e);
    }
}
