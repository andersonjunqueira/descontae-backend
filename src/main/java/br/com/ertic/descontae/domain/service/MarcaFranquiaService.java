package br.com.ertic.descontae.domain.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.MarcaFranquia;
import br.com.ertic.descontae.domain.model.serializer.TimeDeserializer;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.MarcaFranquiaRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.MarcaFranquiasCustomRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaRepository;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeUnidadeDTO;
import br.com.ertic.util.geo.GeoUtils;
import br.com.ertic.util.geo.TimeCount;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class MarcaFranquiaService  extends RestFullService<MarcaFranquia, Long> {

    @Autowired
    private MarcaFranquiasCustomRepository franquiasCustomRepo;

    @Autowired
    public MarcaFranquiaService(MarcaFranquiaRepository repository) {
        super(repository);
    }

    @Autowired
    private ImagemUnidadeRepository imgRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    public List<HomeParceiroDTO> findFranquiasByCidade(Long idCidade, String filtro, Double lat, Double lon, String[] categorias) {

        DateFormat sdf = TimeDeserializer.getParser();
        NumberFormat nf = NumberFormat.getPercentInstance();

        TimeCount tc2 =  TimeCount.start(this.getClass(), "Consulta franquias/cidade");

        List<Long> idCats = null;
        if(categorias != null) {
            idCats = new ArrayList<>();
            for(int i = 0; i < categorias.length; i++) {
                idCats.add(new Long(categorias[i]));
            }
        }

        List<Object[]> result = null;
        if(filtro != null) {
            String f = filtro.toLowerCase() + "%";
            result = franquiasCustomRepo.findAllByCidade(idCidade, f, idCats);
        } else {
            result = franquiasCustomRepo.findAllByCidade(idCidade, idCats);
        }

        tc2.end();

        List<HomeParceiroDTO> franquias = new ArrayList<>();
        Double maiorDesconto = null;

        if(result != null) {
            HomeParceiroDTO item = null;
            Long idMarca = null;

            for(Object[] r : result) {

                boolean novaFranquia = !r[1].equals(idMarca);
                if(novaFranquia) {
                    maiorDesconto = null;

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

                if(r[17] != null) {
                    Double desconto = (Double)r[17];
                    if(maiorDesconto == null) {
                        maiorDesconto = desconto;
                    } else {
                        maiorDesconto = desconto > maiorDesconto ? desconto : maiorDesconto;
                    }
                }

                item.setDesconto(maiorDesconto == null || maiorDesconto == 0D ? null : nf.format(maiorDesconto));

                if(lat != null && lon != null && r[10] != null) {
                    Double distance = GeoUtils.geoDistanceInKm(lat, lon, (Double)r[10], (Double)r[11]);
                    String d = distance.toString();
                    d = d.substring(0, d.indexOf(".") + 3);
                    distance = Double.parseDouble(d);

                    if(item.getDistanciaKM() == null) {
                        item.setDistanciaKM(distance);
                        item.setDistanciaStr(distance.toString().substring(0, distance.toString().indexOf(".")+2) + " km");

                        item.setIdUnidade((Long)r[0]);
                        if(r[15] != null) {
                            item.setHoraAbrir(sdf.format((Date)r[15]));
                            item.setHoraFechar(sdf.format((Date)r[16]));
                        }

                    } else if(distance < item.getDistanciaKM()) {
                        item.setDistanciaKM(distance);
                        item.setDistanciaStr(distance.toString().substring(0, distance.toString().indexOf(".")+2) + " km");
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
        List<Object[]> result = ((MarcaFranquiaRepository)getRepository()).findDetalhes(idUnidade);
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
                detalhe.setLatitude((Double)unidades[6]);
                detalhe.setLongitude((Double)unidades[7]);
                detalhe.setEndereco((String)unidades[8]);
                detalhe.setEnderecoResumido((String)unidades[10]);
                detalhe.setSobre((String)unidades[11]);

                Long curtidas = (Long)unidades[12];
                curtidas = curtidas == null ? 0 : curtidas;

                Long descurtidas = (Long)unidades[13];
                descurtidas = descurtidas == null ? 0 : descurtidas;

                Long total = curtidas + descurtidas;
                if(total == 0) {
                    detalhe.setCurtidas(0D);
                    detalhe.setDescurtidas(0D);

                } else {
                    String p = new Double(curtidas/total).toString();
                    p = p.substring(0, p.indexOf(".") + 3);
                    detalhe.setCurtidas(Double.parseDouble(p));

                    p = new Double(descurtidas/total).toString();
                    p = p.substring(0, p.indexOf(".") + 3);
                    detalhe.setDescurtidas(Double.parseDouble(p));
                }

                idCidade = (Long)unidades[14];

                if(unidades[15] != null) {
                    detalhe.setHoraAbrir(sdf.format((Date)unidades[15]));
                    detalhe.setHoraFechar(sdf.format((Date)unidades[16]));
                }

                detalhe.setImagensProduto(imgRepository.findByIdUnidade(detalhe.getIdUnidade()));


                if(lat != null && lon != null && detalhe.getLatitude() != null) {
                    detalhe.setDistancia(GeoUtils.geoDistanceInKm(lat, lon, detalhe.getLatitude(), detalhe.getLongitude()));
                    String d = detalhe.getDistancia().toString();
                    d = d.substring(0, d.indexOf(".") + 3);
                    detalhe.setDistancia(Double.parseDouble(d));
                }
            }

            tc =  TimeCount.start(this.getClass(), "Consulta outras unidades");
            result = ((MarcaFranquiaRepository)getRepository()).findOutrasUnidades(detalhe.getIdMarca(), idCidade);
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

            detalhe.setOfertas(ofertaRepository.findAllAtivasByUnidade(detalhe.getIdUnidade()));

        }

        return detalhe;

    }

}
