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
    private ObjectId addingObject;
    private ObjectId removingObject;

    public RoomEffect(ObjectId addingObject, ObjectId removingObject) {
        this.addingObject = addingObject;
        this.removingObject = removingObject;
    }

    public ObjectId getAddingObject() {
        return addingObject;
    }

    public ObjectId getRemovingObject() {
        return removingObject;
    }
}
