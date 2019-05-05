/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.Encoders;

import Models.Kwetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class KwetterEncoder implements Encoder.Binary<Kwetter>{

    @Override
    public ByteBuffer encode(Kwetter object) throws EncodeException {
        try {
            return ByteBuffer.wrap(new ObjectMapper().writeValueAsBytes(object));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(KwetterEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void init(EndpointConfig config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
