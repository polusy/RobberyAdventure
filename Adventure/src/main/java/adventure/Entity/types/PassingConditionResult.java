/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.effects.GameEffect;

/** Risultato della valutazione della condizione completa rappresentata da un oggetto {@link CompleteCondition} di una specifica
 * di un'azione sul gioco, nel caso in cui tale condizione sia soddisfatta rispetto alla sessione di gioco in corso.
 * 
 * La classe conserva un oggetto {@link GameEffect} che rappresenta l'effetto da applicare alla partita in corso, in seguito
 * alla valutazione con successo della {@link CompleteCondition} relativa alla specifica della corrispondente azione sul gioco.
 * 
 * La classe conserva inoltre il messaggio che notifica l'applicaazione dell'azione sul gioco, di cui lo specifico oggetto
 * GameEffect fa parte
 *
 * @author Paolo
 */
public class PassingConditionResult {
    
    private GameEffect gameEffect;
    private String passingConditionMessage;

    /**
     *
     * @param gameEffect Effetto da applicare alla partita in corso, in seguito alla valutazione con successo della {@link CompleteCondition} corrispondente
     * rispetto alla sessione di gioco in corso
     * @param passingConditionMessage Messaggio che notifica l'applicazione del {@link GameEffect} sul gioco
     */
    public PassingConditionResult(GameEffect gameEffect, String passingConditionMessage) {
        this.gameEffect = gameEffect;
        this.passingConditionMessage = passingConditionMessage;
    }

    /**
     *
     * @return
     */
    public GameEffect getGameEffect() {
        return gameEffect;
    }

    /**
     *
     * @return
     */
    public String getPassingConditionMessage() {
        return passingConditionMessage;
    }

}
