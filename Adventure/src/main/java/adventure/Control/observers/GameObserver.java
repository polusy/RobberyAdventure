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
 * @author Paolo
 */
public interface GameObserver {
    
    public abstract void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out)
            throws NotValidSentenceException;
    
}
