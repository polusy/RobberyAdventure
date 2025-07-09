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
public class Openable extends PropertyWithValue {
    
    public Openable(boolean value){
        super(PropertyType.OPENABLE);
        super.setValue(value);
    }
}
