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
    
    private boolean conditionPassed;
    private boolean inventoryConditionPassed;
    private ObjectId missingNecessaryObject;
    private ObjectPropertyReference objectFailedConditionReference;
    private ObjectId failedVisibilityObject;

    /**
     *
     */
    public ConditionEvaluationResult() {
        this.conditionPassed = true;
        this.inventoryConditionPassed = true;
        this.missingNecessaryObject = null;
        this.objectFailedConditionReference = null;
        this.failedVisibilityObject = null;
    }

    /**
     *
     * @param objectFailedConditionReference
     */
    public void setObjectFailedConditionReference(ObjectPropertyReference objectFailedConditionReference) {
        this.objectFailedConditionReference = objectFailedConditionReference;
    }

    /**
     *
     * @param failedVisibilityObject
     */
    public void setFailedVisibilityObject(ObjectId failedVisibilityObject) {
        this.failedVisibilityObject = failedVisibilityObject;
    }

    /**
     *
     * @param inventoryConditionPassed
     */
    public void setInventoryConditionPassed(boolean inventoryConditionPassed) {
        this.inventoryConditionPassed = inventoryConditionPassed;
    }

    /**
     *
     * @param conditionPassed
     */
    public void setConditionPassed(boolean conditionPassed) {
        this.conditionPassed = conditionPassed;
    }

    /**
     *
     * @param missingNecessaryObject
     */
    public void setMissingNecessaryObject(ObjectId missingNecessaryObject) {
        this.missingNecessaryObject = missingNecessaryObject;
    }
    
    /**
     *
     * @return
     */
    public boolean hasConditionPassed() {
        return conditionPassed;
    }

    /**
     *
     * @return
     */
    public boolean hasInventoryConditionPassed() {
        return inventoryConditionPassed;
    }

    /**
     *
     * @return
     */
    public ObjectId getMissingNecessaryObject() {
        return missingNecessaryObject;
    }

    /**
     *
     * @return
     */
    public ObjectPropertyReference getObjectFailedConditionReference() {
        return objectFailedConditionReference;
    }

    /**
     *
     * @return
     */
    public ObjectId getFailedVisibilityObject() {
        return failedVisibilityObject;
    }
    
    
    
    
    
    
    
    
}
