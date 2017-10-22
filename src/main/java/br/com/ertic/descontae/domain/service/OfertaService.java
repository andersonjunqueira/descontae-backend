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
import br.com.ertic.descontae.domain.model.SituacaoAtivo;
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

        SituacaoAtivo situacao = null;
        if(params.get("situacao") != null) {
            if(params.get("situacao")[0].equals("A")) {
                situacao = SituacaoAtivo.A;
            } else if(params.get("situacao")[0].equals("I")) {
                situacao = SituacaoAtivo.I;
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

        if(dto.getId() != null) {
            o = repository.findOne(dto.getId());
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

        List<OfertaUnidade> ous = ofertaUnidadeRepository.findAllByOferta(dto.getId());
        List<OfertaUnidade> toDel = new ArrayList<>();

        // APAGANDO AQUELAS QUE EXISTEM NO BANCO, MAS NÃO FORAM ENVIADAS PELO FRONTEND
        for(OfertaUnidade ou : ous) {
            boolean encontrado = false;
            for(OfertaUnidadeDTO udto : dto.getUnidades()) {
                if(ou.getUnidade().getId().equals(udto.getId())) {
                    encontrado = true;
                    break;
                }
            }
            // SE NÃO ENCONTRAR NO FRONT = APAGAR
            if(!encontrado) {
                toDel.add(ou);
            }
        }

        for(OfertaUnidadeDTO udto : dto.getUnidades()) {

            OfertaUnidade item = null;
            for(OfertaUnidade ou : ous) {
                if(ou.getUnidade().getId().equals(udto.getId())) {
                    item = ou;
                    break;
                }
            }

            // OFERTAUNIDADE ENCONTRADA, SE NÃO ESTÁ MARCADA = APAGAR
            if(item != null && !udto.isSelecionada()) {
                toDel.add(item);

            // OFERTA UNIDADE NÃO ENCONTRADA, SE ESTÁ SELECIONADA = INCLUIR
            } else if(item == null && udto.isSelecionada()) {
                OfertaUnidade ou = new OfertaUnidade();
                ou.setOferta(o);
                ou.setUnidade(new Unidade());
                ou.getUnidade().setId(udto.getId());
                ofertaUnidadeRepository.save(ou);
            }

        }

        if(toDel != null && !ous.isEmpty()) {
            ofertaUnidadeRepository.delete(toDel);
        }

        return o;

    }

    @Transactional
    @Override
    public void delete(Long id) throws NegocioException {
        List<OfertaUnidade>ous = ofertaUnidadeRepository.findAllByOferta(id);
        if(ous != null && !ous.isEmpty()) {
            ofertaUnidadeRepository.delete(ous);
        }
        super.delete(id);
    }
}
