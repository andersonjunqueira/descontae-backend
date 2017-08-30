package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.infraestructure.persistence.jpa.PessoaRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class PessoaService extends RestFullService<Pessoa, Long> {

    @Autowired
    private EnderecoService enderecoService;

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
    public Pessoa save(Pessoa e) {

        if(e.getId() == null) {
            e.setDataCadastro(new Date(System.currentTimeMillis()));
            e.setIdioma(e.getIdioma() != null ? e.getIdioma() : "pt-BR");
        }

        if(e.getEndereco() != null && e.getEndereco().getCep() != null)  {
            e.setEndereco(enderecoService.save(e.getEndereco()));
        } else {
            e.setEndereco(null);
        }

        e.setDataAlteracao(e.getDataCadastro());

        //TODO CRIAR O USUÁRIO NO KEYCLOAK

        return super.save(e);

    }

    public boolean verificar(String cpf, String email) {
        Long total = ((PessoaRepository)repository).checkDuplicate(cpf != null ? cpf : email);
        return total > 0;
    }

}
