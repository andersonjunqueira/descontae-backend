package br.com.iwstech.descontae.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Assinatura;
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
        if(uc != null && uc.getData().after(limite)) {
            throw new NegocioException("oferta-utilizada-24h");
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

    public DashboardConsumosDTO getDashboard(Long idCliente, Long idCidade, Date dataInicio, Date dataFim) throws NegocioException {

        try {
            DashboardConsumosDTO dto = new DashboardConsumosDTO();

            dto.setCartoesAtivos(consumoCustom.findCartoesAtivos(idCliente));
            dto.setTotais(consumoCustom.findTotais(idCliente, dataInicio, dataFim));

            dto.setTotaisByCategoria(consumoCustom.findTotaisByCategoria(idCliente, idCidade, dataInicio, dataFim));
            dto.setTotaisByBairro(consumoCustom.findTotaisByBairro(idCliente, idCidade, dataInicio, dataFim));
            dto.setTotaisByCidade(consumoCustom.findTotaisByCidade(idCliente, dataInicio, dataFim));
            return dto;

        } catch(Exception ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

}
