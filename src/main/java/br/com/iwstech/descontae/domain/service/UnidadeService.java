package br.com.iwstech.descontae.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.iwstech.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class UnidadeService extends RestFullService<Unidade, Long> {

    @Autowired
    UnidadeService(UnidadeRepository repository) {
        super(repository);
    }

    public List<Unidade> findAllByMarca(Long idMarca) throws NegocioException {
        return ((UnidadeRepository)repository).findAllByMarca(idMarca);
    }

    public List<Unidade> findAllByOferta(Long idOferta) throws NegocioException {
        return ((UnidadeRepository)repository).findAllByOferta(idOferta);
    }

    public List<OfertaUnidadeDTO> findUnidadesDTOByMarca(Long marcaId) throws NegocioException {

        List<Unidade> unidades = ((UnidadeRepository)repository).findAllByMarca(marcaId);
        List<OfertaUnidadeDTO> dtos = new ArrayList<>();

        for(Unidade u : unidades) {

            OfertaUnidadeDTO oudto = new OfertaUnidadeDTO();
            dtos.add(oudto);

            oudto.setId(u.getId());
            oudto.setNome(u.getNome());
            oudto.setEndereco(
                u.getEndereco().getLogradouro() + " " +
                u.getEndereco().getComplemento() + " " +
                u.getEndereco().getNumero() + " " +
                u.getEndereco().getBairro()
            );

            oudto.setSelecionada(false);

        }

        return dtos;

    }

}
