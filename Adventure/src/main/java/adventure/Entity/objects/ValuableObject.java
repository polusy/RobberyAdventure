/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import adventure.identifiers.ObjectId;
import adventure.identifiers.CommandType;
import adventure.Entity.types.GameActionSpecification;
import adventure.Entity.properties.Property;
import adventure.exceptions.InconsistentInitializationException;
import java.util.Set;
import java.util.Map;

/**
 *
 * @author Paolo
 */
public class ValuableObject extends InteractiveObject{
    
    private int value;
    
    /**
     *
     * @param objectId
     * @param name
     * @param description
     * @param brokenDescription
     * @param alias
     * @param visible
     * @param gameActionSpecification
     * @param value
     * @throws InconsistentInitializationException
     */
    public ValuableObject(ObjectId objectId, String name, String description,
            String brokenDescription, Set<String> alias, boolean visible,
            Map<Property,Map<CommandType, GameActionSpecification>> gameActionSpecification, int value)throws InconsistentInitializationException{

        super(objectId, name, description, brokenDescription, alias, visible, gameActionSpecification);
        
       if (value >= 0)
            this.value = value;
       else
           throw new InconsistentInitializationException();

    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
    
    
    
    
}
