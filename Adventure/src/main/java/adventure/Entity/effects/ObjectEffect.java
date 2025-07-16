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
 * La classe rappresenta un effetto (eseguito solo dopo aver superato la completeCondition)
 * sulle proprietà din un altro oggetto del gioco.
 * 
 * L'effetto sull'oggetto specifico viene conservato come composizione di:
 * - effetto sui valori delle proprietà dell'oggetto (nello specifico i valori associati ad ogni proprietà).
 * - effetto su container (se l'oggetto è container).
 * - visibilità dell'oggetto.
 * 
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
