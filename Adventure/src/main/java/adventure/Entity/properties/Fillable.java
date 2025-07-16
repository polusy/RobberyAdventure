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
 * La classe rappresenta la proprietà di riempibilità di un oggetto, conserva un valore rappresentante il suo stato di riempimento:
 * 
 * {@link value} è True -> riempito.
 * {@link value} è False -> non riempito.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#FILLABLE}.
 * 
 * @author Paolo
 */
public class Fillable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di riempimento dell'oggetto.
     * 
     *
     */
    public Fillable(boolean value){
        super(PropertyType.FILLABLE, value);
    }
    
}
