/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.SaveCommandGUI;
import adventure.Boundary.ClientManager;
import adventure.Boundary.ServerManager;
import adventure.Boundary.DatabaseManager;
import adventure.Boundary.services.GameService;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.RobberyAdventure;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.EndGameException;
import adventure.exceptions.NotValidSentenceException;


import java.sql.SQLException;

/**
 *
 * @author Paolo
 */
public class SaveCommandObserver implements TechnicalObserver{
    
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException {
        
        if (parserOutput.getObjects() != null || parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        SaveCommandGUI saveCommandGUI = new SaveCommandGUI(new ClientManager("http://localhost:8081"),game, null, true);
        saveCommandGUI.setVisible(true);
        
    }   
    
    
    
    public static void main(String[] args) throws SQLException{
        
        ServerManager server = new ServerManager("http://localhost/", 8081, GameService.class);
        server.run();
        DatabaseManager dbmanager = new DatabaseManager();
        ClientManager clientManager = new ClientManager("http://localhost:8081");
        SaveCommandGUI saveCommandGUI = new SaveCommandGUI(clientManager, new RobberyAdventure(), null, true);
        saveCommandGUI.setVisible(true);
        
        System.out.println(clientManager.getAllGamesNamesRequest());
    }
    
    
}
