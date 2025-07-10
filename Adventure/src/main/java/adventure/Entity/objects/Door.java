/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import adventure.identifiers.ObjectId;
import adventure.identifiers.CommandType;
import adventure.identifiers.PropertyType;
import adventure.Entity.types.GameActionSpecification;
import adventure.Entity.properties.Property;
import adventure.exceptions.InconsistentInitializationException;
import java.util.Set;
import java.util.Map;

/**
 *
 * @author Paolo
 */
public class Door extends InteractiveObject{
    
    private final String commonDoorName = "porta";
    private boolean special;
    
    
    public Door(ObjectId objectId, String name, String description,
            String brokenDescription, Set<String> alias, boolean visible,
            Map<Property,Map<CommandType, GameActionSpecification>> gameActionSpecification, boolean special)throws InconsistentInitializationException{
        
        super(objectId, name, description, brokenDescription, alias, visible, gameActionSpecification);
        this.special = special;
        
    }

    public String getCommonDoorName() {
        return commonDoorName;
    }

    public boolean isSpecial() {
        return special;
    }
    
    
    
}
