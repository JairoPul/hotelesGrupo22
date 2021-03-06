/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo22.hoteling.jaas;

import com.grupo22.hoteling.entities.UserGroups;
import com.grupo22.hoteling.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jairo
 */ 
@Stateless
public class UserEJB {
    @PersistenceContext
    private EntityManager em;
    
    public Users createUser(Users user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGroups group = new UserGroups();
        group.setEmail(user.getEmail());
        group.setGroupname("users");
        em.persist(user);
        em.persist(group);
        return user;
    }
    
    public Users createBusiness(Users user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGroups group = new UserGroups();
        group.setEmail(user.getEmail());
        group.setGroupname("business");
        em.persist(user);
        em.persist(group);
        return user;
    }
    
    public Users findByEmail(String email) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByEmail",Users.class);
        query.setParameter("email", email);
        Users user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {}
        return user;
    }
    
    public void deleteUser(Users usuario) {
        
        TypedQuery<UserGroups> query = em.createNamedQuery("UserGroups.findByEmail",UserGroups.class);
        query.setParameter("email", usuario.getEmail());
        UserGroups usergroup = null;
        
        TypedQuery<Users> query2 = em.createNamedQuery("Users.findByEmail",Users.class);
        query2.setParameter("email", usuario.getEmail());
        Users user = null;
        
        try{
            usergroup = query.getSingleResult();
            user = query2.getSingleResult();
            
            em.remove(usergroup);
            em.remove(user);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
