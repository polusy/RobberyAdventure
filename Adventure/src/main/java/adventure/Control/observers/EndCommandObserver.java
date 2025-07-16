/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.FrameCreator;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.EndGameException;
import adventure.Boundary.GUI.EndCommandGUI;

import javax.swing.JFrame;



/**
 *
 *
 * 
 * La classe rappresenta il gestore del comando di fine gioco.
 * 
 * @author Paolo
 */
public class EndCommandObserver implements TechnicalObserver {

    /**
     *
     * Il metodo verifica che la frase sia sintatticamente corretta, e effettua una chiamata alla classe GUI per
     * gestire la volontà di abbandono dell'utente.
     * 
     * Se l'utente conferma l'abbandono viene lanciata un'eccezione di fine gioco, altrimenti nulla.
     * 
     * @param game Descrizione di gioco corrente
     * 
     * @param parserOutput Rappresenta l'output del parser al momento del parsing della stringa inserita nell'input stream
     * dell'utente attraverso CLI.
     * 
     * @param message
     * @throws NotValidSentenceException Se l'utente inserisce una frase non corretta (per far finire il gioco).
     * @throws EndGameException Se l'utente inserisce un corretto comando di fine gioco e conferma la volontà di uscire dal gioco.
     * 
     */
    @Override
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException, EndGameException {
        
        //verifico che il parserOutput non contenga altri tipi di elementi parsati (stanza, oggetti...)
        if (!parserOutput.getObjects().isEmpty() || parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        JFrame baseFrame = FrameCreator.createBaseFrame();
        
        EndCommandGUI endCommandGUI = new EndCommandGUI(baseFrame, true);
        endCommandGUI.setVisible(true);
        
        //se l'utente vuole abbandonare
       if (endCommandGUI.isGameEnded())
           throw new EndGameException();
        
    }   
}
