/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uvt.thesis.logic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ro.uvt.thesis.persistance.Client;

/**
 *
 * @author Cristian
 */

@Stateless
public class ClientBean {
    
    @PersistenceContext(unitName = "clients")
    private EntityManager manager;
    
    
    public void addNewClient(Client client){
         manager.persist(client);
    }
    
}
