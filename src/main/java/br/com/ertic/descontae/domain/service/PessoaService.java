package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.infraestructure.persistence.jpa.PessoaRepository;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.KeycloakService;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class PessoaService extends RestFullService<Pessoa, Long> {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    PessoaService(PessoaRepository repository) {
        super(repository);
    }

    public Pessoa findByEmail(String email) {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        return getRepository().findOne(Example.of(pessoa));
    }

    @Override
    protected Pessoa save(Pessoa e) throws NegocioException {

        if(e.getId() == null) {
            e.setDataCadastro(new Date(System.currentTimeMillis()));
            e.setIdioma(e.getIdioma() != null ? e.getIdioma() : "pt-BR");
        }

        if(e.getEndereco() != null && e.getEndereco().getCep() != null)  {
            e.setEndereco(enderecoService.addOrUpdate(e.getEndereco()));
        } else {
            e.setEndereco(null);
        }

        e.setDataAlteracao(e.getDataCadastro());

        keycloakService.createUser(e.getNome(), null, e.getEmail(), e.getSenha());

        return repository.save(e);

    }

    public boolean verificar(String cpf, String email) {
        Long total = 0L;
        if(cpf != null) {
            total += ((PessoaRepository)repository).verificarCPFDuplicado(cpf);
        }

        if(email != null) {
            total += ((PessoaRepository)repository).verificarEmailDuplicado(email);
        }

        return total > 0;
    }

}
