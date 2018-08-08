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
@Stateless
public class VehicleBean {
    @PersistenceContext(unitName = "vehicles")
    private EntityManager manager;
    
    public Vehicle findByData(String licencePlate, String vehicleType, int maximumCapacity, boolean active){
       Query q = manager.createNativeQuery("SELECT * FROM vehicle WHERE licencePlate=? AND "
                                                       + "vehicleType=? AND"
                                                       + "maximumCapacity=?    AND "
                                                       + "active=?",Vehicle.class );
       
       q.setParameter(1, licencePlate);
       q.setParameter(2, vehicleType);
       q.setParameter(3, maximumCapacity);
       q.setParameter(4, active);
       
       return (Vehicle) q.getSingleResult();
    }
    
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
