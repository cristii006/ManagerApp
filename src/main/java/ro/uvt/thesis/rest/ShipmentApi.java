/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import java.util.GregorianCalendar;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import ro.uvt.thesis.logic.ShipmentBean;
import ro.uvt.thesis.persistance.Shipment;

/**
 *
 * @author Cristian
 */
@Path("shipment")
public class ShipmentApi {

    @Inject
    ShipmentBean shipmentBean;

    @POST
    @Path("/departure_place/{departurePlace}/arrival_place/"
            + "{arrivalPlace}/final_date/{finaldate}/transport_type/{transportType}/load/{load}")
    public String onPost(@PathParam("departurePlace") String departurePlace,
                         @PathParam("arrivalPlace") String arrivalPlace,
                         @PathParam("finaldate") GregorianCalendar finaldate,
                         @PathParam("transportType") String transportType,
                         @PathParam("load") int load) {
        Shipment shipment = new Shipment(departurePlace, arrivalPlace, finaldate, transportType, load);
        shipmentBean.addNewShipment(shipment);
        return "Shipment Added!";
    }
    
    
    @GET
    @Path("/departure_place/{departurePlace}/arrival_place/"
            + "{arrivalPlace}/final_date/{finaldate}/transport_type/{transportType}/load/{load}")
    public String onGet(@PathParam("departurePlace") String departurePlace,
                         @PathParam("arrivalPlace") String arrivalPlace,
                         @PathParam("finaldate") GregorianCalendar finaldate,
                         @PathParam("transportType") String transportType,
                         @PathParam("load") int load) {
        return shipmentBean.findByData(departurePlace, arrivalPlace, finaldate, transportType, load).toString();
    }

}

