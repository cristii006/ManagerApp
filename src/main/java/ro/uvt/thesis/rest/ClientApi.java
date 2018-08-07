/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("/name/{name}/location/{location}/email/{email}/phone/{phone}")
    public String onPost(@PathParam("name") String name,
                         @PathParam("location") String location,
                         @PathParam("email") String email,
                         @PathParam("phone") String phone) {
        Client client = new Client(name,location,email,phone);
        clientBean.addNewClient(client);
        return "Client Added!";
    }
    
    
    @GET
    @Path("/name/{name}/location/{location}/email/{email}/phone/{phone}")
    public String onGet(@PathParam("name") String name,
                         @PathParam("location") String location,
                         @PathParam("email") String email,
                         @PathParam("phone") String phone) {
        return clientBean.findByData(name, location, email, phone).toString();
    }

}
