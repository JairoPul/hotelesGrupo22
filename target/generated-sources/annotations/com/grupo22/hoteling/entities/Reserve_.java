package com.grupo22.hoteling.entities;

import com.grupo22.hoteling.entities.Hotel;
import com.grupo22.hoteling.entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< Updated upstream
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T00:03:46")
=======
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T14:25:18")
>>>>>>> Stashed changes
@StaticMetamodel(Reserve.class)
public class Reserve_ { 

    public static volatile SingularAttribute<Reserve, Hotel> hotel;
    public static volatile SingularAttribute<Reserve, Integer> id;
    public static volatile SingularAttribute<Reserve, Date> day;
    public static volatile SingularAttribute<Reserve, Users> customer;

}