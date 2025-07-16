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
 * La classe rappresenta la proprietà di riempibilità di un oggetto, può avere due valori rappresentanti il valore della suo stato di riempimento:
 * 
 * value è True -> riempito.
 * value è False -> non riempito.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa.
 */
public class Fillable extends PropertyWithValue {
    
    /**
     *
     * @param value Stato di riempimento dell'oggetto.
     */
    public Fillable(boolean value){
        super(PropertyType.FILLABLE, value);
    }
    
}
