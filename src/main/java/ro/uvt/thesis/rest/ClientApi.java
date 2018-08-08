/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ro.uvt.thesis.logic.ClientBean;
import ro.uvt.thesis.persistance.Client;

/**
 *
 * @author Cristian
 */
@Path("client")
public class ClientApi {

    @Inject
   ClientBean clientBean;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String onPost(Client client) {
        clientBean.addNewClient(client);
        return "Client Added!";
    }
    
    
    @POST
    @Path("retrieve")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Client onGet(int id) {
        return clientBean.findById(id);
    }

}
