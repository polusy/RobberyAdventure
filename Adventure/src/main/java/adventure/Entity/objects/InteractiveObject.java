/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.objects;

import java.util.Map;
import java.util.Set;

import adventure.Entity.properties.Property;
import adventure.Entity.properties.PropertyWithValue;
import adventure.identifiers.PropertyType;
import adventure.identifiers.CommandType;
import adventure.identifiers.ObjectId;
import adventure.exceptions.InconsistentInitializationException;
import adventure.Entity.objects.AdvObject;
import adventure.utilities.PropertyCommandsCorrespondence;

/**
 *
 * @author utente
 */
public class InteractiveObject extends AdvObject {
    private String brokenDescription;
    private Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications;

    // Constructor
    
    public InteractiveObject(ObjectId id, String name, String description, String brokenDescription, 
            Set<String> alias, boolean visible, 
            Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications)
            throws InconsistentInitializationException {
	super(id, name, description, alias, visible);

	for (Map.Entry<Property, Map<CommandType, GameActionSpecification>> generalEntry : gameActionSpecifications.entrySet()){
            
            if (!(generalEntry.getKey() instanceof PropertyWithValue) && !generalEntry.getValue().keySet().isEmpty())
                throw new InconsistentInitializationException();
            
            if ((generalEntry.getKey() instanceof PropertyWithValue) && !generalEntry.getValue().keySet().isEmpty()){
                PropertyWithValue property = (PropertyWithValue) generalEntry.getKey();
                
                if (!PropertyCommandsCorrespondence.getCommands(property.getType()).containsAll(generalEntry.getValue().keySet()))
                    throw new InconsistentInitializationException();
            }
	}

	this.gameActionSpecifications = gameActionSpecifications;
    }
    
    // Getter methods
    
    public String getBrokenDescription() { return brokenDescription; }
    
    public Set<Property> getProperties(){
	return gameActionSpecifications.keySet();
    }

    public boolean hasProperty(PropertyType propertyType){ 
	boolean foundProperty = false;

	for (Property property : this.getProperties())
            if (property. == propertyType && !foundProperty)
                    foundProperty = true;
			
	return foundProperty;
}

+ boolean isContainer(){
	boolean container = false;
	
	for (property : this.getProperties())
		if (property instanceof Container)
			container = true;

	return container;
}

+ Container getContainerProperty() throws NoSuchElementException {
	if (!this.isContainer())
		throw new NoSuchElementException();

	Container containerProperty;
	
	for (property : this.getProperties())
		if (property instanceof Container)
			containerProperty = (Container) property;

	return containerProperty;
}

+ Property getPropertyByType(PropertyType propertyType) throws NoSuchElementException
{
	if (!this.hasProperty(propertyType))
		throw new NoSucElementException();

	Property property;
	for (iteratedProperty : this.getProperties())
		if (iteratedProperty.getType() == propertyType)
			property = iteratedProperty;

	return property;
}

+ GameActionSpecification getGameActionSpecification(PropertyType propertyType, CommandType commandType)
{
	return gameActionSpecifications.get(this.getPropertyByType(propertyType)).get(commandType);
}

+ void setValueToProperty(PropertyType propertyType, boolean value) throws NoSuchElementException
{
	if (!this.hasProperty(propertyType))
		throw new NoSuchElementException();

	PropertyWithvalue propertyWithValue = (PropertyWithValue) this.getPropertyByType(propertyType);
	propertyWithValue.setValue(value);
	
}    
}
