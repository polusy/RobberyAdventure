/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary;

import java.util.Set;
import java.util.HashSet;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import adventure.Entity.objects.AdvObject;
import adventure.Entity.types.RobberyAdventure;
import adventure.identifiers.ObjectId;


/**
 *
 * @author Paolo
 * 
 * La classe si occupa di gestire il database, di conservarne la connessione, di effettuare creazioni di tabelle,
 * inserimento di dati di oggetti e progressi di gioco, e infine di eseguire queries su di esso.
 */
public class DatabaseManager {
    /**
     * connessione al db specifico.
     */
    private Connection connection;
    
    /**
     *
     * @throws SQLException
     * 
     * Il metodo costruisce la connessione al db specifico, conservato nella cartella database.
     */
    public DatabaseManager() throws SQLException{
        
        String url = "jdbc:h2:./database/db";
        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
    }

    /**
     *
     * @throws SQLException
     * 
     * Il metodo si occupa di creare la tabella che conterrà le tuple rappresentanti gli oggetti dell'adventure.
     */
    public void createObjectsTable() throws SQLException{

	String objectsTableCreation = "CREATE TABLE IF NOT EXISTS Objects ( ObjectId VARCHAR(40) PRIMARY KEY, Name VARCHAR(50), Description TEXT,  Alias TEXT)";
	Statement statement = connection.createStatement();
	statement.executeUpdate(objectsTableCreation);
	statement.close();
    }
    
    /**
     *
     * @throws SQLException
     * 
     * Il metodo si occupa di creare la tabella che conterrà le tuple rappresentanti il salvataggio specifico dei progressi di gioco.
     */
    public void createGamesTable() throws SQLException{
        String objectsTableCreation = "CREATE TABLE IF NOT EXISTS Games ( GameId VARCHAR(40) PRIMARY KEY, GameDescription TEXT)";
	Statement statement = connection.createStatement();
	statement.executeUpdate(objectsTableCreation);
	statement.close();
    }

    /**
     *
     * @param gameId Id dei progressi di gioco da salvare
     * @param jsonGameDescription descrizione json dei progressi di gioco da salvare.
     * 
     * @return Boolean rappresentante il risultato positivo (true) o negativo (false) dell'aggiunta
     * del salvataggio dei progressi di gioco al db.
     * 
     * @throws SQLException
     * 
     * 
     * Il metodo di occupa di aggiungere nella tabella Games del db la tupla con l'identificativo del salvataggio
     * e il valore ad esso associato.
     */
    public Boolean addGame(String gameId, String jsonGameDescription) throws SQLException{

        Boolean gameSavingAlreadyExist = false;
        String objectInsertionQuery = "INSERT INTO Games VALUES (?, ?)" ;

        PreparedStatement statement = connection.prepareStatement(objectInsertionQuery);
        statement.setString(1, gameId);
        statement.setString(2, jsonGameDescription);
        try{
            statement.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException exception){
            gameSavingAlreadyExist = true;
        }
        statement.close();

        return gameSavingAlreadyExist;
    }
        
    /**
     *
     * @param gameId Id del salvataggio da recuperare
     * @return String Json rappresentante salvataggio recuperato
     * @throws SQLException
     * 
     * 
     * Il metodo si occupa di eseguire una query sul db per ottenere la gameDescription
     * associata all'id passato come param. al metodo.
     */
    public String getGameById(String gameId) throws SQLException{
        
	Gson gson  = new Gson();
	String objectSelectionQuery = "SELECT * FROM Games WHERE GameId = ?";
	PreparedStatement statement = connection.prepareStatement(objectSelectionQuery);
	statement.setString(1, gameId);
	ResultSet resultSet = statement.executeQuery();
	String jsonGameDescription;
	
	if (resultSet.next())
	{
            jsonGameDescription = resultSet.getString(2);
	}
	else{
            throw new SQLException();
	}
        
        
        statement.close();
        
        return jsonGameDescription;
    }
    
    /**
     *
     * @return Set di stringhe rappresentante l'insieme di tutti i nomi di salvataggi di gioco
     * all'interno del db.
     * 
     * @throws SQLException
     * 
     * Il metodo si occupa di recuperare dal db, attraverso una query, tutti i nomi dei salvataggi
     * di gioco effettuati.
     */
    public Set<String> getAllGameNames() throws SQLException{
        
        String objectSelectionQuery = "SELECT GameId FROM Games";
        PreparedStatement statement = connection.prepareStatement(objectSelectionQuery);
	ResultSet resultSet = statement.executeQuery();
	Set<String> gameNames = new HashSet<>();
        
        while (resultSet.next()){
            gameNames.add(resultSet.getString(1));
        }
        
        statement.close();
        return gameNames;
    }
	
    /**
     *
     * @param objectIdString Id dell'oggetto (AdvObject) da aggiungere al db.
     * @param name Nome dell'oggetto (AdvObject) da aggiungere al db.
     * @param description Descrizione dell'oggetto (AdvObject) da aggiungere al db.
     * @param jsonAlias Set serializzato in Json, rappresentante gli alias dell'oggetto (AdvObject) da aggiungere al db.
     * @throws SQLException
     * 
     * Il metodo si occupa di aggiungere l'oggetto AdvObject (descritto dai parametri) al db.
     */
    public void addObject(String objectIdString, String name, String description, String jsonAlias) throws SQLException{
	String objectInsertionQuery = "INSERT INTO Objects VALUES (?, ?, ?, ?)" ;

	PreparedStatement statement = connection.prepareStatement(objectInsertionQuery);
	statement.setString(1, objectIdString);
	statement.setString(2, name);
	statement.setString(3, description);
	statement.setString(4, jsonAlias);
	statement.executeUpdate();
	statement.close();
    }

    /**
     *
     * @param objectIdString Id dell'oggetto (AdvObject) che si vuole recuperare dal db.
     * @return JsonString rappresentante serializzazione dell'oggetto recuperato dal db.
     * @throws SQLException
     * 
     * Il metodo si occupa di recuperare un oggetto (AdvObject) dal db, per mezzo del suo id
     * e di convertirlo in una stringa json attraverso serializzazione.
     */
    public String getObjectById(String objectIdString) throws SQLException{
	Gson gson  = new Gson();
	String objectSelectionQuery = "SELECT Name, Description, Alias FROM Objects WHERE ObjectId = ?";
	PreparedStatement statement = connection.prepareStatement(objectSelectionQuery);
	statement.setString(1, objectIdString);
	ResultSet resultSet = statement.executeQuery();
	String name;
	String description;
	Set<String> alias;
	
	if (resultSet.next())
	{
            name = resultSet.getString(1);
            description = resultSet.getString(2);
            
            Type type = new TypeToken<Set<String>>(){}.getType();
            alias = gson.fromJson(resultSet.getString(3), type);
	}
	else{
            throw new SQLException();
	}
	
	return gson.toJson(new AdvObject(Enum.valueOf(ObjectId.class, objectIdString), name, description, alias));
    }
	
}
