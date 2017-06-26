package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.domain.model.TipoPessoa;
import br.com.ertic.descontae.infraestructure.persistence.jpa.PessoaRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class PessoaService extends RestFullService<Pessoa, Long> {

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

        if(e.getTipoPessoa() == null) {
            e.setTipoPessoa(TipoPessoa.TIPO_USUARIO);
            e.setDataCadastro(new Date(System.currentTimeMillis()));
            e.setDataAlteracao(e.getDataCadastro());
        }

        return super.save(e);
    }

}
