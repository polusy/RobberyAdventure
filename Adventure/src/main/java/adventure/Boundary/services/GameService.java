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
 * 
 * La classe rappresenta la configurazione REST del server per permettere richieste http
 * per quanto riguarda i servizi di salvataggio di progressi di gioco e recuperi di questi ultimi.
 */

@Path("game")
public class GameService {
    
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
     * @param jsonDatabaseGameTable rappresentazione json della tupla (id, gameDescription).
     * @return Restituisce il risultato dell'operazione eseguita.
     * @throws SQLException
     * 
     * Il metodo si occupa di convertire la tupla serializzata (json) in un oggetto di classe opportuna,
     * di recuperarne i valori effettivi e di effetuare una richiesta al dbManager per salvare i progressi di gioco raggiunti.
     */
    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGameSaving(String jsonDatabaseGameTable) throws SQLException {
        
            Gson gson = new Gson();
            DatabaseGameTable databaseGameTable = gson.fromJson(jsonDatabaseGameTable, DatabaseGameTable.class);
            String gameId = databaseGameTable.getId();
            RobberyAdventure robberyAdventure = databaseGameTable.getRobberyAdventure();

            String jsonGameDescription = null;

                jsonGameDescription = gson.toJson(robberyAdventure, RobberyAdventure.class);

            Boolean gameAdded = databaseManager.addGame(gameId, jsonGameDescription);

            return Response.ok(gson.toJson(gameAdded), MediaType.APPLICATION_JSON).build();
    }
    
    /**
     *
     * @param gameId Id del gioco di cui si vuole recuperare il salvataggio.
     * @return Response con il salvataggio specifico richiesto (serializzato in json).
     * @throws SQLException
     * 
     * Il metodo restituisce, effettuando una chiamata al db, il salvataggio di gioco associato
     * all'id passato nel path della richiesta http al server.
     */
    @GET
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameSaving(@PathParam("gameId") String gameId) throws SQLException{
        
        String jsonGameDescription = databaseManager.getGameById(gameId);
        return Response.ok(jsonGameDescription, MediaType.APPLICATION_JSON).build();
        
    }
    
    /**
     *
     * @return Response rappresentante la serializzazione del Set di nomi
     * dei salvataggi del gioco (in formato Json).
     * 
     * @throws SQLException
     * 
     * Il metodo restituisce, attraverso una query del dbManager,
     * tutti i nomi dei salvataggi di gioco presenti nel db.
     */
    @GET
    @Path("/allnames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGameSaving() throws SQLException{
        
        Gson gson = new Gson();
        
        Set<String> gameIds = databaseManager.getAllGameNames();
        
        Type type = new TypeToken<Set<String>>(){}.getType();
        
        String jsonAllGameNames = gson.toJson(gameIds, type);
        return Response.ok(jsonAllGameNames, MediaType.APPLICATION_JSON).build();
        
    }
    
    
    
    
    
}
