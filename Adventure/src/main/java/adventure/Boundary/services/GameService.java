/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary.services;

import adventure.Boundary.DatabaseManager;
import adventure.Entity.objects.AdvObject;
import adventure.utilities.DatabaseGameTable;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.RobberyAdventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import java.util.Set;

/**
 *
 * @author Paolo
 */

@Path("game")
public class GameService {
    
    private final static DatabaseManager databaseManager;
    
    static{
        try{
            databaseManager = new DatabaseManager();
        }catch(SQLException exception){
            throw new ExceptionInInitializerError();
        }
    }
    
    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGameSavingRequest(String jsonDatabaseGameTable) throws SQLException {

        Gson gson = new Gson();
        DatabaseGameTable databaseGameTable = gson.fromJson(jsonDatabaseGameTable, DatabaseGameTable.class);
        String gameId = databaseGameTable.getId();
        GameDescription gameDescription = databaseGameTable.getGameDescription();

        String jsonGameDescription = null;

        if (gameDescription instanceof RobberyAdventure)
            jsonGameDescription = gson.toJson((RobberyAdventure) gameDescription);

        boolean gameAdded = databaseManager.addGame(gameId, jsonGameDescription);

        return Response.ok(gson.toJson(gameAdded), MediaType.APPLICATION_JSON).build();
    }
    
    
    @GET
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameSavingRequest(@PathParam("gameId") String gameId) throws SQLException{
        
        String jsonGameDescription = databaseManager.getGameById(gameId);
        return Response.ok(jsonGameDescription, MediaType.APPLICATION_JSON).build();
        
    }
    
    
    @GET
    @Path("/allnames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGameSavingRequest() throws SQLException{
        
        Gson gson = new Gson();
        
        Set<String> gameIds = databaseManager.getAllGameNames();
        
        Type type = new TypeToken<Set<String>>(){}.getType();
        
        String jsonAllGameNames = gson.toJson(gameIds, type);
        return Response.ok(jsonAllGameNames, MediaType.APPLICATION_JSON).build();
        
    }
    
    
    
    
    
}
