/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.rest;

import com.grupo22.hoteling.entities.Hotel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alvaro
 */
@Stateless
@Path("com.grupo22.hoteling.entities.hotel")
public class HotelFacadeREST extends AbstractFacade<Hotel> {

    @PersistenceContext(unitName = "com.grupo22_hotelesGrupo22_war_v1PU")
    private EntityManager em;

    public HotelFacadeREST() {
        super(Hotel.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Hotel entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Hotel entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hotel find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hotel> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hotel> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("company/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hotel> findAllFromCompany(@PathParam("id") int id) {
        try {
            return em.createNamedQuery("Hotel.findByCompany", Hotel.class)
                     .setParameter("id", id)
                     .getResultList();
        } catch (NoResultException e) {
            return null;  
        }
    }
    
    @GET
    @Path("company/{id}/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countFromCompany(@PathParam("id") int id) {
        try {
            return String.valueOf(em.createNamedQuery("Hotel.countByCompany", Integer.class)
                     .setParameter("id", id)
                     .getSingleResult());
        } catch (NoResultException e) {
            return null;  
        }
    }
    
    @GET
    @Path("cities")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> findCities(){
        try {
            return em.createNamedQuery("Hotel.findCities", String.class)
                     .getResultList();
        } catch (NoResultException e) {
            return null;  
        }
    }
    
    @GET
    @Path("city/{city}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hotel> findInCity(@PathParam("city") String city){
        try {
            return em.createNamedQuery("Hotel.findByCity", Hotel.class)
                     .setParameter("city", city)
                     .getResultList();
        } catch (NoResultException e) {
            return null;  
        }
    }
    
}
