/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.PropertyType;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.AdvObject;

/**
 *
 * @author utente
 */
public class CommandAnalysisResult {
    private String message;
    private AdvObject targetObject;
    private InteractiveObject auxiliaryObject;
    private boolean analysisPassed;
    private PropertyType propertyType;    

    /**
     *
     * @param message
     * @param targetObject
     * @param auxiliaryObject
     * @param analysisPassed
     * @param propertyType
     */
    public CommandAnalysisResult(String message, InteractiveObject targetObject, InteractiveObject auxiliaryObject, boolean analysisPassed, PropertyType propertyType) {
        this.message = message;
        this.targetObject = targetObject;
        this.auxiliaryObject = auxiliaryObject;
        this.analysisPassed = analysisPassed;
        this.propertyType = propertyType;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public AdvObject getTargetObject() {
        return targetObject;
    }

    /**
     *
     * @param targetObject
     */
    public void setTargetObject(AdvObject targetObject) {
        this.targetObject = targetObject;
    }

    /**
     *
     * @return
     */
    public InteractiveObject getAuxiliaryObject() {
        return auxiliaryObject;
    }

    /**
     *
     * @param auxiliaryObject
     */
    public void setAuxiliaryObject(InteractiveObject auxiliaryObject) {
        this.auxiliaryObject = auxiliaryObject;
    }

    /**
     *
     * @return
     */
    public boolean isAnalysisPassed() {
        return analysisPassed;
    }

    /**
     *
     * @param analysisPassed
     */
    public void setAnalysisPassed(boolean analysisPassed) {
        this.analysisPassed = analysisPassed;
    }

    /**
     *
     * @return
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     *
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
    
    
}
