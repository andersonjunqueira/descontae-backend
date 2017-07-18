package br.com.ertic.descontae;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootApplication(scanBasePackages={
    "br.com.ertic.descontae.interfaces.web",
    "br.com.ertic.descontae.domain.service",
    "br.com.ertic.descontae.infraestructure.persistence.jpa",
    "br.com.ertic.util.infraestructure.web",
    "br.com.ertic.util.infraestructure.service"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccessToken getAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();
    }

}
