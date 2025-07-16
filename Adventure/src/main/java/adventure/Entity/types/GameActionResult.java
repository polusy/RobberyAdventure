/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.effects.SpecialAction;

/**
 *
 * 
 * 
 * La classe incapsula il risultato di un'azione sul gioco (messaggio da mostrare all'utente e azione speciale da eseguire).
 * 
 * @author Paolo
 */
public class GameActionResult {
    /**
     * messaggio da mostrare all'utente dopo aver valutato le specifiche condizioni di applicabilità
     * del comando.
     */
    private String message;
    
    /**
     * l'azione speciale da eseguire, successivamente alla valutazione della condizione di applicabilità.
     */
    private SpecialAction specialAction;    

    /**
     *
     * @param message
     * @param specialAction
     */
    public GameActionResult(String message, SpecialAction specialAction) {
        this.message = message;
        this.specialAction = specialAction;
    }
    
    /**
     *
     * @param message
     */
    public GameActionResult(String message){
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public SpecialAction getSpecialAction() {
        return specialAction;
    }

    /**
     *
     * @param specialAction
     */
    public void setSpecialAction(SpecialAction specialAction) {
        this.specialAction = specialAction;
    }
    
    
}
