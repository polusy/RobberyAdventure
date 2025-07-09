/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public abstract class PropertyWithValue extends Property {
    private boolean value;
    final private PropertyType propertyType; 

    // Constructors
    
    public PropertyWithValue(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    // Getter methods
    
    public boolean getValue() {
        return value;
    }

    public PropertyType getType(){ return propertyType; }
    
    // Setter method
    
    public void setValue(boolean value) {
        this.value = value;
    }    
}
