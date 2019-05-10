/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import DTO.KwetterJsonDTO;
import Models.Kwetter;
import Models.KwetterUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import services.KwetterService;
import services.KwetterUserService;

@javax.websocket.server.ServerEndpoint(value = "/KwetterWS/{id}")
public class KwetterWebsocketEndpoint {
    private final static Map<Integer, Session> SESSIONS = Collections.synchronizedMap(new HashMap());
    
    @Inject
    private KwetterService kwetterService;
    
    @Inject
    private KwetterUserService kwetterUserService;
    
    @OnOpen
    public void open(Session session, @PathParam("id") int id) {
        this.SESSIONS.put(id, session);    
    }
    
    @OnClose
    public void close(Session session, @PathParam("id") int id) {
        this.SESSIONS.remove(id, session);
    }
    
    @OnError
    public void onError(Throwable error) {
        
    }
    
    @OnMessage
    public void handleMessage(Session session, String message) {
        try {
            KwetterJsonDTO kwetter = new ObjectMapper().readValue(message, KwetterJsonDTO.class);
            Kwetter k = kwetterService.Create(kwetter);
            String kweet = new ObjectMapper().writeValueAsString(k);
            session.getAsyncRemote().sendText(kweet);
            for(KwetterUser follower: k.getUser().getFollowers()) {
                try {
                    this.SESSIONS.get(follower.getId()).getAsyncRemote().sendText(kweet);
                }
                catch(NullPointerException ex) {}
            }
        } catch (IOException ex) {
            Logger.getLogger(KwetterWebsocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @OnMessage
    public void handleMessage(Session session, ByteBuffer bytes) {
        for(Session sess: session.getOpenSessions()) {
            if(sess.isOpen()) {
                sess.getAsyncRemote().sendBinary(bytes);
                System.out.println("Message sent: " + bytes.toString());
            }
        }
    }
}
