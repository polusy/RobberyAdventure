/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;
import adventure.identifiers.ObjectId;

/**
 *
 * @author Paolo
 */
public class LootBagEffect {
    
    private ObjectId addingObject;

    public LootBagEffect(ObjectId addingObject) {
        this.addingObject = addingObject;
    }
    
    public ObjectId getAddingObject() {
        return addingObject;
    }

    public void setAddingObject(ObjectId addingObject) {
        this.addingObject = addingObject;
    }
    
    
    
    
    
    
    
}
