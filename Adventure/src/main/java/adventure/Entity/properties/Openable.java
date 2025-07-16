/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;

/**
 *
 * 
 * 
 * La classe estende {@link PropertyWithValue} (poiché una proprietà con un valore booleano associato).
 * 
 * La classe rappresenta la proprietà di apribilità di un oggetto, conserva un valore rappresentante il suo stato di apertura/chiusura:
 * 
 * {@link value} è True -> aperto.
 * {@link value} è False -> chiuso.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#OPENABLE}.
 * 
 * @author utente
 */
public class Openable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di apertura dell'oggetto.
     * 
     * 
     */
    public Openable(boolean value){
        super(PropertyType.OPENABLE, value);
    }
}
