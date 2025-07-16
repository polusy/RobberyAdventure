/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.ObjectId;

/**
 *
 * @author Paolo
 * 
 * La classe rappresenta il risultato di valutazione delle condizioni presenti in una delle GameActionSpecification dell'oggetto target.
 * 
 */
public class ConditionEvaluationResult {
    
    /**
     * {@code conditionPassed} per indicare il superamento generale di tutte le condizioni (su inventario e su proprietà degli oggetti)
     */
    private boolean conditionPassed;
    
    /**
     *{@code inventoryConditionPassed} per indicare il superamento della condizione sull'inventario.
     */
    private boolean inventoryConditionPassed;
    
    /**
     * {@code missingNecessaryObject} per indicare un {@link ObjectId} dell'oggetto necessario mancante nell'inventario.
     */
    private ObjectId missingNecessaryObject;
    
    /**
     * {@code objectFailedConditionReference} rappresenta un riferimento all'oggetto di cui è fallita la valutazione della condizione, e di cui
     * viene conservato il tipo di proprietà specifico il cui valore ha portato al fallimento della condizione.
     */
    private ObjectPropertyReference objectFailedConditionReference;
    /**
     * {@code failedVisibilityObject} rappresenta l'oggetto su cui è fallito il controllo di visibilità. (e.g. l'oggetto doveva essere visibile,
     * ma invece il suo attributo visible è impostato a {@code false}).
     */
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
