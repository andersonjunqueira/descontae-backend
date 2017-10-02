package br.com.ertic.descontae.domain.service;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;

import br.com.ertic.descontae.interfaces.util.AppConfig;
import br.com.ertic.util.infraestructure.exception.EmailException;
import br.com.ertic.util.infraestructure.service.SendMailService;

@Service
public class EmailService extends SendMailService {

    public void enviarAlteracaoSenha(String nome, String email, String novaSenha) throws EmailException {
        try {

            AppConfig cfg = AppConfig.getInstance();
            enviar(
                cfg.getNomeRemetente(),
                cfg.getEmailRemetente(),
                cfg.getAssuntoReenviarSenha(),
                MessageFormat.format(cfg.getMensagemReenviarSenha(), new Object[] { nome, novaSenha }),
                new InternetAddress(email, nome)
            );

        } catch (UnsupportedEncodingException e) {
            throw new EmailException("Email inválido", e);
        }

    }


}
