package br.com.iwstech.descontae.domain.service;

import static org.springframework.data.domain.ExampleMatcher.matching;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.iwstech.descontae.domain.model.Assinatura;
import br.com.iwstech.descontae.domain.model.Cartao;
import br.com.iwstech.descontae.domain.model.Pessoa;
import br.com.iwstech.descontae.domain.model.Telefone;
import br.com.iwstech.descontae.domain.model.TipoPessoa;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.PessoaRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.TelefoneRepository;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.TipoPessoaRepository;
import br.com.iwstech.util.infraestructure.dto.Token;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.security.PasswordGenerator;
import br.com.iwstech.util.infraestructure.service.KeycloakService;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class PessoaService extends RestFullService<Pessoa, Long> {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TipoPessoaRepository tpRepo;

    @Autowired
    private TelefoneRepository telRepo;

    @Autowired
    PessoaService(PessoaRepository repository) {
        super(repository);
    }

    public Pessoa findByEmail(String email) throws NegocioException {

        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        ExampleMatcher em = matching();
        em = matching().withMatcher("email", matcher -> matcher.ignoreCase());
        pessoa = getRepository().findOne(Example.of(pessoa, em));

        if(pessoa != null) {
            Cartao c = cartaoService.findAtivoPorUsuario(email);
            pessoa.setCartaoAtivo(c != null);
        }

        return pessoa;
    }

    public Pessoa findByCPF(String cpf) throws NegocioException {
        if(cpf != null && cpf.length() == 0) {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(cpf);
            pessoa = getRepository().findOne(Example.of(pessoa));
            return pessoa;
        }
        return null;
    }

    @Override
    public Pessoa add(Pessoa e) throws NegocioException {

        // VERIFICANDO SE O CPF JÁ EXISTE
        if(!StringUtils.isEmpty(e.getCpf())) {
            Pessoa p = findByCPF(e.getCpf());
            if(p != null) {
                throw new NegocioException("cpf-ja-registrado");
            }
        }

        // VERIFICANDO SE O EMAIL JÁ ESTÁ REGISTRADO NO BANCO
        Pessoa dbu = findByEmail(e.getEmail());

        // VERIFICANDO SE O EMAIL JÁ EXISTE NO KEYCLOAK
        UserRepresentation kcu = keycloakService.findUserByEmail(e.getEmail());

        boolean db = dbu != null;
        boolean kc = kcu != null;

        if(db && kc) { // ENCONTRADO NO BANCO E NO KC = ERRO
            // ERRO JÁ REGISTRADO
            throw new NegocioException("email-ja-registrado");
        }

        if(!kc) {
            boolean emailPassword = false;
            if(StringUtils.isEmpty(e.getSenha())) {
                e.setSenha(PasswordGenerator.generate(8));
                emailPassword = true;
            }

            keycloakService.createUser(e.getNome(), " ", e.getEmail(), e.getSenha());

            if(emailPassword) {
                //TODO ENVIAR EMAIL DA SENHA
                //e.getEmail(), e.getSenha();
            }
        }

        if(!db) {
            e.setDataCadastro(new Date(System.currentTimeMillis()));
            e.setIdioma(e.getIdioma() != null ? e.getIdioma() : "pt-BR");
            return super.add(e);
        } else {
            return dbu;
        }
    }

    @Override
    protected Pessoa save(Pessoa e) throws NegocioException {

        try {
            if(e.getTipoPessoa() == null) {
                e.setTipoPessoa(TipoPessoa.TIPO_USUARIO);
            }

            e.setTipoPessoa(tpRepo.findOne(e.getTipoPessoa().getId()));

            e.setDataAlteracao(new Date(System.currentTimeMillis()));
            if(e.getEndereco() != null && e.getEndereco().getCep() != null)  {
                e.setEndereco(enderecoService.addOrUpdate(e.getEndereco()));
            } else {
                e.setEndereco(null);
            }

            List<Telefone> ts = new ArrayList<>();

            if(e.getTelefones() != null) {
                ts.addAll(e.getTelefones());
                e.getTelefones().clear();
            }

            // IF INCLUIDO PARA MANTER A COMPATIBILIDADE DA ENTIDADE RECEBENDO
            // UM CAMPO TELEFONE E NÃO UM ARRAY
            if(e.getTelefone() != null) {
                List<Telefone> tels = ts.stream()
                    .filter(tel -> tel.getNumero().equals(e.getTelefone()))
                    .collect(Collectors.toList());
                if(tels.size() == 0) {
                    ts.add(new Telefone(e.getTelefone()));
                }
            }

            Pessoa saida = repository.save(e);

            for(Telefone t : ts) {
                Telefone nt = new Telefone();
                nt.setPessoa(saida);
                nt.setNumero(t.getNumero());
                telRepo.save(nt);
            }

            return saida;

        } catch(Exception ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    public boolean verificar(String cpf, String email) {
        Long total = 0L;
        if(cpf != null && cpf.length() > 0) {
            total += ((PessoaRepository)repository).verificarCPFDuplicado(cpf);
        }

        if(email != null) {
            total += ((PessoaRepository)repository).verificarEmailDuplicado(email);
        }

        return total > 0;
    }

    public Assinatura findAssinaturaVigente(String email) throws NegocioException {
        return ((PessoaRepository)repository).findAssinaturaVigente(email);
    }

    public void alterarSenha(Token token) throws NegocioException {

        try {

            Pessoa p = findByEmail(token.getUsername());
            if(p != null) {
                throw new NegocioException("email-nao-encontrado");
            }

            String novaSenha = PasswordGenerator.generate(8);
            keycloakService.defineUserPassword(token.getUserId(), novaSenha);
            emailService.enviarAlteracaoSenha(token.getName(), token.getUsername(), novaSenha);

        } catch(Exception ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @Override
    public void delete(Long id) throws NegocioException {
        // TODO não pode apagar pessoa relacionada a cliente ou parceiro
        // TODO remover o endereço primeiro
        throw new NegocioException("metodo-nao-disponivel");
    }
}
