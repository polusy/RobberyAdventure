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
 * La classe rappresenta la proprietà di usabilità di un oggetto, conserva un valore rappresentante il suo stato d'utilizzo:
 * 
 * {@link value} è True -> usato.
 * {@link value} è False -> non usato.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#USABLE}.
 * 
 * @author Paolo
 */
public class Usable extends PropertyWithValue {
    
    /**
     *
     * @param value Valore caratteristico associato all'usabilità dell'oggetto (usato, non usato).
     */
    public Usable(boolean value){
        super(PropertyType.USABLE, value);
    }
    
}
