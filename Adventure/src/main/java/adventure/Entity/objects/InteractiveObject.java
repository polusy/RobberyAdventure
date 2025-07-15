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
 */
public class InteractiveObject extends AdvObject {
    final private String brokenDescription;
    final private Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications;

    // Constructor

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param brokenDescription
     * @param alias
     * @param visible
     * @param gameActionSpecifications
     * @throws InconsistentInitializationException
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
