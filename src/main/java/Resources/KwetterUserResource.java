/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Authentication.TokenGenerator;
import Beans.AuthorisationBean;
import DTO.KwetterUserDTO;
import Interfaces.JWTTokenNeeded;
import Models.KwetterUser;
import Models.account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import services.KwetterUserService;

/**
 *
 * Resource for the users
 */
@Stateless
@Path("users")
public class KwetterUserResource {

    @Inject
    private KwetterUserService kwetterUserService;
    
    @Inject
    private TokenGenerator tokenGenerator;

    public KwetterUserResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(KwetterUser u) {
        u = kwetterUserService.Create(u);
        if (u != null) {
            try {
                return Response.status(Response.Status.CREATED).entity(new ObjectMapper().writeValueAsString(u)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(KwetterUserDTO u) {
        if (u == null || u.getId() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        KwetterUser user = kwetterUserService.Update(u);
        try {
            return Response.ok(new ObjectMapper().writeValueAsString(user)).build();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllUsers() {
        List<KwetterUser> users = kwetterUserService.getAll();
        if (users != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(users)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response getKwetterUser(@PathParam("id") int id) {
        KwetterUser kwetterUser = kwetterUserService.userByID(id);
        if (kwetterUser != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(kwetterUser)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        kwetterUserService.DeleteUser(kwetterUserService.userByID(id));
        return Response.ok().build();
    }

    @POST
    @Path("Login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(account account) {
        KwetterUser user = kwetterUserService.login(account.getUsername(), account.getPassword());
        if (user != null) {
            try {
                String token = tokenGenerator.createToken(account.getUsername());
                return Response.status(Response.Status.OK).entity(new ObjectMapper().writeValueAsString(user)).header(AUTHORIZATION, "Bearer " + token).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("follow/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response follow(@PathParam("id") int userID, KwetterUser u) {
        u = kwetterUserService.follow(u, kwetterUserService.userByID(userID));
        if (u != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(u)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("unfollow/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response unfollow(@PathParam("id") int id, KwetterUser u) {
        KwetterUser user = kwetterUserService.unfollow(u, kwetterUserService.userByID(id));
        if (user != null) {
            try {
                return Response.ok(new ObjectMapper().writeValueAsString(user)).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(KwetterUserResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
