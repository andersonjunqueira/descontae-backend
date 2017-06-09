package br.com.ertic.descontae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.infraestructure.persistence.jpa.EmpreendimentoRepository;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.GeoUtils;
import br.com.ertic.util.geo.TimeCount;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class EmpreendimentoService extends RestFullService<Empreendimento, Long> {

    private EmpreendimentoRepository repo = (EmpreendimentoRepository)repository;

    @Autowired
    EmpreendimentoService(EmpreendimentoRepository repository) {
        super(repository);
    }

    public List<HomeParceiroDTO> findParceirosRedondezas(Long idCidade, Double lat, Double lon) {

        TimeCount tc1 =  TimeCount.start("Servico findParceirosRedondezas");
        TimeCount tc2 =  TimeCount.start("Consulta parceiros / cidade");
        List<Object[]> result = repo.findUnidadesByIdCidade(idCidade);
        tc2.end();

       /*
        0 unidade.id, " +
        1 marca.nome,
        2 marca.caminhoLogomarca, " +
        3 categoria.nome,  " +
        4 endereco.latitude,
        5 endereco.longitude, " +
        6 unidade.inicioExpediente,
        7 unidade.fimExpediente, " +
        8 (select sum(a.satisfacao) / count(*) from Avaliacao a WHERE a.idUnidade = unidade.id) " +
        */

        List<HomeParceiroDTO> parceiros = new ArrayList<>();
        for(Object[] r : result) {

            //unidade.id, marca.nome, categoria.nome, endereco.latitude, endereco.longitude, unidade.inicioExpediente, unidade.fimExpediente, marca.caminhoLogomarca
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

        tc1.end();
        return parceiros;
    }

}
