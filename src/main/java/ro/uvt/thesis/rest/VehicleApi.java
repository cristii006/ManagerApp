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
    @Path("/licence_plate/{licencePlate}/vehicle_type/"
            + "{vehicleType}/maximum_capacity/{maximumCapacity}/active/{active}")
    public String onPost(@PathParam("licencePlate") String licencePlate,
                         @PathParam("vehicleType") String vehicleType,
                         @PathParam("maximumCapacity") int maximumCapacity,
                         @PathParam("active") boolean active) {
        Vehicle vehicle = new Vehicle(licencePlate, vehicleType, maximumCapacity, active);
        vehicleBean.addNewVehicle(vehicle);
        return "Vehicle Added!";
    }
    
    
    @GET
    @Path("/licence_plate/{licencePlate}/vehicle_type/"
            + "{vehicleType}/maximum_capacity/{maximumCapacity}/active/{active}")
    public String onGet(@PathParam("licencePlate") String licencePlate,
                         @PathParam("vehicleType") String vehicleType,
                         @PathParam("maximumCapacity") int maximumCapacity,
                         @PathParam("active") boolean active) {
        return vehicleBean.findByData(licencePlate, vehicleType, maximumCapacity, active).toString();
    }

}