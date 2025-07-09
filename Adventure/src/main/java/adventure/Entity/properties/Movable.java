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
public class Movable extends PropertyWithValue {
    
    public Movable(boolean value){
        super(PropertyType.MOVABLE);
        super.setValue(value);
    }
}
