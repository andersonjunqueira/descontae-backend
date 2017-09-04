package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.model.OfertaUnidade;
import br.com.ertic.descontae.domain.model.SituacaoOferta;
import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaCustomRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaUnidadeRepository;
import br.com.ertic.descontae.interfaces.web.dto.OfertaDTO;
import br.com.ertic.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class OfertaService extends RestFullService<Oferta, Long> {

    @Autowired
    private Token token;

    @Autowired
    private OfertaCustomRepository customRepository;

    @Autowired
    private OfertaUnidadeRepository ofertaUnidadeRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    public OfertaService(OfertaRepository repository) {
        super(repository);
    }

    public Page<Object[]> findListaSimples(Map<String, String[]> params) throws NegocioException {

        SituacaoOferta situacao = null;
        if(params.get("situacao") != null) {
            if(params.get("situacao")[0].equals("A")) {
                situacao = SituacaoOferta.A;
            } else if(params.get("situacao")[0].equals("I")) {
                situacao = SituacaoOferta.I;
            }
        }

        return customRepository.findListaSimples(
            params.get("texto") != null ? params.get("texto")[0] : null,
            situacao, getPageRequest(params));
    }

    public OfertaDTO getForEdit(Long id) throws NegocioException {

        OfertaDTO dto = new OfertaDTO();
        Oferta o = repository.findOne(id);

        dto.setId(o.getId());
        dto.setDescricao(o.getDescricao());
        dto.setImagem(o.getImagem());
        dto.setValor(o.getValor());
        dto.setRegras(o.getRegras());
        dto.setPessoa(o.getPessoa());
        dto.setDesconto(o.getDesconto());
        dto.setSituacao(o.getSituacao());

        List<Unidade> unidadesOferta = unidadeService.findAllByOferta(id);

        if(unidadesOferta != null && !unidadesOferta.isEmpty()) {

            dto.setMarcaSelecionada(unidadesOferta.get(0).getParceiro().getMarca().getId());
            List<Unidade> unidades = unidadeService.findAllByMarca(dto.getMarcaSelecionada());

            dto.setUnidades(new ArrayList<>());
            for(Unidade u : unidades) {

                OfertaUnidadeDTO oudto = new OfertaUnidadeDTO();
                dto.getUnidades().add(oudto);

                oudto.setId(u.getId());
                oudto.setNome(u.getNome());
                oudto.setEndereco(
                    u.getEndereco().getLogradouro() + " " +
                    u.getEndereco().getComplemento() + " " +
                    u.getEndereco().getNumero() + " " +
                    u.getEndereco().getBairro()
                );

                oudto.setSelecionada(unidadesOferta.contains(u));

            }
        }

        return dto;

    }

    @Transactional
    public Oferta saveDTO(OfertaDTO dto) throws NegocioException {

        Oferta o = null;
        List<OfertaUnidade> ous = null;

        if(dto.getId() != null) {
            o = repository.findOne(dto.getId());
            ous = ofertaUnidadeRepository.findAllByOferta(dto.getId());
        } else {
            o = new Oferta();
            o.setPessoa(pessoaService.findByEmail(token.getUsername()));
        }

        o.setDescricao(dto.getDescricao());
        o.setImagem(dto.getImagem());
        o.setValor(dto.getValor());
        o.setRegras(dto.getRegras());
        o.setDesconto(dto.getDesconto());
        o.setSituacao(dto.getSituacao());
        o = repository.save(o);

        if(ous != null && !ous.isEmpty()) {
            ofertaUnidadeRepository.delete(ous);
        }

        for(OfertaUnidadeDTO udto : dto.getUnidades()) {
            if(udto.isSelecionada()) {
                OfertaUnidade ou = new OfertaUnidade();
                ou.setOferta(o);
                ou.setUnidade(new Unidade());
                ou.getUnidade().setId(udto.getId());
                ofertaUnidadeRepository.save(ou);
            }
        }

        return o;

    }

}
