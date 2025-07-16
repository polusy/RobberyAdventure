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
 * 
 * La classe rappresenta l'oggetto comune dell'avventura, conserva un id, un nome, una descrizione, gli alias
 * del nome originale (se necessario) e la visibilità di questo all'interno del gioco.
 */
public class AdvObject {
    private ObjectId id;
    private String name;
    private String description;
    private Set<String> alias;
    private boolean visible;

    // Constructors

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     */
    
    public AdvObject(ObjectId id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     * @param visible
     */
    public AdvObject(ObjectId id, String name, String description, Set<String> alias, boolean visible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.visible = visible;
    }

    // Getter methods

    /**
     *
     * @return
     */
    
    public ObjectId getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     *
     * @return
     */
    public boolean isVisible() { return visible; }
    
    // Setter method

    /**
     *
     * @param visible
     */
    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    /**
     *
     */
    public AdvObject() {
    }

    /**
     *
     * @param id
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    /**
     *
     * @return HashCode definito sull'Id dell'oggetto.
     * 
     * Questo garantisce che oggetti considerati uguali secondo il metodo equals
     * abbiano lo stesso codice hash.
     *
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return True se gli id dell'oggetto obj è uguale all'id dell'oggetto su cui viene invocato l'equals, False altrimenti.
     * 
     * Il metodo ridefinisce l'equals, garantendo che due oggetti sono uguali se e solo se
     * il loro id è lo stesso.
     */
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
