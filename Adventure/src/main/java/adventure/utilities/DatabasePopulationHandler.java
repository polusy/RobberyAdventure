/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;


import adventure.Boundary.ClientManager;
import adventure.Boundary.DatabaseManager;
import adventure.Boundary.ServerManager;
import adventure.Boundary.services.ObjectService;
import adventure.Entity.objects.AdvObject;
import adventure.identifiers.ObjectId;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Gestore del popolamento della base di dati con le tuple che rappresentano tutti gli oggetti del gioco, interattivi
 * e non interattivi
 *
 * @author Paolo
 */
public class DatabasePopulationHandler {
    
    /** Metodo main che inizializza i propri attributi {@link ServerManager} e {@link ClientManager}, ne avvia il 
     * funzionamento, e popola la base di dati con le tuple contenenti le informazioni di base di tutti gli oggetti
     * del gioco
     *
     * @param args
     */
    public static void main(String[] args){
    
    ServerManager serverManager = new ServerManager("http://localhost/", 8080, ObjectService.class);
    
        try{
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.createObjectsTable();
            
        }catch(SQLException exception){
            throw new ExceptionInInitializerError();
        }
        
        serverManager.run();
        ClientManager clientManager = new ClientManager("http://localhost:8080");
        clientManager.populate();
    }
    
    
    
    
    
}
