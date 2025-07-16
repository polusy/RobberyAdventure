/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.conditions.CompleteCondition;
import adventure.exceptions.InconsistentInitializationException;
import java.util.NoSuchElementException;
import adventure.Entity.conditions.InventoryCondition;
import adventure.identifiers.ObjectId;
import adventure.identifiers.PropertyType;


import java.util.Map;


/**
 *
 * 
 * 
 * La classe Rappresenta la specifica di un azione sul gioco, quindi ne conserva le condizioni di applicabilità,
 * i risultati associati al superamento delle condizioni e i messaggi da mostrare in caso di fallimento.
 * 
 * @author Paolo
 */
public class GameActionSpecification {
    /**
     * Rappresenta l'insieme delle condizioni sull'inventario e sulle proprietà degli oggetti che devono
     * essere soddisfatte affinché possano essere eseguiti i contenuti della {@link PassingConditionResult}.
     */
    private final CompleteCondition completeCondition;
    
    /**
     * Messaggi per il fallimento di ognuna delle condizioni non soddisfatte all'interno di completeCondition.
     */
    private final FailingConditionMessages failingConditionMessages;
    
    /**
     * Effetti e azioni speciali da applicare in caso di superamento delle condizioni di applicabilità del comando.
     */
    private final PassingConditionResult passingConditionResult;

    /**
     *
     * Il costruttore si assicura che l'inizializzazione della {@link CompleteCondition} 
     * sia coerente con l'inizializzazione dei messaggi di fallimento sulle stesse.
     * 
     * @param completeCondition
     * @param failingConditionMessages
     * @param passingConditionResult
     * @throws InconsistentInitializationException
     * 
     */
    public GameActionSpecification(CompleteCondition completeCondition, FailingConditionMessages failingConditionMessages, PassingConditionResult passingConditionResult) throws InconsistentInitializationException{
        
        
        //controllo su condizioni dell'inventario
        if (failingConditionMessages.getMissingNecessaryObjectsMessages() != null){

            if (completeCondition.getInventoryConditionOptions().size() != 1)
                    throw new InconsistentInitializationException();
            
       
            for (Map.Entry<ObjectId, String> missingNecessaryObjectMessage : failingConditionMessages.getMissingNecessaryObjectsMessages().entrySet()){
                
                if (!completeCondition.getInventoryConditionOptions().get(0).contains(missingNecessaryObjectMessage.getKey()))
                    throw new InconsistentInitializationException();
                }
                
            }
        
        if (failingConditionMessages.getFailingInventoryConditionMessage() != null){
            
            if (completeCondition.getInventoryConditionOptions() == null)
                throw new InconsistentInitializationException();
                
            if (completeCondition.getInventoryConditionOptions().size() <= 1)
                throw new InconsistentInitializationException();

        }
        
        if (failingConditionMessages.getFailingObjectsConditionsMessages() != null){
        
            for(Map.Entry<ObjectId, Map<PropertyType, String>> failingConditionMessage : failingConditionMessages.getFailingObjectsConditionsMessages().entrySet()){

               if (!completeCondition.getObjectsConditions().keySet().contains(failingConditionMessage.getKey()))
                   throw new InconsistentInitializationException();
            }
        }

        
        if (failingConditionMessages.getFailingVisibilityConditionMessages() != null){ 
           
            for (Map.Entry<ObjectId, String> failingVisibilityConditionMessage : failingConditionMessages.getFailingVisibilityConditionMessages().entrySet()){

                if(!completeCondition.getObjectsConditions().keySet().contains(failingVisibilityConditionMessage.getKey()))
                    throw new InconsistentInitializationException();

            }
        }
           
        
        this.completeCondition = completeCondition;
        this.failingConditionMessages = failingConditionMessages;
        this.passingConditionResult = passingConditionResult;
    }
    
    /**
     *
     * @return
     */
    public CompleteCondition getCompleteCondition() {
        return completeCondition;
    }

    /**
     *
     * @return
     */
    public FailingConditionMessages getFailingConditionMessages() {
        return failingConditionMessages;
    }

    /**
     *
     * @return
     */
    public PassingConditionResult getPassingConditionResult() {
        return passingConditionResult;
    }
    
}
