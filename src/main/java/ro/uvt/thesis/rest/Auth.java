/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

/**
 *
 * @author dan
 */
@Path("login")
public class Auth {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void auth(Credentials cred){
        
    }
    
}

