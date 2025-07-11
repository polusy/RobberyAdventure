/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.effects.SpecialAction;

/**
 *
 * @author utente
 */
public class GameActionResult {
    private String message;
    private SpecialAction specialAction;    

    public GameActionResult(String message, SpecialAction specialAction) {
        this.message = message;
        this.specialAction = specialAction;
    }
    
    public GameActionResult(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SpecialAction getSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(SpecialAction specialAction) {
        this.specialAction = specialAction;
    }
    
    
}
