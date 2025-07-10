/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.EndGameException;



/**
 *
 * @author utente
 */
public class EndCommandObserver implements TechnicalObserver {
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException, EndGameException {
        
        if (parserOutput.getObjects() != null || parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        throw new EndGameException();
    }   
}
