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
import ro.uvt.thesis.persistance.Vehicle;
/**
 *
 * @author Cristian
 */
public class VehicleBean {
    @PersistenceContext(unitName = "vehicle")
    private EntityManager manager;
    
    
    public void addNewVehicle(Vehicle vehicle){
        manager.persist(vehicle);
    }
    
    public void removeById(long id){
        manager.remove(manager.find(Vehicle.class, id));
    }
    
    public void update(Vehicle vehicle){
        manager.persist(manager.merge(vehicle));
    }
    
    public int count() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        Root<Vehicle> rt = cq.from(Vehicle.class);
        cq.select(manager.getCriteriaBuilder().count(rt));
        Query q = manager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<Vehicle> findAll(){
        return manager.createQuery("SELECT t FROM" + Vehicle.class.getSimpleName()+ "t").getResultList();
    }
    
    public Vehicle findById(long id){
        return manager.find(Vehicle.class, id);
    }
}
