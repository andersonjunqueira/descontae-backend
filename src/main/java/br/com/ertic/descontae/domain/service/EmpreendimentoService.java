package br.com.ertic.descontae.domain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.infraestructure.persistence.jpa.EmpreendimentoRepository;
import br.com.ertic.descontae.interfaces.util.GoogleAPIKeys;
import br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO;
import br.com.ertic.util.infraestructure.dto.CepDTO;
import br.com.ertic.util.infraestructure.service.CepService;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class EmpreendimentoService extends RestFullService<Empreendimento, Long> {

    @Autowired
    private CepService cepService;

    @Autowired
    EmpreendimentoService(EmpreendimentoRepository repository) {
        super(repository);
    }

    public List<Empreendimento> findEmpreendimentosRedondezas(Double lat, Double lon) {

        if(lat != null && lon != null) {
            String uf = getUFFromLatLon(lat, lon);

            if(uf != null) {
                List<ListaEmpreendimentoDTO> emps = ((EmpreendimentoRepository)repository).findUnidadesByUF(uf);

                //TODO PARA CADA EMPREENDIMENTO
                //TODO CALCULA A MEDIA DAS AVALIACOES
                //TODO CALCULA A DISTANCIA ENTRE A LOCALIDADE DA UNIDADE E DO PONTO
                //TODO JOGAR EM UM NOVO DTO PARA TRANSFERENCIA PARA A HOME

            }

        }

        return null;
    }

    private String getUFFromLatLon(Double lat, Double lon) {

        GeoApiContext context = new GeoApiContext().setApiKey(GoogleAPIKeys.GEOCODING_KEY);
        LatLng coordinates = new LatLng(lat, lon);

        try {

            GeocodingResult[] results =
                GeocodingApi.reverseGeocode(context,coordinates)
                .language(GoogleAPIKeys.LANGUAGE)
                .await();

            if(results != null && results.length > 0) {
                for(AddressComponent ac : results[0].addressComponents) {
                    if(ac.shortName.matches("\\d{5}-\\d{3}")) {
                        CepDTO cep = cepService.find(ac.shortName);
                        return cep.getUf();
                    }
                }
            }

        } catch(IOException | InterruptedException | ApiException ex) {
            //TODO Ajustar
            ex.printStackTrace();
        }

        return null;
    }
}
