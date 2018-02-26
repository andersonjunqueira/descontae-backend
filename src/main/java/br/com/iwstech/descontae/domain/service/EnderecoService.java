package br.com.iwstech.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.iwstech.descontae.domain.model.Endereco;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.EnderecoRepository;
import br.com.iwstech.util.infraestructure.dto.CepDTO;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.CepService;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class EnderecoService extends RestFullService<Endereco, Long> {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CepService cepService;

    @Autowired
    EnderecoService(EnderecoRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Endereco save(Endereco e) throws NegocioException {

        if(e.getCep() != null) {
            CepDTO cep = cepService.find(e.getCep());

            if(cep == null) {
                throw new NegocioException("cep-inexistente");
            }

            e.setLogradouro(cep.getLogradouro());
            e.setBairro(cep.getBairro());
            e.setCidade(cidadeService.findByNomeAndSigla(cep.getCidade(), cep.getUf()));

        }

        return repository.save(e);
    }

}
