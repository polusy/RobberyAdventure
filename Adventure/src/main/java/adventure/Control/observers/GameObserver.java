/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import java.io.PrintStream;

/**
 *
 * 
 * 
 * Interfaccia rappresentante qualsiasi observer del gioco
 * 
 * @author Paolo
 */
public interface GameObserver {
    
    /**
     *
     * Il metodo esegue un update sul gioco, relativamente all'output del parser nella corrente interazione CLI,
     * eseguendo possibilmente un print su un generico stream di output, inoltre si accerta che il parserOutput
     * sia corretto contestualmente al comando di gioco inserito.
     * 
     * @param gameDescription Descrizione corrente del gioco.
     * @param parserOutput output del parser
     * @param out
     * @throws NotValidSentenceException Se l'utente scrive una frase non corretta contestualmente al comando inserito.
     * 
     * 
     */
    public abstract void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out)
            throws NotValidSentenceException;
    
}
