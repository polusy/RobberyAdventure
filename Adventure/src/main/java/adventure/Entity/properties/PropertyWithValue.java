/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;
import java.util.Objects;

/**
 *
 *
 * 
 * La classe estende {@link Property}, in quanto proprietà di oggetto.
 * La classe modella, in più, una proprietà con un valore di stato associato.
 * 
 * 
 * e.g. (Breakable ha {@link value} a True se l'oggetto è rotto, False altrimenti).
 * 
 *  @author utente
 * 
 */
public abstract class PropertyWithValue extends Property {
    
    /**
     *  Rappresenta il valore caratteristico della proprietà dell'oggetto in uno specifico momento della sessione di gioco.
     */
    private boolean value;
    
    /**
     * Identificativo associato alla proprietà con valore di stato associato.
     */
    final private PropertyType propertyType; 

    // Constructors

    /**
     *
     * @param propertyType
     * @param value
     */
    
    public PropertyWithValue(PropertyType propertyType, boolean value) {
        this.propertyType = propertyType;
        this.value = value;
    }

    // Getter methods

    /**
     *
     * @return
     */
    
    public boolean getValue() {
        return value;
    }

    /**
     *
     * @return
     */
    public PropertyType getType(){ return propertyType; }
    
    // Setter method

    /**
     *
     * @param value
     */
    
    public void setValue(boolean value) {
        this.value = value;
    }    

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.propertyType);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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
