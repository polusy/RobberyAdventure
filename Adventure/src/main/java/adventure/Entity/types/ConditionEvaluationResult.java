/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.ObjectId;

/**
 *
 * @author Paolo
 */
public class ConditionEvaluationResult {
    
    boolean conditionPassed;
    boolean inventoryConditionPassed;
    ObjectId missingNecessaryObject;
    ObjectPropertyReference objectFailedConditionReference;
    ObjectId failedVisibilityObject;

    public ConditionEvaluationResult() {
        this.conditionPassed = true;
        this.inventoryConditionPassed = true;
        this.missingNecessaryObject = null;
        this.objectFailedConditionReference = null;
        this.failedVisibilityObject = null;
    }

    public void setObjectFailedConditionReference(ObjectPropertyReference objectFailedConditionReference) {
        this.objectFailedConditionReference = objectFailedConditionReference;
    }

    public void setFailedVisibilityObject(ObjectId failedVisibilityObject) {
        this.failedVisibilityObject = failedVisibilityObject;
    }

    public boolean hasConditionPassed() {
        return conditionPassed;
    }

    public boolean hasInventoryConditionPassed() {
        return inventoryConditionPassed;
    }
    
    
    
    
    
    
}
