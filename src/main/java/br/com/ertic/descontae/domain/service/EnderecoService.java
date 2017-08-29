package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Cidade;
import br.com.ertic.descontae.domain.model.Endereco;
import br.com.ertic.descontae.domain.model.Estado;
import br.com.ertic.descontae.infraestructure.persistence.jpa.EnderecoRepository;
import br.com.ertic.util.infraestructure.dto.CepDTO;
import br.com.ertic.util.infraestructure.service.CepService;
import br.com.ertic.util.infraestructure.service.RestFullService;

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
    public Endereco save(Endereco e) {

        if(e.getCep() != null) {
            CepDTO cep = cepService.find(e.getCep());
            e.setLogradouro(cep.getLogradouro());
            e.setBairro(cep.getBairro());
            e.setCidade(new Cidade());
            e.getCidade().setNome(cep.getCidade());
            e.getCidade().setEstado(new Estado());
            e.getCidade().getEstado().setSigla(cep.getUf());
        }

        if(e.getCidade().getId() == null && e.getCidade().getNome() != null) {
            e.setCidade(cidadeService.findByNomeAndSigla(
                e.getCidade().getNome(),
                e.getCidade().getEstado().getSigla()));
        }

        return super.save(e);
    }

}
