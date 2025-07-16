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
 * 
 * 
 * La classe rappresenta il risultato del metodo analyze degli analyzers del gioco.
 * 
 * @author Paolo
 */
public class CommandAnalysisResult {
    
    /**
     * Il {@code message} individuato da mostrare all'utente.
     */
    private String message;
    
    /**Il {@code targetObject} individuato dall'analyzer, attraverso l'analisi del parserOutput, rappresenta
     * l'oggetto target su cui si vuole eseguire l'azione, e che quindi sarà necessario per valutare condizioni di applicabilità
     * e esecuzione degli effetti.
     */ 
    private AdvObject targetObject;
    
    /**
     * Il {@code auxiliaryObject} rappresenta l'oggetto ausiliario nella stringa inserita dall'utente, rappresentante un
     * oggetto necessario per eseguire un'azione specifica sul {@code targetObject}.
     */
    private InteractiveObject auxiliaryObject;
    
    /**
     * - Il boolean {@code analysisPassed} rappresenta il risultato dell'analisi degli analyzers:
     *  se la stringa integrale è semanticamente valida rispetto all'azione che si vuole eseguire sul {@code targetObject}, per mezzo del {@code auxiliaryObject}
     *  allora è impostato a {@code true}.
     */
    private boolean analysisPassed;
    
    /**
     * - la {@code propertyType} inserita rappresenta il tipo di proprietà che lo specifico Analyzer ha assicurato
     * fosse presente nell'insieme di proprietà dell'oggetto.
     */
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
