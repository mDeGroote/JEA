/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import Models.KwetterUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;

@Stateless
public class TokenGenerator {

    private String secretKey = "simpleKey";

    public String createToken(KwetterUser user) {
        Key key = new SecretKeySpec("simpleKey".getBytes(), 0, "simpleKey".getBytes().length, "DES");
        String jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                //.setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                //.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;

    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

}
