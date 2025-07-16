/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import java.util.Map;
import adventure.identifiers.ObjectId;
import adventure.identifiers.PropertyType;

/**
 *
 * @author Paolo
 * 
 * La classe conserva i messaggi di fallimento per un qualsiasi possibile fallimento della valutazione della completeCondition.
 * 
 * 
 * 
 */
public class FailingConditionMessages {
    
    /**
     * Rappresenta una mappa in cui per ogni {@link ObjectId} viene associata un message. Il message rappresenta
     * il messaggio di fallimento riguardante l'assenza di {@code ObjectId} necessario nell'inventario per l'esecuzione di
     * quella specifica azione.
     */
    Map<ObjectId, String> missingNecessaryObjectsMessages;
    
    /**
     * Rappresenta il messaggio per il fallimento su tutte le possibili condizioni valide dell'inventario.
     */
    String failingInventoryConditionMessage;
    
    /**
     * E' una mappa che associa al {@link ObjectId} di cui è fallita la valutazione di condizione, una Map per cui
     * per ognuna delle {@link PropertyType} fallite è associato uno specifico messaggio di fallimento.
     */
    Map<ObjectId, Map<PropertyType,String>> failingObjectsConditionsMessages;
    
    /**
     * E' una mappa che associa ad ogni {@link ObjectId} che non ha passato la valutazione sulla condizione di visibilità,
     * una stringa con il messaggio specifico da mostrare all'utente. 
     */
    Map<ObjectId, String> failingVisibilityConditionMessages;

    /**
     *
     * @param missingNecessaryObjectsMessages
     * @param failingInventoryConditionMessage
     * @param failingObjectsConditionsMessages
     * @param failingVisibilityConditionMessages
     */
    public FailingConditionMessages(Map<ObjectId, String> missingNecessaryObjectsMessages, String failingInventoryConditionMessage, Map<ObjectId, Map<PropertyType, String>> failingObjectsConditionsMessages, Map<ObjectId, String> failingVisibilityConditionMessages) {
        this.missingNecessaryObjectsMessages = missingNecessaryObjectsMessages;
        this.failingInventoryConditionMessage = failingInventoryConditionMessage;
        this.failingObjectsConditionsMessages = failingObjectsConditionsMessages;
        this.failingVisibilityConditionMessages = failingVisibilityConditionMessages;
    }
    
    /**
     *
     * @param conditionEvaluationResult
     * @return
     */
    public String getMessage(ConditionEvaluationResult conditionEvaluationResult){
        String failingMessage = null;
        
        if (conditionEvaluationResult.getFailedVisibilityObject() != null){
            failingMessage = failingVisibilityConditionMessages.get(conditionEvaluationResult.getFailedVisibilityObject());
        }
        else if (!conditionEvaluationResult.hasInventoryConditionPassed()){
		if (conditionEvaluationResult.getMissingNecessaryObject() == null)
                    failingMessage = failingInventoryConditionMessage;
		else
                    failingMessage = missingNecessaryObjectsMessages.get(conditionEvaluationResult.getMissingNecessaryObject());
	}
	else if (conditionEvaluationResult.getObjectFailedConditionReference() != null){
		ObjectId objectId = conditionEvaluationResult.getObjectFailedConditionReference().getObjectId();
		PropertyType propertyType = conditionEvaluationResult.getObjectFailedConditionReference().getPropertyType();

		failingMessage = failingObjectsConditionsMessages.get(objectId).get(propertyType);
	}
        
        return failingMessage;
    }
    
    /**
     *
     * @return
     */
    public Map<ObjectId, String> getMissingNecessaryObjectsMessages() {
        return missingNecessaryObjectsMessages;
    }

    /**
     *
     * @return
     */
    public String getFailingInventoryConditionMessage() {
        return failingInventoryConditionMessage;
    }

    /**
     *
     * @return
     */
    public Map<ObjectId, Map<PropertyType, String>> getFailingObjectsConditionsMessages() {
        return failingObjectsConditionsMessages;
    }

    /**
     *
     * @return
     */
    public Map<ObjectId, String> getFailingVisibilityConditionMessages() {
        return failingVisibilityConditionMessages;
    }
    
    
    
    
    
}
