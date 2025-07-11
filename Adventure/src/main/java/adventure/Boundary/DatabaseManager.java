/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary;

import java.util.Set;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import adventure.Entity.objects.AdvObject;
import adventure.identifiers.ObjectId;


/**
 *
 * @author utente
 */
public class DatabaseManager {
    private Connection connection;
    
    public DatabaseManager() throws SQLException{
        
        Path projectRoot = Paths.get("").toAbsolutePath();
        Path dbPath = projectRoot.resolve("database/db"); 
        
        String url = "jdbc:h2:" + dbPath.toString();
        connection = DriverManager.getConnection(url);
    }

    public void createObjectsTable() throws SQLException{

	String objectsTableCreation = "CREATE TABLE IF NOT EXISTS Objects ( ObjectId VARCHAR(40) PRIMARY KEY, Name VARCHAR(50), Description TEXT,  Alias TEXT)";
	Statement statement = connection.createStatement();
	statement.executeUpdate(objectsTableCreation);
	statement.close();
    }

    public void addObject(String objectIdString, String name, String description, String jsonAlias) throws SQLException{
	String objectInsertionQuery = "INSERT INTO Objects VALUES (? ? ? ?)" ;

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
