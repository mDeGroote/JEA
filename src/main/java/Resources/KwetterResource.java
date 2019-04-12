/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Interfaces.JWTTokenNeeded;
import Models.Kwetter;
import Models.KwetterUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import services.KwetterService;

/**
 *
 * Resource for the kwetters
 */
@Stateless
@Path("Kwetters")
public class KwetterResource {
    @Inject
    KwetterService kwetterService;

    public KwetterResource() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Kwetter k) {
        k = kwetterService.Create(k);
        try {
            return Response.status(Response.Status.CREATED).entity(new ObjectMapper().writeValueAsString(k)).build();
        } catch (JsonProcessingException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        if(id != 0) {
            kwetterService.Delete(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response kwetterByID(@PathParam("id") int id) {
        Kwetter kwetter = kwetterService.kwetterByID(id);
        if(kwetter != null) {
            try {
                return Response.status(Response.Status.OK).entity(new ObjectMapper().writeValueAsString(kwetter)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
       
    @GET
    @Path("/search/{searchTerm}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response search(@PathParam("searchTerm") String s) {
        List<Kwetter> kwetters = kwetterService.search(s);
        if(kwetters != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(kwetters)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getKwettersFromUser(KwetterUser u) {
        List<Kwetter> kwetters = kwetterService.getKwettersFromUser(u);
        if(kwetters != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(kwetters)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
