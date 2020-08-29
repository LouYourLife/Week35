/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vnord
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer cus1 = new Customer("Lou", "Moore");
        Customer cus2 = new Customer("Theodore", "Tower");
        
        try {
            em.getTransaction().begin();
            em.persist(cus1);
            em.persist(cus2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
