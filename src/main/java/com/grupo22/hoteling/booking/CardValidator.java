/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.booking;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author Jairo
 */
@FacesValidator("cardValidator")
public class CardValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String tarjeta = value.toString().replace("-", "");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://valdavia.infor.uva.es:8080/hoteling/webresources/tarjetas");
        try{
            String response = target.path("{tarjeta}").resolveTemplate("tarjeta",tarjeta).request(MediaType.APPLICATION_JSON).get(String.class);
            JSONObject json = new JSONObject(response); 
            if (json.getString("autorizada").equals("no")){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", value + " no es una tarjeta válida."));
        }
    }
    
}
