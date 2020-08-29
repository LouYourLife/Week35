package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacadeExampleTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final FacadeExample FE = FacadeExample.getFacadeExample(ENF);
    public FacadeExampleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test a method here.
     */
    //@Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
//        assertTrue(true);
    }
    
    @Test
    public void testGetEmpById() {
        Employee e = new Employee("Lou","Hell",48372);
        //EmployeeDTO eDTO = new EmployeeDTO(e);
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(ENF);
        //facade.createEmployee("Lou", "Hell", 75935);
        assertEquals(facade.getEmployeeById(4L).getAddress(), e.getAddress());
    }
    
    @Test
    public void testGetEmployeesByName() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(ENF);
        List<Employee> listResult = facade.getEmployeesByName("Lou");
        Employee e = new Employee("Lou","Hell",48372);
        e.setId(4L);
        List<Employee> listExpected = new ArrayList<>();
        listExpected.add(e);
        assertEquals(listResult, listExpected);
    }
    
    @Test
    public void testGetAllEmployees() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(ENF);
        List<Employee> list = facade.getAllEmployees();
        assertEquals(list.size(), 4);
    }
    
    @Test
    public void testGetEmpHighSalary() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(ENF);
        Employee emp = facade.getEmployeesWithHighestSalary();
        assertEquals(emp.getSalary(), 105738);
    }
    
    @Test
    public void testCreateEmp() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(ENF);
        Employee emp = facade.createEmployee("Theodore", "Underland", 33333);
        assertTrue(emp!=null);
    }
    
}
