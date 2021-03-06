/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.logic;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ro.uvt.thesis.persistance.Shipment;

/**
 *
 * @author Cristian
 */
@Stateless
public class ShipmentBean {
    
    @PersistenceContext(unitName = "shipments")
    private EntityManager manager;
    
    
    public void addNewShipment(Shipment shipment){
        manager.persist(shipment);
    }
    
    public void removeById(long id){
        manager.remove(manager.find(Shipment.class, id));
    }
    
    public void update(Shipment shipment, long id){
       Shipment s =  manager.find(Shipment.class, id);
       s.setDeparturePlace(shipment.getDeparturePlace());
       s.setArrivalPlace(shipment.getArrivalPlace());
       s.setFinaldate(shipment.getFinaldate());
       s.setTransportType(shipment.getTransportType());
       s.setCapacity(shipment.getCapacity());
       s.setClient_id(shipment.getClient_id());
       manager.merge(s);
    }
    
    public int count() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        Root<Shipment> rt = cq.from(Shipment.class);
        cq.select(manager.getCriteriaBuilder().count(rt));
        Query q = manager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<Shipment> findAll(){
        return manager.createQuery("SELECT t FROM shipment t").getResultList();
    }
    
    public Shipment findById(long id){
        return manager.find(Shipment.class, id);
    }
}
