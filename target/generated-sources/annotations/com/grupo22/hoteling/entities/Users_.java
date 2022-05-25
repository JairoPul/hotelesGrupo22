package com.grupo22.hoteling.entities;

import com.grupo22.hoteling.entities.Reserve;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< Updated upstream
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T00:03:46")
=======
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T14:25:18")
>>>>>>> Stashed changes
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> cif;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, Date> bdate;
    public static volatile CollectionAttribute<Users, Reserve> reserveCollection;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Integer> tel;
    public static volatile SingularAttribute<Users, String> nif;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Double> worth;

}