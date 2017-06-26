package br.com.ertic.descontae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
