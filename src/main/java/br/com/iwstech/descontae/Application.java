package br.com.iwstech.descontae;

import java.util.Base64;

import javax.servlet.Filter;
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

import br.com.iwstech.util.infraestructure.dto.Token;

@SpringBootApplication(scanBasePackages = {
    "br.com.iwstech.descontae.interfaces.web",
    "br.com.iwstech.descontae.domain.service",
    "br.com.iwstech.descontae.infraestructure.persistence.jpa",
    "br.com.iwstech.util.infraestructure.web",
    "br.com.iwstech.util.infraestructure.service"
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
            return readToken(token);
        }
        return null;
    }

    public static Token readToken(String accessToken) {

        String token = accessToken.replaceAll("Bearer ", "");
        String[] parts = token.split("\\.");
        if (parts.length < 2 || parts.length > 3) {
        } else {
            byte[] bytes = Base64.getDecoder().decode(parts[1]);
            token = new String(bytes);
        }

        String id = token.substring(token.indexOf("\"sub\":\"")+7);
        id = id.substring(0, id.indexOf("\""));

        String name = token.substring(token.indexOf("\"name\":\"")+8);
        name = name.substring(0, name.indexOf("\""));

        String ntoken = token.substring(token.indexOf("preferred_username")+21);
        ntoken = ntoken.substring(0, ntoken.indexOf("\""));

        return new Token(ntoken, id, name);
    }

    @Bean
    public Filter compressFilter() {
        return new JSONFilter();
    }

}
