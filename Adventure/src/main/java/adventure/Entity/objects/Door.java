/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import adventure.identifiers.ObjectId;
import adventure.identifiers.CommandType;
import adventure.Entity.types.GameActionSpecification;
import adventure.Entity.properties.Property;
import adventure.exceptions.InconsistentInitializationException;
import java.util.Set;
import java.util.Map;

/**
 *
 * 
 * 
 * la classe rappresenta un oggetto di tipo Porta, estende {@link InteractiveObject}, in quanto
 * oggetto interattivo.
 * 
 * 
 * @author Paolo
 */
public class Door extends InteractiveObject{
    
    /**
     * L'attributo rappresenta il nome comune ("porta").
     */
    public static final String commonDoorName = "porta";
    
    /**
     * {@code special = true}, se il nome della porta è diverso da "porta".
     * {@code special = false}, se il nome della porta è uguale a "porta" (è quindi una porta comune).
     */
    private boolean special;
    
    /**
     *
     * @param objectId Identificativo dell'oggetto.
     * 
     * @param name Nome originale dell'oggetto.
     * 
     * @param description Descrizione dell'oggetto (restituita con observe dell'ogetto).
     * 
     * @param brokenDescription Descrizione dell'oggetto rotto (se l'oggetto ha la proprietàBreakable).
     * 
     * @param alias Alias del nome originale dell'oggetto
     * 
     * @param visible Rappresenta la visiiblità dell'oggetto.
     * 
     * @param gameActionSpecification Rappresenta l'insieme delle condizioni, effetti, e messaggi di passaggio e failing sulle condizioni
     * associate ad una specifica proprietà di un oggetto e ad uno specifico comando.
     * 
     * @param special True, Se la porta ha nome diverso da "porta".
     * 
     * @throws InconsistentInitializationException Se l'oggetto viene inizializzato in modo inconsistente.
     */
    public Door(ObjectId objectId, String name, String description,
            String brokenDescription, Set<String> alias, boolean visible,
            Map<Property,Map<CommandType, GameActionSpecification>> gameActionSpecification, boolean special)throws InconsistentInitializationException{
        
        super(objectId, name, description, brokenDescription, alias, visible, gameActionSpecification);
        this.special = special;
        
    }

    /**
     *
     * @return
     */
    public boolean isSpecial() {
        return special;
    }
    
    
    
}
