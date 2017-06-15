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

            for(Object[] r : result) {

                boolean novaFranquia = !r[1].equals(idMarca);
                if(novaFranquia) {
                    item = new HomeParceiroDTO();
                    franquias.add(item);
                    item.setIdMarca((Long)r[1]);
                    item.setMarca((String)r[2]);
                    item.setImagem((String)r[3]);
                    item.setIdUnidade((Long)r[0]);
                    idMarca = item.getIdMarca();
                }

                if(lat != null && lon != null) {
                    double distance = GeoUtils.geoDistanceInKm(lat, lon, (Double)r[10], (Double)r[11]);
                    if(item.getDistanciaKM() == null) {
                        item.setDistanciaKM(distance);
                        item.setIdUnidade((Long)r[0]);
                    } else if(distance < item.getDistanciaKM()) {
                        item.setDistanciaKM(distance);
                        item.setIdUnidade((Long)r[0]);
                    }
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

    public HomeDetalheDTO detalharUnidade(Long idUnidade, Double lat, Double lon) {

        TimeCount tc =  TimeCount.start(this.getClass(), "Consulta detalhes franquia/cidade");
        List<Object[]> result = repository.findDetalhes(idUnidade);
        tc.end();

        HomeDetalheDTO detalhe = null;
        Long idCidade = null;

        if(result != null && result.size() > 0) {
            for(Object[] unidades : result) {

                detalhe = new HomeDetalheDTO();
                detalhe.setIdUnidade((Long)unidades[0]);
                detalhe.setIdMarca((Long)unidades[1]);
                detalhe.setMarca((String)unidades[2]);
                detalhe.setLogomarca((String)unidades[3]);
                detalhe.setFundoApp((String)unidades[4]);
                detalhe.setCategoria((String)unidades[5]);
                detalhe.setOferta((String)unidades[6]);
                detalhe.setRegrasOferta((String)unidades[7]);
                detalhe.setValor((Double)unidades[8]);
                detalhe.setDesconto((Double)unidades[9]);
                detalhe.setLatitude((Double)unidades[10]);
                detalhe.setLongitude((Double)unidades[11]);
                detalhe.setEndereco((String)unidades[12]);
                detalhe.setEnderecoResumido((String)unidades[14]);
                detalhe.setSobre((String)unidades[15]);
                detalhe.setNotaSatisfacao((Long)unidades[16]);
                detalhe.setNotaPreco((Long)unidades[17]);
                detalhe.setImagensProduto(imgRepository.findByIdUnidade(detalhe.getIdUnidade()));

                idCidade = (Long)unidades[18];

                if(lat != null && lon != null) {
                    detalhe.setDistancia(GeoUtils.geoDistanceInKm(lat, lon, detalhe.getLatitude(), detalhe.getLongitude()));
                }
            }
        }

        tc =  TimeCount.start(this.getClass(), "Consulta outras unidades");
        result = repository.findOutrasUnidades(detalhe.getIdMarca(), idCidade);
        tc.end();

        detalhe.setUnidades(new ArrayList<>());
        List<HomeUnidadeDTO> temp = new ArrayList<>();
        if(result != null && result.size() > 0) {
            for(Object[] uns : result) {

                HomeUnidadeDTO u = new HomeUnidadeDTO();
                u.setIdUnidade((Long)uns[0]);
                u.setEnderecoResumido((String)uns[1]);

                if(uns[0].equals(detalhe.getIdUnidade())) {
                    detalhe.getUnidades().add(u);
                } else {
                    temp.add(u);
                }
            }
            detalhe.getUnidades().addAll(temp);
        }

        return detalhe;

    }

}
