/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;

import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public class Pushable extends PropertyWithValue {
    
    public Pushable(boolean value){
        super(PropertyType.PUSHABLE);
        super.setValue(value);
    }
}
