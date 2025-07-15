/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.identifiers.ObjectId;

/**
 *
 * @author utente
 */
public class InventoryEffect {
    private ObjectId addingObject;
    private ObjectId removingObject;

    /**
     *
     * @param addingObject
     * @param removingObject
     */
    public InventoryEffect(ObjectId addingObject, ObjectId removingObject) {
        this.addingObject = addingObject;
        this.removingObject = removingObject;
    }

    /**
     *
     * @return
     */
    public ObjectId getAddingObject() {
        return addingObject;
    }

    /**
     *
     * @return
     */
    public ObjectId getRemovingObject() {
        return removingObject;
    }
    
    
   
}
