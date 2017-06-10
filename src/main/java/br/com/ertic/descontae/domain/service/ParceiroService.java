package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.GeoUtils;
import br.com.ertic.util.geo.TimeCount;

@Service
public class ParceiroService  {

    @Autowired
    private UnidadeRepository repository;

    @Autowired
    private ImagemUnidadeRepository imagensRepository;

    public HomeDetalheDTO findOne(Long idUnidade) {

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta detalhes parceiro");
        List<Object[]> result = repository.findDetalhesUnidade(idUnidade);
        tc2.end();

        HomeDetalheDTO detalhe = null;
        if(result != null && result.size() == 1) {
            detalhe = new HomeDetalheDTO();

            detalhe.setIdUnidade((Long)result.get(0)[0]);
            detalhe.setMarca((String)result.get(0)[1]);
            detalhe.setLogomarca((String)result.get(0)[2]);
            detalhe.setFundoApp((String)result.get(0)[3]);
            detalhe.setCategoria((String)result.get(0)[4]);
            detalhe.setOferta((String)result.get(0)[5]);
            detalhe.setRegrasOferta((String)result.get(0)[6]);
            detalhe.setValor((Double)result.get(0)[7]);
            detalhe.setDesconto((Double)result.get(0)[8]);
            detalhe.setLatitude((Double)result.get(0)[9]);
            detalhe.setLongitude((Double)result.get(0)[10]);
            detalhe.setEndereco((String)result.get(0)[11]);
            detalhe.setEnderecoResumido((String)result.get(0)[13]);
            detalhe.setImagensProduto(imagensRepository.findByIdUnidade(idUnidade));

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
