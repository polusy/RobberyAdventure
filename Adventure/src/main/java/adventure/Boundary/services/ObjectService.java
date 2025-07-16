/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary.services;

import adventure.Boundary.DatabaseManager;
import adventure.Entity.objects.AdvObject;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
/**
 *
 * La classe rappresenta la configurazione REST del server per permettere richieste http
 * per quanto riguarda i servizi di inserimento degli oggetti in uno storage e recupero degli stessi dallo storage.
 * 
 * @author Paolo
 *
 */
@Path("object")
public class ObjectService {
    
    private final static DatabaseManager databaseManager;
    /**
     * il blocco statico permette di istanziare il db,
     * senza istanziare concretamente la classe GameService nel gioco.
     */
    static{
        try{
            databaseManager = new DatabaseManager();
        }catch(SQLException exception){
            throw new ExceptionInInitializerError();
        }
    }
    
    /**
     *
     * Il metodo REST si occupa di convertire il jsonObject in AdvObject
     * e di chiamare il metodo di inserimento (del dbManager) di un oggetto 
     * all'interno del db.
     * 
     * @param jsonObject Rappresenta la serializzazione json di un AdvObject.
     * @return Response -> risultato di inserimento dell'oggetto (AdvObject) nel db.
     * @throws SQLException
     * 
     */
    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObject(String jsonObject) throws SQLException{
        
       Gson gson = new Gson();
       AdvObject object = gson.fromJson(jsonObject, AdvObject.class);
	
       String objectIdString = object.getId().name();
       String name = object.getName();
       String description = object.getDescription();
       String aliasJson = gson.toJson(object.getAlias());

        databaseManager.addObject(objectIdString, name, description, aliasJson);

       return Response.ok().build();
    }
    
    /**
     *
     * Il metodo REST permette, attraverso l'inserimento dell'id dell'oggetto nel path della richiesta http,
     * di restituire l'oggetto associato all'id sopracitato.
     * 
     * @param objectIdString Id dell'oggetto di cui si vogliono recuperare i dati.
     * @return Response contenitore dell'oggetto (in formato Json) recuperato dal db.
     * @throws SQLException
     * 
     * 
     */
    @GET
    @Path("/{objectId}")
    @Produces(MediaType.APPLICATION_JSON)   
    public Response getObject(@PathParam("objectId") String objectIdString) throws SQLException
    {
        String jsonObject = databaseManager.getObjectById(objectIdString);
        return Response.ok(jsonObject, MediaType.APPLICATION_JSON).build();
    }
    
    
    
    
    
    
      
}
