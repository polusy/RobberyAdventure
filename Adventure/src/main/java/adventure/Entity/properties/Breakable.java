/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;

/**
 *
 * La classe estende {@link PropertyWithValue} (poiché una proprietà con un valore booleano associato).
 * 
 * La classe rappresenta la proprietà di rompibilità di un oggetto, conserva un valore rappresentante il suo stato:
 * 
 * {@link value} è True -> rotto
 * {@link value} è False -> integro.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#BREAKABLE}.
 * 
 * @author Paolo
 */
public class Breakable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di integrità dell'oggetto.
     */
    public Breakable(boolean value){
        super(PropertyType.BREAKABLE, value);
    }
    
}
