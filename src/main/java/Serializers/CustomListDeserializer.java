/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializers;

import Models.KwetterUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CustomListDeserializer extends StdDeserializer<List<KwetterUser>>{

    public CustomListDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomListDeserializer() {
        this(null);
    }
    
    @Override
    public List<KwetterUser> deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return new ArrayList<>();
    }
    
}
