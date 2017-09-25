package br.com.ertic.descontae.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Assinatura;
import br.com.ertic.descontae.domain.model.Cartao;
import br.com.ertic.descontae.domain.model.SituacaoAtivo;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CartaoCustomRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CartaoRepository;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class CartaoService extends RestFullService<Cartao, Long> {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PlanoService planoService;

    @Autowired
    private CartaoCustomRepository customRepository;

    @Autowired
    CartaoService(CartaoRepository repository) {
        super(repository);
    }

    public Page<Object[]> findListaSimples(Map<String, String[]> params) throws NegocioException {
        //TODO IMPLEMENTAR PESQUISA POR NOME DO USUARIO DO CLIENTE OU DO CODIGO DO CARTAO
        String filtro = null;
        if(params.get("filtro") != null) {
            filtro = params.get("filtro")[0];
        }
        return customRepository.findListaSimples(filtro, getPageRequest(params));
    }

    @Override
    protected Cartao save(Cartao e) throws NegocioException {

        long codigoInicial = e.getCodigo();
        long codigoFinal = e.getCodigoFinal() != null ? e.getCodigoFinal() : e.getCodigo();
        boolean faixa = codigoFinal != codigoInicial;

        // VERIFICANDO SE JÁ EXISTE
        if(!faixa) {

            // CARTÃO UNICO
            // APENAS PARA NOVOS CARTÕES
            if(e.getId() == null) {
                Long c = ((CartaoRepository)repository).findByCodigo(e.getCodigo());
                if(c != null) {
                    throw new NegocioException("cartao-ja-registrado");
                }
            }

        } else {

            // FAIXA
            for(long i = codigoInicial; i <= codigoFinal; i++) {
                e.setCodigo(i);
                Long c = ((CartaoRepository)repository).findByCodigo(e.getCodigo());
                if(c != null) {
                    throw new NegocioException("cartao-ja-registrado");
                }
            }

        }

        Cartao nc = null;
        for(long i = codigoInicial; i <= codigoFinal; i++) {

            // SE INFORMADO UM ID CARREGA PARA ATUALIZAÇÃO
            if(e.getId() == null) {
                nc = new Cartao();
            } else {
                nc = repository.findOne(e.getId());
            }

            nc.setCodigo(i);
            nc.setAtivo(faixa ? SituacaoAtivo.I : e.getAtivo());
            nc.setPessoa(e.getPessoa());

            if(e.getAtivo() == SituacaoAtivo.A) {
                //TODO INATIVAR OS DEMAIS CARTÕES
            }

            // CARTAO NÃO POSSUI INFORMAÇÃO DE ASSINATURA
            if(e.getAssinatura() == null) {

                // APENAS SALVAR
                nc = repository.save(nc);

            // SE O CARTAO POSSUIR INFORMAÇÃO DE ASSINATURA
            } else {

                // SE O CARTAO NÃO POSSUI DATAS
                if(e.getAssinatura().getInicioVigencia() == null || e.getAssinatura().getFimVigencia() == null)  {
                    throw new NegocioException("vigencia-obrigatorias");
                }

                // SE O CARTAO NÃO POSSUI PLANO OU O PLANO É INVALIDO
                if(e.getAssinatura().getPlano() != null) {
                    e.getAssinatura().setPlano(planoService.findOne(e.getAssinatura().getPlano().getId()));
                }

                // SE O CARTAO NÃO POSSUI PLANO
                if(e.getAssinatura().getPlano() == null)  {
                    throw new NegocioException("plano-obrigatorio");
                }

                // CARGA DOS DADOS A ASSINATURA
                nc.setAssinatura(new Assinatura());
                nc.getAssinatura().setCliente(e.getAssinatura().getCliente());
                nc.getAssinatura().setPessoa(e.getAssinatura().getPessoa());
                nc.getAssinatura().setPlano(e.getAssinatura().getPlano());
                nc.getAssinatura().setInicioVigencia(e.getAssinatura().getInicioVigencia());
                nc.getAssinatura().setFimVigencia(e.getAssinatura().getFimVigencia());

                nc = repository.save(nc);

            }

        }

        return nc;

    }

    //TODO MÉTODO TEMPORARIO DE REGISTRO DE CARTÃO, NO FUTURO OS CARTÕES SERÃO PRECADASTRADOS E O USUARIO VIRA DO TOKEN
    @Transactional
    public Cartao associarCartao(Cartao cartao) throws NegocioException {

        Long id = ((CartaoRepository)repository).findByCodigo(cartao.getCodigo());

        if(id == null) {

            Cartao c = new Cartao();
            c.setCodigo(cartao.getCodigo());
//            c.setPessoa(pessoaService.findByEmail(cartao.getPessoa().getEmail()));
            return super.save(c);

        } else {
            throw new NegocioException("cartao-ja-registrado");
        }
    }

    public Cartao findAtivoPorUsuario(String email) throws NegocioException {
        return ((CartaoRepository)repository).findAtivoByUsuario(email);
    }
}
