package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.MarcaFranquiaRepository;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeUnidadeDTO;
import br.com.ertic.util.geo.GeoUtils;
import br.com.ertic.util.geo.TimeCount;

@Service
public class MarcaFranquiaService  {

    @Autowired
    private MarcaFranquiaRepository repository;

    @Autowired
    private ImagemUnidadeRepository imgRepository;

    public List<HomeParceiroDTO> findFranquiasByCidade(Long idCidade, Double lat, Double lon) {

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta franquias/cidade");
        List<Object[]> result = repository.findAllByCidade(idCidade);
        tc2.end();

        List<HomeParceiroDTO> franquias = new ArrayList<>();

        if(result != null) {
            HomeParceiroDTO item = null;
            Long idMarca = null;
            Double distance = null;

            for(Object[] r : result) {

                if(!r[1].equals(idMarca)) {
                    item = new HomeParceiroDTO();
                    franquias.add(item);
                    item.setIdMarca((Long)r[1]);
                    item.setMarca((String)r[2]);
                    item.setImagem((String)r[3]);
                    idMarca = item.getIdMarca();
                    distance = null;
                }

                if(lat != null && lon != null) {
                    double tempDistance = GeoUtils.geoDistanceInKm(lat, lon, (Double)r[10], (Double)r[11]);
                    if(distance == null) {
                        distance = tempDistance;
                    } else {
                        distance = tempDistance < distance ? tempDistance : distance;
                    }
                }

                if(distance != null) {
                    item.setDistanciaKM(distance);
                }
            }

        }

        Collections.sort(franquias, new Comparator<HomeParceiroDTO>() {
            @Override
            public int compare(HomeParceiroDTO o1, HomeParceiroDTO o2) {
                return o1.getMarca() == null ? 0 : o1.getMarca().compareTo(o2.getMarca());
            }
        });

        return franquias;
    }

    public HomeDetalheDTO detalharFranquia(Long idFranquia, Long idCidade, Double lat, Double lon) {

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta detalhes franquia/cidade");
        List<Object[]> result = repository.findDetalhes(idFranquia, idCidade);
        tc2.end();

        HomeDetalheDTO detalhe = null;
        if(result != null && result.size() > 0) {
            for(Object[] unidades : result) {

                if(detalhe == null) {
                    detalhe = new HomeDetalheDTO();
                    detalhe.setIdMarca((Long)unidades[1]);
                    detalhe.setMarca((String)unidades[2]);
                    detalhe.setLogomarca((String)unidades[3]);
                    detalhe.setFundoApp((String)unidades[4]);
                    detalhe.setCategoria((String)unidades[5]);
                    detalhe.setUnidades(new ArrayList<HomeUnidadeDTO>());
                }

                HomeUnidadeDTO u = new HomeUnidadeDTO();
                detalhe.getUnidades().add(u);

                u.setIdUnidade((Long)unidades[0]);
                u.setOferta((String)unidades[6]);
                u.setRegrasOferta((String)unidades[7]);
                u.setValor((Double)unidades[8]);
                u.setDesconto((Double)unidades[9]);
                u.setLatitude((Double)unidades[10]);
                u.setLongitude((Double)unidades[11]);
                u.setEndereco((String)unidades[12]);
                u.setEnderecoResumido((String)unidades[14]);
                u.setSobre((String)unidades[15]);
                u.setNotaSatisfacao((Long)unidades[16]);
                u.setNotaPreco((Long)unidades[17]);
                u.setImagensProduto(imgRepository.findByIdUnidade(u.getIdUnidade()));

                if(lat != null && lon != null) {
                    u.setDistancia(GeoUtils.geoDistanceInKm(lat, lon, u.getLatitude(), u.getLongitude()));
                }

            }

        }

        if(detalhe != null && detalhe.getUnidades() != null) {
            Collections.sort(detalhe.getUnidades(), new Comparator<HomeUnidadeDTO>() {
                @Override
                public int compare(HomeUnidadeDTO o1, HomeUnidadeDTO o2) {
                    return o1.getDistancia() == null ? 0 : o1.getDistancia().compareTo(o2.getDistancia());
                }
            });
        }
        return detalhe;

    }


}
