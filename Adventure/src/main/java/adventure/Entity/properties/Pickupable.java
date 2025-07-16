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
 * La classe estende {@link PropertyWithValue} (poiché una proprietà con un valore booleano associato).
 * 
 * La classe rappresenta la proprietà (associata all'oggetto) di poter essere raccolto, può avere due valori rappresentanti il suo stato:
 * 
 * {@link value} è True -> raccolto.
 * {@link value} è False -> non raccolto.
 * 
 * La classe conserva inoltre la propertyType (identificativo) corrispondente ad essa {@link PropertyType#PICKUPABLE}.
 */
public class Pickupable extends PropertyWithValue {
    
    /**
     *
     * @param value Valore associato alla possibilità dell'oggetto di poter essere raccolto.
     */
    public Pickupable(boolean value){
        super(PropertyType.PICKUPABLE, value);
    }
    
}
