package Models;

import Models.KwetterUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-24T17:40:48")
@StaticMetamodel(KwetterUser.class)
public class KwetterUser_ { 

    public static volatile SingularAttribute<KwetterUser, byte[]> profilePicture;
    public static volatile SingularAttribute<KwetterUser, String> website;
    public static volatile ListAttribute<KwetterUser, KwetterUser> followers;
    public static volatile SingularAttribute<KwetterUser, String> locatie;
    public static volatile ListAttribute<KwetterUser, KwetterUser> following;
    public static volatile SingularAttribute<KwetterUser, String> name;
    public static volatile SingularAttribute<KwetterUser, String> bio;
    public static volatile SingularAttribute<KwetterUser, Integer> id;

}