/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.conditions.CompleteCondition;
import adventure.exceptions.InconsistentInitializationException;
import java.util.NoSuchElementException;
import adventure.Entity.conditions.InventoryCondition;

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
        
        if (completeCondition.getInventoryConditionOptions() != null){
           
            if(completeCondition.getInventoryConditionOptions().size() == 1){
                
                if (failingConditionMessages.getMissingNecessaryObjectsMessages() == null)
                    throw new InconsistentInitializationException();
                
                if (failingConditionMessages.getFailingInventoryConditionMessage() != null)
                    throw new InconsistentInitializationException();
                
                try{
                    InventoryCondition inventoryCondition = completeCondition.getUniqueInventoryCondition();
                    if(inventoryCondition.getNecessaryObjects().containsAll(failingConditionMessages.getMissingNecessaryObjectsMessages().keySet())){
                        throw new InconsistentInitializationException();
                    }
                }catch(NoSuchElementException exception){}
            }
            
           
            else if (completeCondition.getInventoryConditionOptions().size() > 1){
                if(failingConditionMessages.getFailingInventoryConditionMessage() == null)
                    throw new InconsistentInitializationException();
                
                if (failingConditionMessages.getFailingObjectsConditionsMessages() != null){
                    throw new InconsistentInitializationException();
                }
                
            }
        }
        else{
            if (failingConditionMessages.getMissingNecessaryObjectsMessages() != null)
                throw new InconsistentInitializationException();
            
            if(failingConditionMessages.getFailingInventoryConditionMessage() != null)
                throw new InconsistentInitializationException();
        }
        
        
        //controllo su condizioni degli oggetti
        
        if (completeCondition.getObjectsConditions() != null){
            if(!completeCondition.getObjectsConditions().keySet().containsAll(failingConditionMessages.getFailingObjectsConditionsMessages().keySet())){
                throw new InconsistentInitializationException();
            }
            
            if(!completeCondition.getObjectsConditions().keySet().containsAll(failingConditionMessages.getFailingVisibilityConditionMessages().keySet())){
                throw new InconsistentInitializationException();
            }
            
            
        }
        else{
            if (failingConditionMessages.getFailingObjectsConditionsMessages() != null || failingConditionMessages.getFailingVisibilityConditionMessages() != null){
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
