package com.grupo22.hoteling.entities;

import com.grupo22.hoteling.entities.Reserve;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< Updated upstream
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T00:03:46")
=======
@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-25T14:25:18")
>>>>>>> Stashed changes
@StaticMetamodel(Hotel.class)
public class Hotel_ { 

    public static volatile SingularAttribute<Hotel, Integer> rooms;
    public static volatile SingularAttribute<Hotel, String> city;
    public static volatile CollectionAttribute<Hotel, Reserve> reserveCollection;
    public static volatile SingularAttribute<Hotel, Double> price;
    public static volatile SingularAttribute<Hotel, String> name;
    public static volatile SingularAttribute<Hotel, String> services;

}