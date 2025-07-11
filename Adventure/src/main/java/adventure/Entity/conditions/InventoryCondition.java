/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;

import adventure.identifiers.ObjectId;
import adventure.Entity.objects.AdvObject;
import java.util.List;

/**
 *
 * @author utente
 */
public class InventoryCondition {
    final private List<ObjectId> necessaryObjects;

    public InventoryCondition(List<ObjectId> necessaryObjects) {
        this.necessaryObjects = necessaryObjects;
    }

    public List<ObjectId> getNecessaryObjects() {
        return necessaryObjects;
    }
    
    public boolean contains(AdvObject object){
        if (necessaryObjects.contains(object))
            return true;
        else
            return false;
        
    }
    
    
}
