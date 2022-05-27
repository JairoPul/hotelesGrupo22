/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.client;

import com.grupo22.hoteling.entities.Hotel;
import com.grupo22.hoteling.jaas.LoginView;
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
public class HotelClientBean {
    
    Client client;
    WebTarget target;
    
    @Inject
    LoginView loginBean;
    @Inject
    HotelBackingBean hotelBean;

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
    
    public Hotel[] getHotels() {
        return target.request()
                     .get(Hotel[].class);
    }
    
    public Hotel[] getHotelsFromCompany() {
        return target.path("company/{companyId}")
                     .resolveTemplate("companyId", loginBean.getAuthenticatedUser().getId())
                     .request()
                     .get(Hotel[].class);
    }
    
    public int getNumberHotelsFromCompany() {
        return target.path("company/{companyId}/count")
                     .resolveTemplate("companyId", loginBean.getAuthenticatedUser().getId())
                     .request()
                     .get(Integer.class);
    }
    
    public void addHotel() {
        Hotel h = new Hotel();
        h.setId(1);
        h.setName(hotelBean.getName());
        h.setCity(hotelBean.getCity());
        h.setRooms(hotelBean.getRooms());
        h.setPrice(hotelBean.getPrice());
        h.setServices(hotelBean.getServices());
        h.setOwner(loginBean.getAuthenticatedUser());
        target.request().post(Entity.entity(h, MediaType.APPLICATION_XML));
    }
}
