package br.com.ertic.descontae.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ertic.descontae.domain.model.Parceiro;
import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ParceiroRepository;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.exception.NegocioException;
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
    public Parceiro save(Parceiro parceiro) throws NegocioException {

        if(parceiro.getId() == null) {
            parceiro.setPessoa(pessoaService.findByEmail(token.getUsername()));
            parceiro.setDataCadastro(new Date(System.currentTimeMillis()));
            parceiro.setId(super.nextVal(Parceiro.SEQUENCE));
        } else {
            parceiro.setPessoa(pessoaService.findOne(parceiro.getPessoa().getId()));
        }

        parceiro.setCategoria(categoriaService.findOne(parceiro.getCategoria().getId()));
        parceiro.setMarca(marcaService.findOne(parceiro.getMarca().getId()));
        parceiro.setDataAlteracao(new Date(System.currentTimeMillis()));

        // SALVANDO OS TELEFONES
        parceiro.getTelefones().stream().forEach(telefone -> {
            telefone.setParceiro(parceiro);
        });

        for(Unidade unidade : parceiro.getUnidades()) {
            unidade.setParceiro(parceiro);

            // SALVANDO O ENDEREÇO DA UNIDADE
            if(unidade.getEndereco().getLogradouro() == null || unidade.getEndereco().getLogradouro().isEmpty())  {
                if(unidade.getEndereco().getId() != null) {
                    enderecoService.delete(unidade.getEndereco().getId());
                }
                unidade.setEndereco(null);
            } else {
                unidade.setEndereco(enderecoService.addOrUpdate(unidade.getEndereco()));
            }

            // SALVANDO AS IMAGENS
            unidade.getImagens().stream().forEach(imagem -> {
                imagem.setUnidade(unidade);
            });

            // SALVANDO OS TELEFONES
            unidade.getTelefones().stream().forEach(telefone -> {
                telefone.setUnidade(unidade);
            });

        }

        return super.save(parceiro);
    }
}
