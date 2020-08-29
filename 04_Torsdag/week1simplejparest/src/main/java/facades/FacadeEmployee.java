package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FacadeEmployee {
    private static EntityManagerFactory emf;
    private static FacadeEmployee instance;
    
    private FacadeEmployee() {}
    
    public static FacadeEmployee getFacadeEmployee(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new FacadeEmployee();
        }
        return instance;
    }
    
    //getEmployeeById
    public Employee getEmployeeById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = em.find(Employee.class, id);
            return emp;
        } finally {
            em.close();
        }
    }
    
    //getEmployeesByName
    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("select e from Employee e where e.name = :name", Employee.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }
    
    //getAllEmployees
    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("select e from Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    //getEmployeesWithHighestSalary
    public Employee getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.salary = (select MAX(e.salary) from Employee e)", Employee.class);
            Employee emp = query.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }
    
    //createEmployee
    public Employee createEmployee(String name, String adr, int salary) {
        Employee emp = new Employee(name, adr, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        FacadeEmployee fe = FacadeEmployee.getFacadeEmployee(emf);
//        Employee emp = fe.createEmployee("Edith Hearth", "Elder Bouldevard 44", 184326);
//        Employee emp2 = fe.createEmployee("Adelaide Nightingale", "Parkvej 5", 93728);
        System.out.println("Find by id: " + fe.getEmployeeById(2L).toString());
        System.out.println("Find by name: " + fe.getEmployeesByName("Thomas"));
        System.out.println("Find all employees: " + fe.getAllEmployees());
        System.out.println("Find by highest salary: " + fe.getEmployeesWithHighestSalary());
    }
}
