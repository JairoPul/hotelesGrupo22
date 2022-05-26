/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.jaas;

import com.grupo22.hoteling.entities.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
@Named
@SessionScoped
public class LoginView implements Serializable{
    
    private String email;
    private String password;
    private Users user;
    
    @Inject
    private UserEJB userEJB;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Users getAuthenticatedUser() {
        return user;
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Login incorrecto!", null));
            return "login";
        }
        
        this.user = userEJB.findByEmail(request.getUserPrincipal().getName());

        if (request.isUserInRole("users")) {
            return "/users/welcomepage?faces-redirect=true";
        } else if (request.isUserInRole("business")) {
            return "/business/welcomepage?faces-redirect=true";
        } else if (request.isUserInRole("admin")) {
            return "/admin/welcomepage?faces-redirect=true";
        } else {
            return "login";
        }
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            this.user = null;
        } catch (ServletException e) {
            System.out.println("Fallo durante el proceso de logout!");
        }
        
        return "/index?faces-redirect=true";
    }
    
    public String deleteAccount() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            userEJB.deleteUser(this.user);
            this.user = null;
        } catch (ServletException e) {
            System.out.println("Fallo durante el proceso de logout!");
        }
        
        return "/delok?faces-redirect=true";
    }
    
    public void showVerificationMessage() {        
        
        if (!user.isVerified()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La cuenta está pendiente de verificación por parte del administrador.", null));
        }
        
    }
    
}
