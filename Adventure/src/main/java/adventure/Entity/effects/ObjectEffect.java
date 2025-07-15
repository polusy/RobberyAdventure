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
    
    /**
     *
     * @param propertyWithValueResults
     * @param containerEffect
     * @param visibility
     */
    public ObjectEffect(Set<PropertyValue> propertyWithValueResults, ContainerEffect containerEffect, boolean visibility){
        this.propertyWithValueResults = propertyWithValueResults;
        this.containerEffect = containerEffect;
        this.visibility = visibility;
    }

    /**
     *
     * @return
     */
    public Set<PropertyValue> getPropertyWithValueResults() {
        return propertyWithValueResults;
    }
    
    /**
     *
     * @return
     */
    public ContainerEffect getContainerEffect() {
        return containerEffect;
    }

    /**
     *
     * @param containerEffect
     */
    public void setContainerEffect(ContainerEffect containerEffect) {
        this.containerEffect = containerEffect;
    }

    /**
     *
     * @return
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     */
    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
    
    
    
    
    
    
    
    
}
