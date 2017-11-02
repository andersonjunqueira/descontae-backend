package br.com.iwstech.descontae.infrastructure.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import br.com.iwstech.util.infraestructure.dto.CepDTO;
import br.com.iwstech.util.test.BaseRestTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CepRestTest extends BaseRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCEP() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/ceps/71505220", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        CepDTO z = JsonSerialization.readValue(entity.getBody(), CepDTO.class);
        assertThat(z.getUf()).isEqualTo("DF");

    }

    @Test
    public void getInexistentCEP() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/ceps/70000000", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
