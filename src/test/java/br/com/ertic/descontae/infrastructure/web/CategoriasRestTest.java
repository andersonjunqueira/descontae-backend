package br.com.ertic.descontae.infrastructure.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ertic.descontae.domain.model.Categoria;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.test.BaseRestTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoriasRestTest extends BaseRestTest {

    private String location;
    private Long id;
    private Categoria obj;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        try {
            ObjectNode node = new ObjectMapper().readValue(entity.getBody(), ObjectNode.class);
            if (node.has("totalElements")) {
                int total = node.get("totalElements").asInt();
                assertThat(total).isGreaterThan(0);
            }
        } catch (IOException ex) {
            Log.error(this.getClass(), ex);
        }

    }

    @Test
    public void getInexistentOne() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias/9999", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void insertViaPOSTWithID() throws Exception {

        location = null;
        id = null;
        obj = null;

        HttpHeaders headers = getDefaultHeaders();

        Categoria c = new Categoria();
        c.setId(9999L);
        c.setNome("TEMPORARIO");

        HttpEntity<?> e = new HttpEntity<Object>(c, headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias", HttpMethod.POST, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test(dependsOnMethods={"insertViaPOSTWithID"})
    public void insertViaPOST() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        Categoria c = new Categoria();
        c.setNome("TEMPORARIO");

        HttpEntity<?> e = new HttpEntity<Object>(c, headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias", HttpMethod.POST, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI l = entity.getHeaders().getLocation();
        assertThat(l).isNotNull();

        location = l.toString();
        id = new Long(location.substring(location.lastIndexOf("/") + 1));

    }

    @Test(dependsOnMethods={"insertViaPOST"})
    public void getOne() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias/" + id, HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        obj = new ObjectMapper().readValue(entity.getBody(), Categoria.class);
        assertThat(obj).isNotNull();
        assertThat(obj.getNome()).isNotNull();

    }

    @Test(dependsOnMethods={"getOne"})
    public void updateViaPUTWithId() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        obj.setNome("TEMP2");

        HttpEntity<?> e = new HttpEntity<Object>(obj, headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias/" + obj.getId(), HttpMethod.PUT, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        obj = new ObjectMapper().readValue(entity.getBody(), Categoria.class);
        assertThat(obj).isNotNull();
        assertThat(obj.getNome()).isNotNull();
        assertThat(obj.getNome()).isEqualTo("TEMP2");

    }

    @Test(dependsOnMethods={"updateViaPUTWithId"})
    public void updateViaPUT() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        obj.setNome("TEMP3");

        HttpEntity<?> e = new HttpEntity<Object>(obj, headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias", HttpMethod.PUT, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        obj = new ObjectMapper().readValue(entity.getBody(), Categoria.class);
        assertThat(obj).isNotNull();
        assertThat(obj.getNome()).isNotNull();
        assertThat(obj.getNome()).isEqualTo("TEMP3");

    }

    @Test(dependsOnMethods={"updateViaPUT"})
    public void delete() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange(location, HttpMethod.DELETE, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test(dependsOnMethods={"delete"})
    public void insertViaPUT() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        Categoria c = new Categoria();
        c.setNome("TEMPORARIO");

        HttpEntity<?> e = new HttpEntity<Object>(c, headers);

        ResponseEntity<String> entity = restTemplate.exchange("/api/categorias", HttpMethod.PUT, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI l = entity.getHeaders().getLocation();
        assertThat(l).isNotNull();

        location = l.toString();
        id = new Long(location.substring(location.lastIndexOf("/") + 1));

    }

    @Test(dependsOnMethods={"insertViaPUT"})
    public void deleteAfterPUT() throws Exception {

        HttpHeaders headers = getDefaultHeaders();

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange(location, HttpMethod.DELETE, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
