/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;
import java.util.Objects;

/**
 *
 * @author utente
 */
public abstract class PropertyWithValue extends Property {
    private boolean value;
    final private PropertyType propertyType; 

    // Constructors
    
    public PropertyWithValue(PropertyType propertyType, boolean value) {
        this.propertyType = propertyType;
        this.value = value;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.propertyType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PropertyWithValue other = (PropertyWithValue) obj;
        return this.propertyType == other.propertyType;
    }
    
    
}
