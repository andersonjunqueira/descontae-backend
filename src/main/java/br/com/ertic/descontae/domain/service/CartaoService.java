package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Cartao;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CartaoRepository;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class CartaoService extends RestFullService<Cartao, Long> {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    CartaoService(CartaoRepository repository) {
        super(repository);
    }

    //TODO MÉTODO TEMPORARIO DE REGISTRO DE CARTÃO, NO FUTURO OS CARTÕES SERÃO PRECADASTRADOS E O USUARIO VIRA DO TOKEN
    @Transactional
    public Cartao associarCartao(Cartao cartao) throws NegocioException {

        Cartao existe = ((CartaoRepository)repository).findByCodigo(cartao.getCodigo());

        if(existe == null) {

            Cartao c = new Cartao();
            c.setCodigo(cartao.getCodigo());
            c.setPessoa(pessoaService.findByEmail(cartao.getPessoa().getEmail()));
            return super.save(c);

        } else {
            throw new NegocioException("cartao-ja-registrado");
        }
    }

    public Cartao findAtivoPorUsuario(String email) throws NegocioException {
        return ((CartaoRepository)repository).findAtivoByUsuario(email);
    }
}
