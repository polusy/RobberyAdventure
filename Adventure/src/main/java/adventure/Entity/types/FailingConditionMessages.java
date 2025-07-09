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
 */
public class FailingConditionMessages {
    
    Map<ObjectId, String> missingNecessaryObjectsMessages;
    String failingInventoryConditionMessage;
    Map<ObjectId, Map<PropertyType,String>> failingObjectsConditionsMessages;
    Map<ObjectId, String> failingVisibilityConditionMessages;

    public FailingConditionMessages(Map<ObjectId, String> missingNecessaryObjectsMessages, String failingInventoryConditionMessage, Map<ObjectId, Map<PropertyType, String>> failingObjectsConditionsMessages, Map<ObjectId, String> failingVisibilityConditionMessages) {
        this.missingNecessaryObjectsMessages = missingNecessaryObjectsMessages;
        this.failingInventoryConditionMessage = failingInventoryConditionMessage;
        this.failingObjectsConditionsMessages = failingObjectsConditionsMessages;
        this.failingVisibilityConditionMessages = failingVisibilityConditionMessages;
    }
    
    
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
    

    public Map<ObjectId, String> getMissingNecessaryObjectsMessages() {
        return missingNecessaryObjectsMessages;
    }

    public String getFailingInventoryConditionMessage() {
        return failingInventoryConditionMessage;
    }

    public Map<ObjectId, Map<PropertyType, String>> getFailingObjectsConditionsMessages() {
        return failingObjectsConditionsMessages;
    }

    public Map<ObjectId, String> getFailingVisibilityConditionMessages() {
        return failingVisibilityConditionMessages;
    }
    
    
    
    
    
}
