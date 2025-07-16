/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.ObjectId;
import adventure.identifiers.PropertyType;

/** Riferimento ad una specifica proprietà di uno specifico oggetto
 *
 * @author Alessandro
 */
public class ObjectPropertyReference {
    private ObjectId objectId;
    private PropertyType propertyType;

    /**
     *
     * @param objectId Identificativo dell'oggetto proprietario della proprietà
     * @param propertyType Identificativo della proprietà associata all'oggetto
     */
    public ObjectPropertyReference(ObjectId objectId, PropertyType propertyType) {
        this.objectId = objectId;
        this.propertyType = propertyType;
    }

    /**
     *
     * @return 
     */
    public ObjectId getObjectId() {
        return objectId;
    }

    /**
     *
     * @return
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     *
     * @param objectId Nuovo valore per l'identificativo dell'oggetto a cui è associata la proprietà
     */
    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    /**
     *
     * @param propertyType Nuovo valore per l'identificativo della proprità associata all'oggetto
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
    
    
}
