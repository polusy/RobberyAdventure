/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import adventure.identifiers.ObjectId;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author utente
 */
public class AdvObject {
    private ObjectId id;
    private String name;
    private String description;
    private Set<String> alias;
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

    public AdvObject() {
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdvObject other = (AdvObject) obj;
        return this.id == other.id;
    }
    
}
