/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ro.uvt.thesis.logic.ClientBean;
import ro.uvt.thesis.persistance.Client;

/**
 *
 * @author Cristian
 */
@Path("clients")
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
    
    
    @GET
    @Path("retrieveAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> retrieveAll() {
        return clientBean.findAll();
    }

    @GET
    @Path("retrieveById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Client retrieveById(@PathParam("id") long id) {
        Client c =  clientBean.findById(id);
        if(c == null){
            throw new RuntimeException("Client does not exist, id not found!");
        }
        return c;
    }
    
    
    @GET
    @Path("deleteById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") long id) {
        clientBean.removeById(id);
        return "Client with id " + id + " was successfully deleted";
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateById(Client client) {
        clientBean.update(client);
        return "Client was succesfully updated!";
    }
}
