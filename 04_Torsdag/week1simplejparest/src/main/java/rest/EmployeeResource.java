/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.FacadeEmployee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author vnord
 */
@Path("employee")
public class EmployeeResource {

    @Context
    private UriInfo context;
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmp() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(emf);
        List<Employee> list = facade.getAllEmployees();
        List<EmployeeDTO> listDTO = new ArrayList<>();
        //EmployeeDTO eDTO;
        for (Employee e : list) {
            listDTO.add(new EmployeeDTO(e));
        }
        //return listDTO;
        //return Response.ok().entity(gson.toJson(listDTO)).build();
        return Response.ok().entity(gson.toJson(listDTO)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpById(@PathParam("id") Long id) {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(emf);
        Employee emp = facade.getEmployeeById(id);
        EmployeeDTO empDTO = new EmployeeDTO(emp);
        return Response.ok().entity(gson.toJson(empDTO)).build();
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpHighSal() {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(emf);
        Employee emp = facade.getEmployeesWithHighestSalary();
        EmployeeDTO empDTO = new EmployeeDTO(emp);
        return Response.ok().entity(gson.toJson(empDTO)).build();
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpByName(@PathParam("name") String name) {
        FacadeEmployee facade = FacadeEmployee.getFacadeEmployee(emf);
        List<Employee> empList = facade.getEmployeesByName(name);
        List<EmployeeDTO> dtoList = new ArrayList<>();
        for (Employee e : empList) {
            dtoList.add(new EmployeeDTO(e));
        }
        return Response.ok().entity(gson.toJson(dtoList)).build();
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getJson() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
}
