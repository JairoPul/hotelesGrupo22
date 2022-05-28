/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.client;

import com.grupo22.hoteling.entities.Hotel;
import com.grupo22.hoteling.entities.Reserve;
import com.grupo22.hoteling.entities.Users;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Alvaro
 */
@Named
@SessionScoped
public class ReserveBackingBean implements Serializable {
    private Integer id;
    
    private Date day;
    
    private Hotel hotel;
    
    private Users customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }
    
    public void saveReserve(Reserve r) {
        this.id = r.getId();
        this.day = r.getDay();
        this.hotel = r.getHotel();
        this.customer = r.getCustomer();
    }
}
