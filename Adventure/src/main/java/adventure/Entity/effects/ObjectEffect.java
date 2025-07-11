/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;
import adventure.Entity.types.PropertyValue;
import java.util.Set;

/**
 *
 * @author Paolo
 */
public class ObjectEffect {
    
    private final Set<PropertyValue> propertyWithValueResults;
    private ContainerEffect containerEffect;
    private Boolean visibility;
    
    public ObjectEffect(Set<PropertyValue> propertyWithValueResults, ContainerEffect containerEffect, boolean visibility){
        this.propertyWithValueResults = propertyWithValueResults;
        this.containerEffect = containerEffect;
        this.visibility = visibility;
    }

    public Set<PropertyValue> getPropertyWithValueResults() {
        return propertyWithValueResults;
    }
    

    public ContainerEffect getContainerEffect() {
        return containerEffect;
    }

    public void setContainerEffect(ContainerEffect containerEffect) {
        this.containerEffect = containerEffect;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
    
    
    
    
    
    
    
    
}
