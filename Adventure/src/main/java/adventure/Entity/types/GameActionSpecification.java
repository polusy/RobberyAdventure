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
 * @author Paolo
 */
public class GameActionSpecification {
    
    private final CompleteCondition completeCondition;
    private final FailingConditionMessages failingConditionMessages;
    private final PassingConditionResult passingConditionResult;

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
    
    
    
    
    
    
    
    
    
    
    


    public CompleteCondition getCompleteCondition() {
        return completeCondition;
    }

    public FailingConditionMessages getFailingConditionMessages() {
        return failingConditionMessages;
    }

    public PassingConditionResult getPassingConditionResult() {
        return passingConditionResult;
    }
    
}
