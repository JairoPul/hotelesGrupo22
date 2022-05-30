/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.booking;

import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import com.grupo22.hoteling.entities.Hotel;
import com.grupo22.hoteling.jaas.SecurityUtils;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jairo
 */
@Named
@FlowScoped("booking")
public class Booking implements Serializable{
    private String city;
    private Hotel hotel;
    private Date date;
    private double price;
    private String card;
    private Date fecha;
    
    @PersistenceContext
    private EntityManager em;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    Date currentDate = new Date();

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public double storeHotel(Hotel h) {
        this.hotel = h;
        return h.getPrice();
    }
}
