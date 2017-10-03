package br.com.ertic.descontae.domain.model.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.ertic.util.infraestructure.log.Log;

public class DataHoraDeserializer extends JsonDeserializer<Date> {

    private static SimpleDateFormat sdf;

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

        final String value = p.getText();
        if(value != null && value.length() > 0) {
            try {
                return DataHoraDeserializer.getParser().parse(value);
            } catch (ParseException ex) {
                Log.error(this.getClass(), "Erro processamento de data: " + value, ex);
                return null;
            }
        }
        return null;

    }

    public static DateFormat getParser() {
        if(sdf == null) {
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        }
        return sdf;
    }
}