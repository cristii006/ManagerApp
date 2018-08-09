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
import ro.uvt.thesis.logic.ShipmentBean;
import ro.uvt.thesis.persistance.Shipment;

/**
 *
 * @author Cristian
 */
@Path("shipments")
public class ShipmentApi {

    @Inject
    ShipmentBean shipmentBean;
    
    @GET
    @Path("retrieveAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipment> getAll(){
        return shipmentBean.findAll();
    }
    
   
    @GET
    @Path("retrieveById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipment getById(@PathParam("id") long id){
        Shipment s =  shipmentBean.findById(id);
        if(s == null){
            throw new RuntimeException("Shipment does not exist!");
        }
        return s;
    }
    
    @GET
    @Path("removeById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeById(@PathParam("id") long id){
        shipmentBean.removeById(id);
        return "Shipment removed!";
    }
  
    
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Shipment s){
        shipmentBean.update(s);
        return "Shipment updated!";
    }
    
}
