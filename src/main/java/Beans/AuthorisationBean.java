/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Models.KwetterUser;
import Models.Roles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import services.KwetterUserService;

@Named("AuthorisationBean")
@RequestScoped
public class AuthorisationBean{
    
    @Inject
    KwetterUserService userService;

    public String generateToken(String username) {
        Key key = new SecretKeySpec("simpleKey".getBytes(), 0, "simpleKey".getBytes().length, "DES");
        String jwtToken = Jwts.builder()
                .setSubject(username)
                //.setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                //.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }
}
