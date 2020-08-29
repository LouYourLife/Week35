/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
//import javax.websocket.server.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vnord
 */
@Path("animalsdb")
public class AnimalFromDB {

    @Context
    private UriInfo context;
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }
    
    @GET
    @Path("/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("select a from Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    //Time to think!!
    //animalById
    @GET
    @Path("/animalbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal getAnimalById(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        Animal a = em.find(Animal.class, id);
        if(a == null) {
            throw new EntityNotFoundException("Can't find the animal with id " + id);
        }
        return a;
    }
    
    //animalByType
    @GET
    @Path("/animalbytype/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> tq = em.createQuery("select a from Animal a where a.type = :type", Animal.class);
            tq.setParameter("type", type);
            List<Animal> aList = tq.getResultList();
            Animal a = tq.getSingleResult();
            //return new Gson().toJson(aList);
            return a;
        } finally {
            em.close();
        }
    }
    
    //Random Animal
    @GET
    @Path("/randomAnimal")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        Random ran = new Random();
        long id = ran.nextInt(4);
        Animal a = em.find(Animal.class, id);
        if(a == null) {
            throw new EntityNotFoundException("Can't find an animal with id " + id);
        }
        return a;
    }

//    /**
//     * Retrieves representation of an instance of rest.AnimalFromDB
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getJson() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }

//    /**
//     * PUT method for updating or creating an instance of AnimalFromDB
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
}
