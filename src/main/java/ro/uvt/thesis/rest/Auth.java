/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("login")
public class Auth {
    
    @Context
    SecurityContext securityContext;
   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean auth(){
        return securityContext.isUserInRole("admin");
    }

    @GET
    @Path("logout")
    public void deauth(@Context HttpServletRequest request) throws ServletException{
        request.logout();
    }    
}

