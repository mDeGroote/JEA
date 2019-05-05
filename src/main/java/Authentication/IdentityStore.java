/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import Models.KwetterUser;
import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;

@ApplicationScoped
public class IdentityStore implements javax.security.enterprise.identitystore.IdentityStore{

    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager em;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential)credential;
            Query query = this.em.createQuery("SELECT u FROM KwetterUser u WHERE u.username = ?1 AND u.password = ?2");
            query.setParameter(1, usernamePasswordCredential.getCaller());
            query.setParameter(2, usernamePasswordCredential.getPasswordAsString());
            try {
                KwetterUser u  = (KwetterUser)query.getSingleResult();
                return new CredentialValidationResult(new CallerPrincipal(u.getName()), new HashSet<>(Arrays.asList(u.getUserRole().toString())));
            } catch (NoResultException ex) {
                return CredentialValidationResult.NOT_VALIDATED_RESULT;
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }

}
