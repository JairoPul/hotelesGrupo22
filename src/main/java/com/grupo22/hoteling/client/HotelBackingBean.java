/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.client;

import com.grupo22.hoteling.entities.Hotel;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Alvaro
 */
@Named
@SessionScoped
public class HotelBackingBean implements Serializable{
    
    private int id;
    private String name;
    private String city;
    private int rooms;
    private Double price;
    private String services;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
    
    public void  saveHotel(Hotel h) {
        this.id = h.getId();
        this.name = h.getName();
        this.city = h.getCity();
        this.rooms = h.getRooms();
        this.price = h.getPrice();
        this.services = h.getServices();
    }
    
    public void flush(){
        this.id = 0;
        this.name = null;
        this.city = null;
        this.rooms = 0;
        this.price = null;
        this.services = null;
    }
    
}
