package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            Query q1 = em.createQuery("select e.salary from Employee e where e.salary > 100000");
            List<Employee> r1 = q1.getResultList();
            System.out.println(r1);
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Query q2 = em.createQuery("select e.firstName from Employee e where e.id = :id");
            q2.setParameter("id", "klo999");
            String r2 = (String)q2.getSingleResult();
            System.out.println(r2);
            
            //3) Create a query to fetch the highest salary and print the value
            Query q3 = em.createQuery("select MAX(e.salary) from Employee e");
            BigDecimal r3 = (BigDecimal)q3.getSingleResult();
            System.out.println(r3);

            //4) Create a query to fetch the firstName of all Employees and print the names
            Query q4 = em.createQuery("select e.firstName from Employee e");
            List<String> r4 = q4.getResultList();
            System.out.println(r4);
           
            //5 Create a query to calculate the number of employees and print the number
            Query q5 = em.createQuery("select COUNT(e) from Employee e");
            Long r5 = (Long)q5.getSingleResult();
            System.out.println(r5);
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query q6 = em.createQuery("select e from Employee e where e.salary = :maxs");
            q6.setParameter("maxs", r3);
            Employee r6 = (Employee)q6.getSingleResult();
            System.out.println(r6.toString());
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
