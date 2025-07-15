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
public class RoomEffect {
    final private ObjectId addingObject;
    final private ObjectId removingObject;

    /**
     *
     * @param addingObject
     * @param removingObject
     */
    public RoomEffect(ObjectId addingObject, ObjectId removingObject) {
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
