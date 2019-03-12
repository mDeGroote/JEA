/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DateDeserializer extends StdDeserializer<Date>{

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public DateDeserializer(Class<?> vc) {
        super(vc);
    }
    
    public DateDeserializer() {
        this(null);
    }

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try {
            return formatter.parse(jp.getText());
        } catch (ParseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
}
