package dbfacade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerFacadeTest {
    
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public static final CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    
    public CustomerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Customer cus = facade.addCustomer("Edith", "Theodor");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Customer cus = facade.addCustomer("Edith", "Theodor");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByID() {
        int id = 1;
        Long expResult = 1L;
        Customer result = facade.findByID(1);
        assertEquals(expResult, result.getId());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        String name = "Stuart";
        List<Customer> expResult = null;
        String expR = "Stuart";
        List<Customer> result = facade.findByLastName(name);
        assertEquals(expR, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        //CustomerFacade instance = null;
        Long expResult = 4L;
        Long result = facade.getNumberOfCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomers() {
        CustomerFacade instance = null;
        int expResult = 5;
        List<Customer> result = facade.allCustomers();
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        Customer result = facade.addCustomer("Freya", "North");
        assertTrue(result!=null);
    }
    
}
