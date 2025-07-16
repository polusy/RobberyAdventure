/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import java.util.Map;
import java.util.Set;
import java.util.NoSuchElementException;

import adventure.Entity.properties.Property;
import adventure.Entity.properties.PropertyWithValue;
import adventure.Entity.properties.Container;
import adventure.identifiers.PropertyType;
import adventure.identifiers.CommandType;
import adventure.identifiers.ObjectId;
import adventure.exceptions.InconsistentInitializationException;
import adventure.Entity.objects.AdvObject;
import adventure.utilities.PropertyCommandsCorrespondence;
import adventure.Entity.types.GameActionSpecification;

/**
 *
 * @author utente
 * 
 * La classe rappresenta un qualsiasi oggetto interattivo dell'avventura. Estende la classe AdvObject,
 * infatti conserva tutti gli attributi di un normale oggetto e in più conserva una Map contenente:
 * - Per ogni property, una Map con un comando, a cui viene associata una specifica dell'azione di gioco.
 * 
 * La gameActionSpecification rappresenta l'insieme delle condizioni necessarie e degli effetti da applicare alla sessione di gioco
 * al momento del superamento di tutte i vincoli specificati nella specifica dell'azione di gioco.
 */
public class InteractiveObject extends AdvObject {
    final private String brokenDescription;
    final private Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications;

    // Constructor

    /**
     *
     * @param id Identificativo dell'oggetto.
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
     * @param gameActionSpecifications Rappresenta l'insieme delle condizioni, effetti, e messaggi di passaggio e failing sulle condizioni
     * associate ad una specifica proprietà di un oggetto e ad uno specifico comando.
     * 
     * @throws InconsistentInitializationException Se l'oggetto viene inizializzato in modo inconsistente.
     * 
     * Il metodo costruttore si assicura che se la proprietà inserita non è una proprietà con valore booleano (Container), allora ad essa
     * non deve avere associata alcuna mappa con CommandType e GameActionSpecification (in quanto a questa proprietà non deve essere mai
     * associata ad alcuna specifica di azione di gioco).
     * 
     * Si assicura inoltre che se la proprietà è una proprietà con valore booleano, allora necessariamente
     * deve avere una specifica di un'azione di gioco.
     * 
     * Il metodo si assicura inoltre che la proprietà inserita sia corente con il tipo di comando inserito nella Map più interna:
     * 
     * e.g. Openable -> (Open, Close)
     *      Pickupable -> (Pickup, Drop)
     */
    
    public InteractiveObject(ObjectId id, String name, String description, String brokenDescription, 
            Set<String> alias, boolean visible, 
            Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications)
            throws InconsistentInitializationException {
	super(id, name, description, alias, visible);

	for (Map.Entry<Property, Map<CommandType, GameActionSpecification>> generalEntry : gameActionSpecifications.entrySet()){
            
            if (!(generalEntry.getKey() instanceof PropertyWithValue) && (generalEntry.getValue() != null))
                throw new InconsistentInitializationException();
            
            if ((generalEntry.getKey() instanceof PropertyWithValue) && generalEntry.getValue() != null){
                PropertyWithValue property = (PropertyWithValue) generalEntry.getKey();
                
                if (!PropertyCommandsCorrespondence.getCommands(property.getType()).containsAll(generalEntry.getValue().keySet()))
                    throw new InconsistentInitializationException();
            }
	}

	this.gameActionSpecifications = gameActionSpecifications;
        this.brokenDescription = brokenDescription;
    }
    
    // Getter methods

    /**
     *
     * @return
     */
    
    public String getBrokenDescription() { return brokenDescription; }
    
    /**
     *
     * @return
     */
    public Set<Property> getProperties(){
	return gameActionSpecifications.keySet();
    }

    /**
     *
     * @return
     * @throws NoSuchElementException
     */
    public Container getContainerProperty() throws NoSuchElementException {
	if (!this.isContainer())
            throw new NoSuchElementException();

	Container containerProperty = null;
	
	for (Property property : this.getProperties())
            if (property instanceof Container)
                    containerProperty = (Container) property;

	return containerProperty;
    }    
    
    /**
     *
     * @param propertyType
     * @return
     * @throws NoSuchElementException
     */
    public PropertyWithValue getPropertyByType(PropertyType propertyType) throws NoSuchElementException{
	if (!this.hasProperty(propertyType))
		throw new NoSuchElementException();

        PropertyWithValue propertyWithValue = null;
        
	for (Property property : this.getProperties()){
            if (property instanceof PropertyWithValue){
                if (((PropertyWithValue) property).getType() == propertyType)
                    propertyWithValue = (PropertyWithValue) property;
            }
        }

	return propertyWithValue;
    }    

    /**
     *
     * @param propertyType
     * @param commandType
     * @return
     */
    public GameActionSpecification getGameActionSpecification(PropertyType propertyType, CommandType commandType)
    {
        if (gameActionSpecifications.get(this.getPropertyByType(propertyType)) != null)
            return gameActionSpecifications.get(this.getPropertyByType(propertyType)).get(commandType);
        else
            return null;
    }
    
    // Setter method

    /**
     *
     * @param propertyType
     * @param value
     * @throws NoSuchElementException
     */
    
    public void setValueToProperty(PropertyType propertyType, boolean value) throws NoSuchElementException
    {
	if (!this.hasProperty(propertyType))
		throw new NoSuchElementException();

	this.getPropertyByType(propertyType).setValue(value);
    }   
    
    /**
     *
     * @param propertyType
     * @return
     */
    public boolean hasProperty(PropertyType propertyType){ 
	boolean foundProperty = false;

	for (Property property : this.getProperties()){
            if (property instanceof PropertyWithValue){
                PropertyWithValue propertyWithValue = (PropertyWithValue) property;
                
                if (propertyWithValue.getType() == propertyType && !foundProperty)
                    foundProperty = true;
            }
        }
			
	return foundProperty;
    }

    /**
     *
     * @return
     */
    public boolean isContainer(){
	boolean container = false;
	
	for (Property property : this.getProperties())
            if (property instanceof Container)
                container = true;
        
	return container;
    }


}
