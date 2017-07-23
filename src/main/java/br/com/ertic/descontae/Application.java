package br.com.ertic.descontae;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.ertic.util.infraestructure.dto.Token;

@SpringBootApplication(scanBasePackages = {
    "br.com.ertic.descontae.interfaces.web",
    "br.com.ertic.descontae.domain.service",
    "br.com.ertic.descontae.infraestructure.persistence.jpa",
    "br.com.ertic.util.infraestructure.web",
    "br.com.ertic.util.infraestructure.service"
})
@EnableWebSecurity
@Import(value={ WebConfig.class, WebSecurityConfig.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Token getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = request.getHeader("Authorization");
        if(token != null) {
            token = token.replaceAll("Bearer ", "");
            String[] parts = token.split("\\.");
            if (parts.length < 2 || parts.length > 3) {
            } else {
                byte[] bytes = Base64.getDecoder().decode(parts[1]);
                token = new String(bytes);
            }

            token = token.substring(token.indexOf("preferred_username")+21);
            token = token.substring(0, token.indexOf("\""));

            return new Token(token);
        }

        return null;
    }

}
