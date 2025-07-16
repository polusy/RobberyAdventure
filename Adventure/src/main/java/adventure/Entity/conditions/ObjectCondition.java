/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;
import adventure.Entity.types.PropertyValue;
import java.util.Set;
import java.util.HashSet;
import adventure.identifiers.PropertyType;
import java.util.NoSuchElementException;

/**
 *
 * 
 * 
 * La classe rappresenta la condizione completa su di un oggetto che deve essere necessariamente rispettata,
 * affinché possa essere eseguita un'azione effettiva sulla sessione di gioco.
 * 
 * @author Paolo
 */
public class ObjectCondition {
    
    /**
     * insieme di condizioni sulle proprietà di un oggetto
     * che devono essere necessariamente rispettate,
     * affinché possa essere eseguita un'azione effettiva sulla sessione di gioco.
     */
    private Set<PropertyValue> propertyWithValueConstraints = new HashSet<>();
    
    /**
     * visibilità di oggetto che deve essere necessariamente rispettata
     * affinché possa essere eseguita un'azione effettiva sulla sessione di gioco.
     */
    private final boolean visibility;

    /**
     *
     * @param propertyWithValueConstraints
     * @param visibility
     */
    public ObjectCondition(Set<PropertyValue> propertyWithValueConstraints, boolean visibility) {
        this.propertyWithValueConstraints = propertyWithValueConstraints;
        this.visibility = visibility;
    }
    
    /**
     *
     * @return
     */
    public boolean getVisibility() {
        return visibility;
    }
    
    /**
     * 
     * Il metodo restituisce una proprietà che conserva il vincolo valoriale
     * che corrisponde al {@link PropertyType} inserito come parametro.
     *
     * @param propertyType tipo di proprietà di cui si vuole conoscere il valore associato nell'insieme di vincoli.
     * @return La proprietà (he conserva il vincolo valoriale) che corrisponde al {@link PropertyType} inserito come parametro.
     * @throws NoSuchElementException Se viene inserito un tipo di proprietà non presente nella lista di condizioni sull'oggetto.
     */
    public PropertyValue getPropertyValue(PropertyType propertyType) throws NoSuchElementException {
        
        PropertyValue propertyValue = null;
    
        if (this.propertyWithValueConstraints != null)
        {
            for (PropertyValue iteratedPropertyValue : this.propertyWithValueConstraints){
                if (iteratedPropertyValue.getPropertyType() == propertyType){
                    propertyValue = iteratedPropertyValue;
                }
            }
        }
        
        if (propertyValue == null)
            throw new NoSuchElementException();
        
        return propertyValue;
    }
    
    
    
    
}
