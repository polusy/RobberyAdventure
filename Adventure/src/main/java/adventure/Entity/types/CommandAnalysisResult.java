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

    public CommandAnalysisResult(String message, InteractiveObject targetObject, InteractiveObject auxiliaryObject, boolean analysisPassed, PropertyType propertyType) {
        this.message = message;
        this.targetObject = targetObject;
        this.auxiliaryObject = auxiliaryObject;
        this.analysisPassed = analysisPassed;
        this.propertyType = propertyType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdvObject getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(AdvObject targetObject) {
        this.targetObject = targetObject;
    }

    public InteractiveObject getAuxiliaryObject() {
        return auxiliaryObject;
    }

    public void setAuxiliaryObject(InteractiveObject auxiliaryObject) {
        this.auxiliaryObject = auxiliaryObject;
    }

    public boolean isAnalysisPassed() {
        return analysisPassed;
    }

    public void setAnalysisPassed(boolean analysisPassed) {
        this.analysisPassed = analysisPassed;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
    
    
}
