/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.client;

import com.grupo22.hoteling.entities.Users;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alvaro
 */
@Named
@RequestScoped
public class BusinessClientBean {
    Client client;
    WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target =
        client.target("http://localhost:8080/hotelesGrupo22/webresources/com.grupo22.hoteling.entities.users");
    }
    
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Users[] getBusinesses() {
        return target.path("businesses")
                     .request()
                     .get(Users[].class);
    }
    
    public String verify(Users business){
        business.setVerified(Boolean.TRUE);
        target.path("{id}")
                .resolveTemplate("id", business.getId())
                .request()
                .put(Entity.entity(business, MediaType.APPLICATION_XML));
        return "/admin/businesses";
    }
    
    public String unverify(Users business){
        business.setVerified(Boolean.FALSE);
        target.path("{id}")
                .resolveTemplate("id", business.getId())
                .request()
                .put(Entity.entity(business, MediaType.APPLICATION_XML));
        return "/admin/businesses";
    }
}
