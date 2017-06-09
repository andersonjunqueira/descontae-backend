package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.GeoUtils;
import br.com.ertic.util.geo.TimeCount;

@Service
public class ParceiroService  {

    @Autowired
    private UnidadeRepository repository;

    public HomeDetalheDTO findOne(Long idUnidade) {

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta detalhes parceiro");
        Object[] result = repository.findDetalhesUnidade(idUnidade);
        tc2.end();

        HomeDetalheDTO detalhe = null;
        if(result != null) {
            detalhe = new HomeDetalheDTO();

        }

        return detalhe;

    }

    public List<HomeParceiroDTO> findByCidade(Long idCidade, Double lat, Double lon) {

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta parceiros / cidade");
        List<Object[]> result = repository.findUnidadesByIdCidade(idCidade);
        tc2.end();

        List<HomeParceiroDTO> parceiros = new ArrayList<>();
        for(Object[] r : result) {

            HomeParceiroDTO p = new HomeParceiroDTO();
            p.setIdUnidade((Long)r[0]);
            p.setMarca((String)r[1]);
            p.setImagem((String)r[2]);
            p.setCategoria((String)r[3]);
            p.setHoraAbrir((Date)r[6]);
            p.setHoraFechar((Date)r[7]);
            p.setNota((Long)r[8]);

            if(lat != null && lon != null) {
                p.setDistanciaKM(GeoUtils.geoDistanceInKm(
                    lat, lon, (Double)r[4], (Double)r[5]));
            }

            parceiros.add(p);

        }

        Collections.sort(parceiros, new Comparator<HomeParceiroDTO>() {
            @Override
            public int compare(HomeParceiroDTO o1, HomeParceiroDTO o2) {
                return o1.getDistanciaKM() == null ? 0 : o1.getDistanciaKM().compareTo(o2.getDistanciaKM());
            }
        });

        return parceiros;
    }

}
