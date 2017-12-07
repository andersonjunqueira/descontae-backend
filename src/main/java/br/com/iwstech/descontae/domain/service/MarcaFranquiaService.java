package br.com.iwstech.descontae.domain.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.MarcaFranquia;
import br.com.iwstech.descontae.domain.model.Telefone;
import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.descontae.domain.model.serializer.TimeDeserializer;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.MarcaFranquiaRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.MarcaFranquiasCustomRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.OfertaRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.TelefoneRepository;
import br.com.iwstech.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.iwstech.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.iwstech.descontae.interfaces.web.dto.HomeUnidadeDTO;
import br.com.iwstech.util.geo.GeoUtils;
import br.com.iwstech.util.geo.TimeCount;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class MarcaFranquiaService  extends RestFullService<MarcaFranquia, Long> {

    @Autowired
    private MarcaFranquiasCustomRepository franquiasCustomRepo;

    @Autowired
    private ImagemUnidadeRepository imgRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    public MarcaFranquiaService(MarcaFranquiaRepository repository) {
        super(repository);
    }

    @Override
    public Page<MarcaFranquia> findAllPageable(Map<String, String[]> params) throws NegocioException {
        return franquiasCustomRepo.findAllMarcas(getExample(params), getPageRequest(params));
    }

    public List<HomeParceiroDTO> findFranquiasByCidade(Long idCidade, String filtro, Double lat, Double lon, String[] categorias) {
        TimeCount tc =  TimeCount.start(this.getClass(), "[Consulta HOME]");

        DateFormat sdf = TimeDeserializer.getParser();
        NumberFormat nf = NumberFormat.getIntegerInstance();

        // SEPARANDO OS IDS DAS CATEGORIAS PARA O FILTRO
        List<Long> idCats = null;
        if(categorias != null) {
            idCats = new ArrayList<>();
            for(int i = 0; i < categorias.length; i++) {
                idCats.add(new Long(categorias[i]));
            }
        }

        // PESQUISANDO AS FRANQUIAS DE ACORDO COM O FILTRO
        List<Long> marcas = null;
        String f = null;
        if(filtro != null) {
            f = filtro.toLowerCase() + "%";
        }

        TimeCount tc1 =  TimeCount.start(this.getClass(), "[Consulta HOME] MARCAS");
        marcas = franquiasCustomRepo.findAllMarcasComOfertasAtivas(idCidade, f, idCats);
        tc1.end();

        tc1 =  TimeCount.start(this.getClass(), "[Consulta HOME] PROCESSAMENTO UNIDADES");
        List<HomeParceiroDTO> franquias = new ArrayList<>();
        for(Long m : marcas) {

            HomeParceiroDTO dto = null;
            Double maiorDesconto = null;
            Double menorDistancia = null;

            // PESQUISANDO AS UNIDADES E COMPLEMENTANDO AS INFORMAÇÕES DA HOME
            TimeCount tc2 =  TimeCount.start(this.getClass(), "[Consulta HOME] UNIDADES");
            List<Object[]> result = franquiasCustomRepo.findUnidadesByCidadeEMarca(idCidade, m);
            tc2.end();

            if(result != null) {

                for(Object[] r : result) {
                    if(dto == null) { // NOVA MARCA
                        dto = new HomeParceiroDTO();
                        dto.setIdMarca(m);
                        dto.setMarca((String)r[2]);
                        dto.setImagem((String)r[3]);
                        dto.setCategoria((String)r[5]);
                        dto.setIdUnidade((Long)r[0]);
                        if(r[15] != null) {
                            dto.setHoraAbrir(sdf.format((Date)r[15]));
                            dto.setHoraFechar(sdf.format((Date)r[16]));
                        }
                    }

                    // IDENTIFICANDO O MAIOR DESCONTO
                    if(r[17] != null) {
                        Double desconto = (Double)r[17];
                        if(maiorDesconto == null) {
                            maiorDesconto = desconto;
                        } else {
                            maiorDesconto = desconto > maiorDesconto ? desconto : maiorDesconto;
                        }
                    }

                    // IDENTIFICANDO A MENOR DISTANCIA
                    if(lat != null && lon != null && r[10] != null) {
                        Double distance = GeoUtils.geoDistanceInKm(lat, lon, (Double)r[10], (Double)r[11]);
                        String d = distance.toString();
                        d = d.substring(0, d.indexOf(".") + 3);
                        distance = Double.parseDouble(d);
                        if(menorDistancia == null) {
                            menorDistancia = distance;
                        } else {
                            menorDistancia = distance < menorDistancia ? distance : menorDistancia;
                        }
                    }
                }
            }

            if(menorDistancia != null) {
                dto.setDistancia(menorDistancia);
                dto.setDistanciaKM(menorDistancia.toString().substring(0, menorDistancia.toString().indexOf(".")+2) + " km");
            }

            dto.setDesconto( (maiorDesconto != null && maiorDesconto > 0D) ? nf.format(maiorDesconto) : null);
            franquias.add(dto);

        }
        tc1.end();

        tc1 =  TimeCount.start(this.getClass(), "[Consulta HOME] ORDENACAO");
        if(franquias.size() > 0) {
            Collections.sort(franquias, new Comparator<HomeParceiroDTO>() {
                @Override
                public int compare(HomeParceiroDTO o1, HomeParceiroDTO o2) {
                    if(o1.getDistancia() != null && o2.getDistancia() != null) {
                        return o1.getDistancia().compareTo(o2.getDistancia());
                    }
                    return 0;
                }
            });
        }
        tc1.end();

        tc.end();
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

                List<Telefone> telefones = telefoneRepository.findByUnidade(new Unidade(detalhe.getIdUnidade()));
                if(telefones != null && telefones.size() > 0) {
                    detalhe.setTelefone(telefones.get(0).getNumero());
                }

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
