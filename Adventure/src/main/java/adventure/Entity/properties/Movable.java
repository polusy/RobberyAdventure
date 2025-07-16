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
 * La classe rappresenta la proprietà di mobilità di un oggetto, conserva un valore rappresentante il suo stato di spostamento:
 * 
 * {@link value} è True -> spostato.
 * {@link value} è False -> non spostato.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#MOVABLE}.
 * 
 * @author utente
 */
public class Movable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di spostamento dell'oggetto.
     */
    public Movable(boolean value){
        super(PropertyType.MOVABLE, value);
    }
}
