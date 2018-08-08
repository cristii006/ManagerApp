/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            @PathParam("finaldate") String finaldate,
            @PathParam("transportType") String transportType,
            @PathParam("load") int load) {
        Shipment shipment;
        try {
            shipment = new Shipment(departurePlace, arrivalPlace, new SimpleDateFormat("dd/MM/yyyy").parse(finaldate), transportType, load);
            shipmentBean.addNewShipment(shipment);
            return "Shipment Added!";
        } catch (ParseException ex) {
            Logger.getLogger(ShipmentApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("Error on getting Shipment");
    }

    @GET
    @Path("/departure_place/{departurePlace}/arrival_place/"
            + "{arrivalPlace}/final_date/{finaldate}/transport_type/{transportType}/load/{load}")
    public String onGet(@PathParam("departurePlace") String departurePlace,
            @PathParam("arrivalPlace") String arrivalPlace,
            @PathParam("finaldate") String finaldate,
            @PathParam("transportType") String transportType,
            @PathParam("load") int load) {
        try {
            return shipmentBean.findByData(departurePlace, arrivalPlace, new SimpleDateFormat("dd/MM/yyyy").parse(finaldate), transportType, load).toString();
        } catch (ParseException ex) {
            Logger.getLogger(ShipmentApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("error onGET shipments");
    }

}
