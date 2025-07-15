/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.FrameCreator;
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
 * La classe implementa l'interfaccia dei gestori tecnici di gioco. 
 * Gestisce il comando del gioco che permette di visualizzare il menu di gioco.
 */
public class MenuCommandObserver implements TechnicalObserver {
    
    /**
     *
     * @param game Descrizione di gioco corrente
     * 
     * @param parserOutput Rappresenta l'output del parser al momento del parsing della stringa inserita nell'input stream
     * dell'utente attraverso CLI.
     * 
     * @param message Messaggio da avvalorare possibilmente nel corpo di update.
     * 
     * @throws NotValidSentenceException Se l'utente inserisce attraverso CLI un frase integralmente non coerente con il primo comando di Menu inserito.
     * @throws EndGameException Se l'utente, tramite menu di gioco, decide di interrompere la corrente sessione di gioco.
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
