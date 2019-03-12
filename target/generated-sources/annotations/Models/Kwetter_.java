package Models;

import Models.KwetterUser;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-12T14:49:36")
@StaticMetamodel(Kwetter.class)
public class Kwetter_ { 

    public static volatile SingularAttribute<Kwetter, LocalDate> kwetterDate;
    public static volatile SingularAttribute<Kwetter, Integer> id;
    public static volatile SingularAttribute<Kwetter, String> title;
    public static volatile SingularAttribute<Kwetter, KwetterUser> user;
    public static volatile SingularAttribute<Kwetter, String> content;

}