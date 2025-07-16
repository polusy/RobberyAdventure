/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 * 
 * La classe estende {@link PropertyWithValue} (poiché una proprietà con un valore booleano associato).
 * 
 * La classe rappresenta la proprietà di premibilità di un oggetto, può avere due valori rappresentanti il suo stato:
 * 
 * {@link value} è True -> premuto.
 * {@link value} è False -> non premuto.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#PUSHABLE}.
 */
public class Pushable extends PropertyWithValue {
    
    /**
     *
     * @param value Valore associato alla premibilità dell'oggetto.
     */
    public Pushable(boolean value){
        super(PropertyType.PUSHABLE, value);
    }
}
