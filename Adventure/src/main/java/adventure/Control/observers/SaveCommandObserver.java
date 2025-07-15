/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.FrameCreator;
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
import javax.swing.JFrame;

/**
 *
 * @author Paolo
 * 
 * La classe implementa l'interfaccia dei gestori tecnici di gioco. 
 * Gestisce il comando del gioco che permette di salvare i progressi di gioco.
 */
public class SaveCommandObserver implements TechnicalObserver{
    
    /**
     *
     * @param game Descrizione corrente del gioco.
     * @param parserOutput output del parser da analizzare per eseguire l'update.
     * @param message Messaggio possibilmente da avvalorare nel metodo di update.
     * @throws NotValidSentenceException Se l'utente scrive una frase non corretta contestualmente al comando inserito.
     * 
     * 
     * Il metodo controlla che il parserOutput contenga solo il comando di tipo Save e che non abbia parsato oggetti
     * o stanze, inoltre invoca la GUI per il salvataggio dei progressi.
     */
    @Override
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException {
        
        if (!parserOutput.getObjects().isEmpty() || parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        
        JFrame baseFrame = FrameCreator.createBaseFrame();
        
        SaveCommandGUI saveCommandGUI = new SaveCommandGUI(new ClientManager("http://localhost:8081"),(RobberyAdventure)game, baseFrame, true);
        saveCommandGUI.setVisible(true);
        
    }   
    
    
    
}
