/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.PropertyType;

/**
 *
 * @author Paolo
 */
public class PropertyValue {
    
    private PropertyType propertyType;
    private boolean value;

    public PropertyValue(PropertyType propertyType, boolean value) {
        this.propertyType = propertyType;
        this.value = value;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
    
    
    
    
    
    
    
}
