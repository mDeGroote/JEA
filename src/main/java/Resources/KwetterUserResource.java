/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Models.KwetterUser;
import Models.account;
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
import org.eclipse.persistence.annotations.DeleteAll;
import services.KwetterUserService;

/**
 *
 * Resource for the users
 */
@Stateless
@Path("users")
public class KwetterUserResource {
    @Inject
    KwetterUserService kwetterUserService;

    public KwetterUserResource() {
    }
    
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(KwetterUser u) {
        u = kwetterUserService.Create(u);
        return Response.ok(u).status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(KwetterUser u) {
        u = kwetterUserService.Update(u);
        return Response.ok(u).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllUsers() {
        List<KwetterUser> users = kwetterUserService.getAll();
        if(users != null) {
            return Response.ok(users).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getKwetterUser(@PathParam("id") int id) {
        KwetterUser kwetterUser = kwetterUserService.userByID(id);
        if(kwetterUser != null) {
          return Response.ok(kwetterUser).build();  
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAccount(account account) {
        kwetterUserService.DeleteAccount(account);
        return Response.ok().build();       
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        kwetterUserService.DeleteUser(kwetterUserService.userByID(id));
        return Response.ok().build();       
    }
    
    @POST
    @Path("Register")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registerUser(account account) {
        kwetterUserService.registerUser(account);
        return Response.ok().build();
    }
    
    @POST
    @Path("Login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(account account) {
        KwetterUser user = kwetterUserService.login(account.getUsername(), account.getPassword());
        if(user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
