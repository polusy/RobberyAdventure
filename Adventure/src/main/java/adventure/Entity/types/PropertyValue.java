/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.PropertyType;

/** Associazione di un valore dicotomico ad una specifica tipologia di proprietà, rappresentanta dal suo identificativo
 * {@link PropertyType}
 *
 * @author Paolo
 */
public class PropertyValue {
    
    private PropertyType propertyType;
    private boolean value;

    /**
     *
     * @param propertyType Identificativo della proprietà
     * @param value Valore dicotomico da associare alla proprietà
     */
    public PropertyValue(PropertyType propertyType, boolean value) {
        this.propertyType = propertyType;
        this.value = value;
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

    /**
     *
     * @return
     */
    public boolean getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(boolean value) {
        this.value = value;
    }
    
    
    
    
    
    
    
    
}
