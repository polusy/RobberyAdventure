/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;
import adventure.identifiers.PropertyType;

/**
 *
 * @author Paolo
 * 
 * La classe estende la classe PropertyWithValue (poiché una proprietà con un valore booleano associato).
 * 
 * La classe rappresenta la proprietà di usabilità di un oggetto, può avere due valori rappresentanti il suo stato:
 * 
 * value è True -> usato.
 * value è False -> non usato.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa.
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
