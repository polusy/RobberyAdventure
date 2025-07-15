/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.effects.GameEffect;

/**
 *
 * @author Paolo
 */
public class PassingConditionResult {
    
    private GameEffect gameEffect;
    private String passingConditionMessage;

    /**
     *
     * @param gameEffect
     * @param passingConditionMessage
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
