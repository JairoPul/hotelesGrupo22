/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jairo
 */
@Entity
@Table(name = "hotel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findByName", query = "SELECT h FROM Hotel h WHERE h.name = :name"),
    @NamedQuery(name = "Hotel.findByCity", query = "SELECT h FROM Hotel h WHERE h.city = :city"),
    @NamedQuery(name = "Hotel.findByRooms", query = "SELECT h FROM Hotel h WHERE h.rooms = :rooms"),
    @NamedQuery(name = "Hotel.findByPrice", query = "SELECT h FROM Hotel h WHERE h.price = :price"),
    @NamedQuery(name = "Hotel.findByServices", query = "SELECT h FROM Hotel h WHERE h.services = :services"),
    @NamedQuery(name = "Hotel.findByCompany", query = "SELECT h FROM Hotel h WHERE h.owner = ("
            + "SELECT u FROM Users u WHERE u.id = :id)"),
    @NamedQuery(name = "Hotel.countByCompany", query = "SELECT COUNT(h) FROM Hotel h WHERE h.owner = ("
            + "SELECT u FROM Users u WHERE u.id = :id)"),
    @NamedQuery(name = "Hotel.findCities", query = "SELECT DISTINCT h.city FROM Hotel h")})
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rooms")
    private int rooms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Size(max = 255)
    @Column(name = "services")
    private String services;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private Collection<Reserve> reserveCollection;
    @JoinColumn(name = "owner", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Users owner;

    public Hotel() {
    }

     public Hotel(Integer id) {
        this.id = id;
    }
    
    public Hotel(String name) {
        this.name = name;
    }

    public Hotel(String name, String city, int rooms, double price) {
        this.name = name;
        this.city = city;
        this.rooms = rooms;
        this.price = price;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    @XmlTransient
    public Collection<Reserve> getReserveCollection() {
        return reserveCollection;
    }

    public void setReserveCollection(Collection<Reserve> reserveCollection) {
        this.reserveCollection = reserveCollection;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        return !((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name)));
    }

    @Override
    public String toString() {
        return "com.grupo22.hoteling.entities.Hotel[ name=" + name + " ]";
    }

   

}
