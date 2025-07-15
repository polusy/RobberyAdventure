/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.exceptions.PasswordGuessedException;
import adventure.exceptions.EndGameException;


/**
 *
 * @author Paolo
 */
public interface SpecialAction {
    
    /**
     *
     * @throws PasswordGuessedException
     * @throws EndGameException
     */
    public void execute() throws PasswordGuessedException, EndGameException;
    
}
