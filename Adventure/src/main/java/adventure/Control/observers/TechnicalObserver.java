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
 * 
 * Interfaccia utilizzata per i gestori dei comandi tecnici di gioco.
 */
public interface TechnicalObserver {

    /**
     * @param game Descrizione corrente del gioco.
     * @param parserOutput output del parser da analizzare per eseguire l'update.
     * 
     * @param message Messaggio possibilmente da avvalorare nel metodo di update.
     * 
     * @throws NotValidSentenceException Se l'utente scrive una frase non corretta contestualmente al comando inserito.
     * 
     * @throws EndGameException Se il gioco termina in seguito a una successiva conferma dell'utente.
     * 
     * Il metodo rappresenta l'update eseguito da un comando tecnico di gioco sulla sessione di gioco.
     */
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) 
        throws NotValidSentenceException, EndGameException ;
}
