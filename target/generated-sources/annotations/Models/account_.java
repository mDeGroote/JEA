package Models;

import Models.KwetterUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-25T16:13:45")
@StaticMetamodel(account.class)
public class account_ { 

    public static volatile SingularAttribute<account, String> password;
    public static volatile SingularAttribute<account, Integer> id;
    public static volatile SingularAttribute<account, KwetterUser> user;
    public static volatile SingularAttribute<account, String> username;

}