package br.com.ertic.descontae.domain.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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
                    idMarca = (Long)r[1];

                    item.setIdMarca(idMarca);
                    item.setMarca((String)r[2]);
                    item.setImagem((String)r[3]);
                    item.setCategoria((String)r[5]);

                    item.setIdUnidade((Long)r[0]);
                    if(r[15] != null) {
                        item.setHoraAbrir(sdf.format((Date)r[15]));
                        item.setHoraFechar(sdf.format((Date)r[16]));
                    }

                }

                if(lat != null && lon != null) {
                    Double distance = GeoUtils.geoDistanceInKm(lat, lon, (Double)r[10], (Double)r[11]);
                    String d = distance.toString();
                    d = d.substring(0, d.indexOf(".") + 3);
                    distance = Double.parseDouble(d);

                    if(item.getDistanciaKM() == null) {
                        item.setDistanciaKM(distance);
                        item.setIdUnidade((Long)r[0]);
                        if(r[15] != null) {
                            item.setHoraAbrir(sdf.format((Date)r[15]));
                            item.setHoraFechar(sdf.format((Date)r[16]));
                        }

                    } else if(distance < item.getDistanciaKM()) {
                        item.setDistanciaKM(distance);
                        item.setIdUnidade((Long)r[0]);
                        if(r[15] != null) {
                            item.setHoraAbrir(sdf.format((Date)r[15]));
                            item.setHoraFechar(sdf.format((Date)r[16]));
                        }
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

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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

                Double curtidas = (Double)unidades[16];
                Double descurtidas = (Double)unidades[17];
                Double total = curtidas + descurtidas;

                String p = new Double(curtidas/total).toString();
                p = p.substring(0, p.indexOf(".") + 3);
                detalhe.setCurtidas(Double.parseDouble(p));

                p = new Double(descurtidas/total).toString();
                p = p.substring(0, p.indexOf(".") + 3);
                detalhe.setDescurtidas(Double.parseDouble(p));

                if(unidades[19] != null) {
                    detalhe.setHoraAbrir(sdf.format((Date)unidades[19]));
                    detalhe.setHoraFechar(sdf.format((Date)unidades[20]));
                }

                detalhe.setImagensProduto(imgRepository.findByIdUnidade(detalhe.getIdUnidade()));

                idCidade = (Long)unidades[18];

                if(lat != null && lon != null) {
                    detalhe.setDistancia(GeoUtils.geoDistanceInKm(lat, lon, detalhe.getLatitude(), detalhe.getLongitude()));
                    String d = detalhe.getDistancia().toString();
                    d = d.substring(0, d.indexOf(".") + 3);
                    detalhe.setDistancia(Double.parseDouble(d));
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
