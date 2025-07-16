/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.exceptions.PasswordGuessedException;
import adventure.exceptions.EndGameException;


/**
 *
 * 
 * 
 * 
 * Questa interfaccia funzionale è stata pensata per permettere ad ogni gameEffect di conservarne
 * un attributo, avvalorato con una espressione lambda al momento dell'init. del GameEffect negli oggetti.
 * 
 * In generale questa interfaccia rappresenta un metodo speciale che deve essere eseguito come effetto (dopo aver
 * superato la completeCondition associata alla specifica proprietà di uno specifico oggetto).
 * 
 * @author Paolo
 */
public interface SpecialAction {
    
    /**
     *
     * @throws PasswordGuessedException Se l'azione speciale è l'apertura della cassaforte (che genera l'eccezione prima citata).
     * @throws EndGameException Se l'azione speciale è di terminazione della partita.
     */
    public void execute() throws PasswordGuessedException, EndGameException;
    
}
