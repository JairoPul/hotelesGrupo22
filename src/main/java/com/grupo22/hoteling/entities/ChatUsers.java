/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "chat_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatUsers.findAll", query = "SELECT c FROM ChatUsers c"),
    @NamedQuery(name = "ChatUsers.findAllUsers", query = "SELECT c.name FROM ChatUsers c"),
    @NamedQuery(name = "ChatUsers.findById", query = "SELECT c FROM ChatUsers c WHERE c.id = :id"),
    @NamedQuery(name = "ChatUsers.findByName", query = "SELECT c FROM ChatUsers c WHERE c.name = :name")})
public class ChatUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    public ChatUsers() {
    }

    public ChatUsers(Integer id) {
        this.id = id;
    }
    
    public ChatUsers(String name) {
        this.name = name;
    }

    public ChatUsers(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatUsers)) {
            return false;
        }
        ChatUsers other = (ChatUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupo22.hoteling.entities.ChatUsers[ id=" + id + " ]";
    }
    
}
