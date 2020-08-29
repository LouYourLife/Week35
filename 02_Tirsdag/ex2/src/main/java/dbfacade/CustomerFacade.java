/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vnord
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    private CustomerFacade() {}
    
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    //Customer findByID(int id);
    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select c from Customer c where c.id = :id");
            q.setParameter("id", id);
            Customer cus = (Customer)q.getSingleResult();
            return cus;
        } finally {
            em.close();
        }
    }
    
    //List<Customer> findByLastName(String name);
    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("select customer from Customer customer where customer.lastName = :name", Customer.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }
    
    //int getNumberOfCustomers();
    public Long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select COUNT(c) from Customer c");
            Long num = (Long)q.getSingleResult();
            return num;
        } finally {
            em.close();
        }
    }
    
    //List<Customer> allCustomers();
    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select c from Customer c");
            List<Customer> list = q.getResultList();
            return list;
        } finally {
            em.close();
        }
    }
    
    //Customer addCustomer(String fName, String lName);
    public Customer addCustomer(String fName, String lName) {
        Customer cus = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        //Convert Long object to integer:
        //Long l = new Long(1);
        //int i = l.intValue();
        
        System.out.println("Find by id: " + facade.findByID(1));
        System.out.println("Find by last name: " + facade.findByLastName("Moore"));
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
        System.out.println("List of all customers: " + facade.allCustomers());
        Customer cus = facade.addCustomer("Mary", "Stuart");
    }
}
