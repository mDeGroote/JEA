/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializers;

import Models.KwetterUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CustomListSerializer extends StdSerializer<List<KwetterUser>>{

    public CustomListSerializer(Class<List<KwetterUser>> t) {
        super(t);
    }

    public CustomListSerializer() {
        this(null);
    }
    
    @Override
    public void serialize(List<KwetterUser> t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        List<Integer> ids = new ArrayList<>();
        for(KwetterUser u : t) {
            ids.add(u.getId());
        }
        new ObjectMapper().writeValueAsString(ids);
    }
    
}
