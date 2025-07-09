/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import adventure.identifiers.ObjectId;
import java.util.Set;

/**
 *
 * @author utente
 */
public class AdvObject {
    final private ObjectId id;
    final private String name;
    final private String description;
    final private Set<String> alias;
    private boolean visible;

    // Constructors
    
    public AdvObject(ObjectId id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    public AdvObject(ObjectId id, String name, String description, Set<String> alias, boolean visible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.visible = visible;
    }

    // Getter methods
    
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public boolean isVisible() { return visible; }
    
    // Setter method
    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    
}
