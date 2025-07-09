/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;
import adventure.Entity.types.PropertyValue;
import java.util.Set;
import adventure.identifiers.PropertyType;
import java.util.NoSuchElementException;

/**
 *
 * @author Paolo
 */
public class ObjectCondition {
    
    private final Set<PropertyValue> PropertyWithValueConstraints;
    private final boolean visibility;

    public ObjectCondition(Set<PropertyValue> PropertyWithValueConstraints, boolean visibility) {
        this.PropertyWithValueConstraints = PropertyWithValueConstraints;
        this.visibility = visibility;
    }
    


    public boolean getVisibility() {
        return visibility;
    }
    
    public PropertyValue getPropertyValue(PropertyType propertyType) throws NoSuchElementException {
        
        PropertyValue propertyValue = null;
        
        for (PropertyValue iteratedPropertyValue : this.PropertyWithValueConstraints){
            if (iteratedPropertyValue.getPropertyType() == propertyType){
                propertyValue = iteratedPropertyValue;
            }
        }
        
        if (propertyValue == null)
            throw new NoSuchElementException();
        
        return propertyValue;
    }
    
    
    
    
}
