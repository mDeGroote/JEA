/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateSerializer extends StdSerializer<Date>{

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public DateSerializer(Class<Date> t) {
        super(t);
    }

    public DateSerializer() {
        this(null);
    }
    
    @Override
    public void serialize(Date t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(formatter.format(t));
    }
    
}
