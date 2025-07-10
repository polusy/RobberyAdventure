/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adventure;

import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.EndGameException;

/**
 *
 * @author utente
 */
public interface TechnicalObserver {
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) 
            throws NotValidSentenceException, EndGameException ;
}
