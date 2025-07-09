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
public class Breakable extends PropertyWithValue {
    
    public Breakable(boolean value){
        super(PropertyType.BREAKABLE);
        super.setValue(value);
    }
    
}
