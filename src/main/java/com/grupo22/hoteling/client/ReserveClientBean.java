/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.client;

import com.grupo22.hoteling.entities.Reserve;
import com.grupo22.hoteling.jaas.LoginView;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Alvaro
 */
@Named
@RequestScoped
public class ReserveClientBean {
    Client client;
    WebTarget target;
    
    @Inject
    LoginView loginBean;
    @Inject
    ReserveBackingBean reserveBean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target =
        client.target("http://localhost:8080/hotelesGrupo22/webresources/com.grupo22.hoteling.entities.reserve");
    }
    
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Reserve[] getReservesFromCustomer() {
        return target.path("customer/{customerId}")
                     .resolveTemplate("customerId", loginBean.getAuthenticatedUser().getId())
                     .request()
                     .get(Reserve[].class);
    }
    
    public void cancelReserve() {
        target.path("{id}")
              .resolveTemplate("id", reserveBean.getId())
              .request()
              .delete();
    }
}
