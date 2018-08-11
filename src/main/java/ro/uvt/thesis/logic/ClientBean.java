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
    
    
    public Client findById(int id){
       Query q = manager.createQuery("SELECT c FROM client c WHERE c.id = :id ");
       
       q.setParameter("id", id);
       
       return (Client) q.getSingleResult();
    }
    
    
    public void addNewClient(Client client){
        manager.persist(client);
    }
    
    public void removeById(long id){
        manager.remove(manager.find(Client.class, id));
    }
    
    public void update(Client client, long id){
       Client c =  manager.find(Client.class, id);
       c.setEmail(client.getEmail());
       c.setLocation(client.getLocation());
       c.setPhone(client.getPhone());
       c.setName(client.getName());
       
       manager.merge(c);
    }
    
    public int count() {
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        Root<Client> rt = cq.from(Client.class);
        cq.select(manager.getCriteriaBuilder().count(rt));
        Query q = manager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<Client> findAll(){
        return manager.createQuery("SELECT t FROM client t").getResultList();
    }
    
    public Client findById(long id){
        return manager.find(Client.class, id);
    }
}
