/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import adventure.identifiers.ObjectId;


/**
 *
 * @author utente
 */
public class CompleteCondition {
    final private List<InventoryCondition> inventoryConditionOptions; 
    final private Map<ObjectId, ObjectCondition> objectsConditions;

    public CompleteCondition(List<InventoryCondition> inventoryConditionOptions, Map<ObjectId, ObjectCondition> objectsConditions) {
        this.inventoryConditionOptions = inventoryConditionOptions;
        this.objectsConditions = objectsConditions;
    }
    
    
    public InventoryCondition getUniqueInventoryCondition() throws NoSuchElementException{
	if (inventoryConditionOptions != null && inventoryConditionOptions.size() == 1){
		return inventoryConditionOptions.get(0);
	}
	else{
		throw new NoSuchElementException();
	}
    }   

    public List<InventoryCondition> getInventoryConditionOptions() {
        return inventoryConditionOptions;
    }

    public Map<ObjectId, ObjectCondition> getObjectsConditions() {
        return objectsConditions;
    }
    
    
    
}
