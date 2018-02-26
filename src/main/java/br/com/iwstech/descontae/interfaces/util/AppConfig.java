package br.com.iwstech.descontae.interfaces.util;

import org.springframework.stereotype.Service;

import br.com.iwstech.util.infraestructure.service.EnvService;

@Service
public class AppConfig extends EnvService {

    private static AppConfig config;

    private AppConfig() {}

    public static AppConfig getInstance() {
        if(config == null) {
            config = new AppConfig();
        }
        return config;
    }

    public String getNomeRemetente() {
        return getEnvProperty("descontae.mail.fromName");
    }

    public String getEmailRemetente() {
        return getEnvProperty("descontae.mail.from");
    }

    public String getAssuntoReenviarSenha() {
        return getEnvProperty("descontae.mail.senha.assunto");
    }

    public String getMensagemReenviarSenha() {
        return getEnvProperty("descontae.mail.senha.mensagem");
    }



    public String getKeycloakAuthEndpoint() {
        return getEnvProperty("keycloak.auth-server-url");
    }

    public String getKeycloakTokenEndpoint() {
        return getEnvProperty("admin.keycloak.tokenendpoint");
    }

    public String getKeycloakClientId() {
        return getEnvProperty("admin.keycloak.clientid");
    }

    public String getKeycloakClientSecret() {
        return getEnvProperty("admin.keycloak.clientsecret");
    }

    public String getKeycloakUsername() {
        return getEnvProperty("admin.keycloak.username");
    }

    public String getKeycloakPassword() {
        return getEnvProperty("admin.keycloak.password");
    }

    public String getKeycloakRealm() {
        return getEnvProperty("keycloak.realm");
    }

}
