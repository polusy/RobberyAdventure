/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adventure.Control.observers;

import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.EndGameException;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;

/**
 *
 * @author utente
 */
public interface TechnicalObserver {
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) 
        throws NotValidSentenceException, EndGameException ;
}
