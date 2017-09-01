package br.com.ertic.descontae.domain.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.ertic.descontae.domain.model.Sexo;

public class SexoSerializer extends JsonSerializer<Sexo> {

    @Override
    public void serialize(final Sexo value, JsonGenerator gen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        if(value != null) {
            gen.writeString(value.getSigla());
        }
    }
}