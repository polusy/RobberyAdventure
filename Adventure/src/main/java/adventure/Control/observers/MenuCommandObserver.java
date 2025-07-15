/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.EndCommandGUI;
import adventure.Boundary.GUI.MenuCommandGUI;
import adventure.Boundary.ClientManager;
import adventure.Boundary.DatabaseManager;
import adventure.Boundary.GUI.SaveCommandGUI;
import adventure.Boundary.ServerManager;
import adventure.Boundary.services.GameService;


import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.Entity.types.RobberyAdventure;


import adventure.exceptions.EndGameException;
import adventure.exceptions.NotValidSentenceException;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;


/**
 *
 * @author Paolo
 */
public class MenuCommandObserver implements TechnicalObserver {
    
    /**
     *
     * @param game
     * @param parserOutput
     * @param message
     * @throws NotValidSentenceException
     * @throws EndGameException
     */
    @Override
        public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException, EndGameException {
        
        if (!parserOutput.getObjects().isEmpty()|| parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        JFrame baseFrame = FrameCreator.createBaseFrame();

        MenuCommandGUI menuCommandGUI = new MenuCommandGUI(new ClientManager("http://localhost:8081"), (RobberyAdventure)game, baseFrame, true);
        menuCommandGUI.setVisible(true);
        
        if (menuCommandGUI.isGameEnded())
            throw new EndGameException();
        else if (menuCommandGUI.getRescuedRobberyAdventure()!= null){
            game = menuCommandGUI.getRescuedRobberyAdventure();
        }
    }
        
        
        
          
    
}
