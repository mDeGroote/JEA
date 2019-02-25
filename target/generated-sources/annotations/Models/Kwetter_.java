package Models;

import Models.KwetterUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-24T17:40:48")
@StaticMetamodel(Kwetter.class)
public class Kwetter_ { 

    public static volatile SingularAttribute<Kwetter, String> kwetterDate;
    public static volatile SingularAttribute<Kwetter, Integer> id;
    public static volatile SingularAttribute<Kwetter, String> title;
    public static volatile SingularAttribute<Kwetter, KwetterUser> user;
    public static volatile SingularAttribute<Kwetter, String> content;

}