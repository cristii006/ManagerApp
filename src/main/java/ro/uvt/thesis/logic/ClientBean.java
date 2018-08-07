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
import ro.uvt.thesis.persistance.Client;

/**
 *
 * @author Cristian
 */

@Stateless
public class ClientBean {
    
    @PersistenceContext(unitName = "clients")
    private EntityManager manager;
    
    
    public Client findByData(String name, String location, String email, String phone){
       Query q = manager.createNativeQuery("SELECT * FROM client WHERE name=? AND "
                                                       + "location=? AND"
                                                       + "email=?    AND "
                                                       + "phone=?",Client.class );
       
       q.setParameter(1, name);
       q.setParameter(2, location);
       q.setParameter(3, email);
       q.setParameter(4, phone);
       
       return (Client) q.getSingleResult();
    }
    
    
    public void addNewClient(Client client){
        manager.persist(client);
    }
    
    public void removeById(long id){
        manager.remove(manager.find(Client.class, id));
    }
    
    public void update(Client client){
        manager.persist(manager.merge(client));
    }
    
    public int count() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        Root<Client> rt = cq.from(Client.class);
        cq.select(manager.getCriteriaBuilder().count(rt));
        Query q = manager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<Client> findAll(){
        return manager.createQuery("SELECT t FROM" + Client.class.getSimpleName()+ "t").getResultList();
    }
    
    public Client findById(long id){
        return manager.find(Client.class, id);
    }
}
