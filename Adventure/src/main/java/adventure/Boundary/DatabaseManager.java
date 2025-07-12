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
 * @author utente
 */
public class DatabaseManager {
    private Connection connection;
    
    public DatabaseManager() throws SQLException{
        
        String url = "jdbc:h2:./database/dbprova";
        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
    }

    public void createObjectsTable() throws SQLException{

	String objectsTableCreation = "CREATE TABLE IF NOT EXISTS Objects ( ObjectId VARCHAR(40) PRIMARY KEY, Name VARCHAR(50), Description TEXT,  Alias TEXT)";
	Statement statement = connection.createStatement();
	statement.executeUpdate(objectsTableCreation);
	statement.close();
    }
    
    public void createGamesTable() throws SQLException{
        String objectsTableCreation = "CREATE TABLE IF NOT EXISTS Games ( GameId VARCHAR(40) PRIMARY KEY, GameDescription TEXT)";
	Statement statement = connection.createStatement();
	statement.executeUpdate(objectsTableCreation);
	statement.close();
    }

    public Boolean addGame(String gameId, String jsonGameDescription) throws SQLException{

        Boolean gameSavingAlreadyExist = false;
        String objectInsertionQuery = "INSERT INTO Objects VALUES (?, ?)" ;

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
        
        
    public String getGameById(String gameId) throws SQLException{
        
	Gson gson  = new Gson();
	String objectSelectionQuery = "SELECT * FROM Games WHERE GameId = ?";
	PreparedStatement statement = connection.prepareStatement(objectSelectionQuery);
	statement.setString(1, gameId);
	ResultSet resultSet = statement.executeQuery();
	String gameDescription;
	
	if (resultSet.next())
	{
            gameDescription = resultSet.getString(2);
	}
	else{
            throw new SQLException();
	}
        
        
        statement.close();
        
        return gameDescription;
    }
    
    public Set<String> getAllGameNames() throws SQLException{
        
        String objectSelectionQuery = "SELECT GameId FROM Games";
        PreparedStatement statement = connection.prepareStatement(objectSelectionQuery);
	ResultSet resultSet = statement.executeQuery();
	Set<String> gameNames = new HashSet<>();
        
        if (resultSet.next()){
            gameNames.add(resultSet.getString(1));
        }
        
        statement.close();
        return gameNames;
    }
	
    
    

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
