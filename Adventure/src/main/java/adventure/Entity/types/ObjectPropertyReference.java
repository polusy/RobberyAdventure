/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.ObjectId;
import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public class ObjectPropertyReference {
    private ObjectId objectId;
    private PropertyType propertyType;

    public ObjectPropertyReference(ObjectId objectId, PropertyType propertyType) {
        this.objectId = objectId;
        this.propertyType = propertyType;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
    
    
}
