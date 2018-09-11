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
import ro.uvt.thesis.logic.VehicleBean;
import ro.uvt.thesis.persistance.Vehicle;
/**
 *
 * @author Cristian
 */
@Path("vehicle")
public class VehicleApi {

    @Inject
    VehicleBean vehicleBean;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String onPost(Vehicle vehicle) {
        Vehicle v = new Vehicle(vehicle.getLicencePlate(),vehicle.getVehicleType(),
                                vehicle.getMaximumCapacity(),vehicle.isActive());
        vehicleBean.addNewVehicle(v);
        return "Vehicle Added!";
    }
    
    @GET
    @Path("retrieveAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> retrieveAll() {
        return vehicleBean.findAll();
    }
    
    @GET
    @Path("retrieveById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vehicle retrieveById(@PathParam("id") long id) {
        Vehicle v =  vehicleBean.findById(id);
        if(v == null){
            throw new RuntimeException("Vehicle does not exist, id not found!");
        }
        return v;
    }
    
    @GET
    @Path("deleteById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") long id) {
        vehicleBean.removeById(id);
        return "Vehicle with id " + id + " was successfully deleted";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateById/{id}")
    public String updateById(Vehicle vehicle,
                             @PathParam("id") long id) {
        vehicleBean.update(vehicle, id);
        return "Vehicle was succesfully updated!";
    }

}