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
 * La classe rappresenta la proprietà di attivabilità di un oggetto, può avere due valori rappresentanti il suo stato di attivazione:
 * 
 * {@link value} è True -> attivato
 * {@link value} è False -> disattivato.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa. {@link PropertyType#ACTIVATABLE}. 
 */
public class Activatable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di attivazione dell'oggetto.
     */
    public Activatable(boolean value){
        super(PropertyType.ACTIVATABLE, value);
    }
}
