/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Authentication.TokenGenerator;
import Dao.JPA;
import Dao.UserDao;
import Models.KwetterUser;
import Models.account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import services.KwetterUserService;

@Path("Authorization")
public class AuthorisationResource {
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private IdentityStoreHandler identityStoreHandler;
    
    @Inject
    private TokenGenerator tokenGenerator;
    
    @Inject
    private KwetterUserService userService;
        
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(account account) {
        CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(account.getUsername(), account.getPassword()));
        if(result.getStatus() == CredentialValidationResult.Status.VALID) {
            KwetterUser user = userService.login(account.getUsername(), account.getPassword());
            String token = tokenGenerator.createToken(user);
            try {
                return Response.status(Response.Status.OK).entity(new ObjectMapper().writeValueAsString(user)).header("Authorization", token).build();
            } catch (JsonProcessingException ex) {
                Logger.getLogger(AuthorisationResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
}
