/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;


import adventure.Boundary.ClientManager;
import adventure.Boundary.DatabaseManager;
import adventure.Boundary.ServerManager;
import adventure.Boundary.services.ObjectService;
import java.sql.SQLException;

/**
 *
 * @author Paolo
 */
public class DatabasePopulationHandler {
    
    public static void main(String[] args){
    
    ServerManager serverManager = new ServerManager("http://localhost/", 4040, ObjectService.class);
        try{
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.createObjectsTable();
            
        }catch(SQLException exception){
            throw new ExceptionInInitializerError();
        }
        
        serverManager.run();
        ClientManager clientManager = new ClientManager("http://localhost/4040");
        clientManager.populate();
        
    }
    
    
    
    
    
}
