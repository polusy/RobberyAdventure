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
 * @author Paolo
 * 
 * La classe rappresenta un qualsiasi oggetto di valore dell'avventura.
 * Poiché questi oggetti sono sempre raccoglibili e necessari per il proseguimento dell'avventura 
 * sono stati progettati come ereditanti {@link InteractiveObject} (poiché, appunto, necessariamente interattivi).
 * 
 * Conserva in più un attributo d'istanza intero {@code value}, che rappresenta il valore dell'oggetto raccolto o da raccogliere.
 */
public class ValuableObject extends InteractiveObject{
    
    private int value;
    
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
     * @param value Rappresenta il valore dell'oggetto.
     * 
     * @throws InconsistentInitializationException Se l'oggetto viene inizializzato in modo inconsistente.
     */
    public ValuableObject(ObjectId objectId, String name, String description,
            String brokenDescription, Set<String> alias, boolean visible,
            Map<Property,Map<CommandType, GameActionSpecification>> gameActionSpecification, int value)throws InconsistentInitializationException{

        super(objectId, name, description, brokenDescription, alias, visible, gameActionSpecification);
        
       if (value >= 0)
            this.value = value;
       else
           throw new InconsistentInitializationException();

    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
    
    
    
    
}
