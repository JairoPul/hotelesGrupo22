/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.booking;

import com.grupo22.hoteling.entities.Hotel;
import com.grupo22.hoteling.entities.Users;
import com.grupo22.hoteling.entities.Reserve;
import com.grupo22.hoteling.jaas.LoginView;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alvaro
 */
@Named
@RequestScoped
public class BookingClientBean {
    Client client;
    WebTarget target;
    
    @Inject
    LoginView loginBean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target =
        client.target("http://localhost:8080/hotelesGrupo22/webresources/com.grupo22.hoteling.entities.hotel");
    }
    
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
   
    
    public String[] findCities() {
        return target.path("cities")
                     .request()
                     .get(String[].class);
    }
    
    public Hotel[] findHotelsInCity(String city) {
        return target.path("city/{city}")
                     .resolveTemplate("city", city)
                     .request()
                     .get(Hotel[].class);
    }
    
    public String newReserve(Date day, Hotel hotel){
        Reserve r = new Reserve(day,hotel,loginBean.getAuthenticatedUser());
        target.request().post(Entity.entity(r, MediaType.APPLICATION_XML));
        return "confirm";
    }
}
