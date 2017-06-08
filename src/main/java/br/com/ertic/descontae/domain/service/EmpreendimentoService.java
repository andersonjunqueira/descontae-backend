package br.com.ertic.descontae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.infraestructure.persistence.jpa.EmpreendimentoRepository;
import br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class EmpreendimentoService extends RestFullService<Empreendimento, Long> {

    private EmpreendimentoRepository repo = (EmpreendimentoRepository)repository;

    @Autowired
    EmpreendimentoService(EmpreendimentoRepository repository) {
        super(repository);
    }

    public List<Empreendimento> findEmpreendimentosRedondezas(Long idCidade, Double lat, Double lon) {

        List<ListaEmpreendimentoDTO> emps = repo.findUnidadesByIdCidade(idCidade);

        //TODO PARA CADA EMPREENDIMENTO
        //TODO CALCULA A MEDIA DAS AVALIACOES
        //TODO CALCULA A DISTANCIA ENTRE A LOCALIDADE DA UNIDADE E DO PONTO
        //TODO JOGAR EM UM NOVO DTO PARA TRANSFERENCIA PARA A HOME


        return null;
    }

}
