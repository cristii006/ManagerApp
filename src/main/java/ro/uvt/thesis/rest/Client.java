/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Cristian
 */
@Path("client")
public class Client {

    @Inject
    ro.uvt.thesis.logic.Client clientBean;

    @GET
    public String create() {
        ro.uvt.thesis.persistance.Client client = new ro.uvt.thesis.persistance.Client("Horia Popa", "Brasov","ionion",054645654);
         clientBean.addNewClient(client);
        return "it worked!";
    }

}
