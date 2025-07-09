/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;
import adventure.identifiers.PropertyType;

/**
 *
 * @author Paolo
 */
public class Pickupable extends PropertyWithValue {
    
    public Pickupable(boolean value){
        super(PropertyType.PICKUPABLE);
        super.setValue(value);
    }
    
}
