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
public class ContainerEffect {
    
    private ObjectId removingObject;

    /**
     *
     * @param removingObject
     */
    public ContainerEffect(ObjectId removingObject) {
        this.removingObject = removingObject;
    }

    /**
     *
     * @return
     */
    public ObjectId getRemovingObject() {
        return removingObject;
    }

    /**
     *
     * @param removingObject
     */
    public void setRemovingObject(ObjectId removingObject) {
        this.removingObject = removingObject;
    }
    
    
    
    
    
    
    
}
