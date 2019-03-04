/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Models.Kwetter;
import Models.KwetterUser;
import java.net.URI;
import java.util.List;
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
@Path("kwetters")
public class KwetterResource {
    @Inject
    KwetterService kwetterService;

    public KwetterResource() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Kwetter k) {
        k = kwetterService.Create(k);
        return Response.ok(k).build();
    }
    
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) {
        kwetterService.Delete(kwetterService.kwetterByID(id));
        return Response.ok().build();
    }
    
    @GET
    @Path("{id}")
    public Response kwetterByID(@PathParam("id") int id) {
        Kwetter kwetter = kwetterService.kwetterByID(id);
        if(kwetter != null) {
            return Response.ok(kwetter).build();
        }
        throw new WebApplicationException(Response.Status.NO_CONTENT);
    }
       
    @GET
    @Path("/search/{searchTerm}")
    public Response search(@PathParam("searchTerm") String s) {
        List<Kwetter> kwetters = kwetterService.search(s);
        if(kwetters != null) {
            return Response.ok(kwetters).build();
        }
        throw new WebApplicationException(Response.Status.NO_CONTENT);
    }
}
