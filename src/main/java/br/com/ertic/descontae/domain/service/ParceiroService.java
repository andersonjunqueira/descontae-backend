package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Parceiro;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ParceiroRepository;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class ParceiroService extends RestFullService<Parceiro, Long> {

    @Autowired
    private Token token;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaFranquiaService marcaService;

    @Autowired
    public ParceiroService(ParceiroRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Parceiro save(Parceiro e) {

        if(e.getId() == null) {
            e.setPessoa(pessoaService.findByEmail(token.getUsername()));
            e.setDataCadastro(new Date(System.currentTimeMillis()));
        } else {
            e.setPessoa(pessoaService.findOne(e.getPessoa().getId()));
        }

        e.getUnidades().stream().forEach(unidade -> {
            if(unidade.getEndereco().getLogradouro() == null || unidade.getEndereco().getLogradouro().isEmpty())  {
                if(unidade.getEndereco().getId() != null) {
                    enderecoService.delete(unidade.getEndereco().getId());
                }
                unidade.setEndereco(null);
            } else {
                unidade.setEndereco(enderecoService.save(unidade.getEndereco()));
            }
        });

        e.setCategoria(categoriaService.findOne(e.getCategoria().getId()));
        e.setMarca(marcaService.findOne(e.getMarca().getId()));

        e.setDataAlteracao(new Date(System.currentTimeMillis()));
        return super.save(e);
    }
}
