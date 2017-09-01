package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.model.Revista;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.RevistaRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.ertic.descontae.interfaces.web.dto.OfertaDTO;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class RevistaService extends RestFullService<Revista, Long> {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    public RevistaService(RevistaRepository repository) {
        super(repository);
    }

    @Override
    public Revista save(Revista e) throws NegocioException {

        long t = e.getInicioVigencia().getTime() - TimeZone.getDefault().getOffset(e.getInicioVigencia().getTime());
        e.setInicioVigencia(new Date(t));

        t = e.getFimVigencia().getTime() - TimeZone.getDefault().getOffset(e.getFimVigencia().getTime());
        e.setFimVigencia(new Date(t));

        return super.save(e);
    }

    public List<OfertaDTO> findOfertasByRevista(Long idRevista) {
        List<Oferta> ofertas = ofertaRepository.findAllByRevista(idRevista);
        List<OfertaDTO> saida = new ArrayList<>();
        for(Oferta oferta : ofertas) {
            OfertaDTO dto = new OfertaDTO();
            dto.setOferta(oferta);
            dto.setUnidades(unidadeRepository.findAllByRevistaEOferta(idRevista, oferta.getId()));
            dto.setMarca(dto.getUnidades().get(0).getParceiro().getMarca());
            saida.add(dto);
        }

        return saida;
    }
}
