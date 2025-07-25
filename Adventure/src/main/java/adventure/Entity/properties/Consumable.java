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
 * La classe rappresenta la proprietà di consumabilità di un oggetto, conserva un valore rappresentante il suo stato di consumazione:
 * 
 * {@link value} è True -> consumato.
 * {@link value} è False -> non consumato.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#CONSUMABLE}. 
 */
public class Consumable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di consumazione dell'oggetto.
     */
    public Consumable(boolean value){
        super(PropertyType.CONSUMABLE, value);
    }    
}
