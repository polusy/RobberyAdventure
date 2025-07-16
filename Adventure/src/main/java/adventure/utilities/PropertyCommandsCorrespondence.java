/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
        
import adventure.identifiers.PropertyType;
import adventure.identifiers.CommandType;
import adventure.exceptions.DuplicateException;
import java.util.NoSuchElementException;

/** Mappa che associa ad un {@link PropertyType} l'insieme di {@link CommandType} corrispondente.
 * 
 * La classe conserva in questo modo delle corrispondenze tra una tipologia di proprietà, rappresentata dall'identificativo
 * {@link PropertyType} e un insieme di tipologie di comando, rappresentata dall'identificativo {@link CommandType}
 *
 * @author Alessandro
 */
public abstract class PropertyCommandsCorrespondence {
    private static Map<PropertyType, Set<CommandType>> propertyCommandsCorrespondences;
    
    static {
        propertyCommandsCorrespondences = new HashMap();
        
        try {
            addPropertyCommandsCorrespondence(PropertyType.ACTIVATABLE, 
            new CommandType[] {CommandType.ACTIVATE, CommandType.DEACTIVATE});
            
            addPropertyCommandsCorrespondence(PropertyType.BREAKABLE, 
            new CommandType[] {CommandType.BREAK});
            
            addPropertyCommandsCorrespondence(PropertyType.CONSUMABLE, 
            new CommandType[] {CommandType.CONSUME});   
            
            addPropertyCommandsCorrespondence(PropertyType.FILLABLE, 
            new CommandType[] {CommandType.FILL});    
            
            addPropertyCommandsCorrespondence(PropertyType.MOVABLE, 
            new CommandType[] {CommandType.MOVE});

            addPropertyCommandsCorrespondence(PropertyType.OPENABLE, 
            new CommandType[] {CommandType.CLOSE, CommandType.OPEN});

            addPropertyCommandsCorrespondence(PropertyType.PICKUPABLE, 
            new CommandType[] {CommandType.PICK_UP, CommandType.DROP});

            addPropertyCommandsCorrespondence(PropertyType.PUSHABLE, 
            new CommandType[] {CommandType.PUSH});

            addPropertyCommandsCorrespondence(PropertyType.USABLE, 
            new CommandType[] {CommandType.USE});            
        } catch (DuplicateException exception){};
    }

    private static void addPropertyCommandsCorrespondence(PropertyType propertyType, CommandType[] commands) throws DuplicateException {
        if (propertyCommandsCorrespondences.containsKey(propertyType))
            throw new DuplicateException();
        
        Set<CommandType> commandsSet = new HashSet();
        
        for (int i=0; i<commands.length; i++){
            commandsSet.add(commands[i]);
        }
        propertyCommandsCorrespondences.put(propertyType, commandsSet); 
    }
    
    /**
     *
     * @param propertyType Identificativo della proprietà di cui si desidera il relativo insieme di comandi associato
     * @return Insieme di identificativi di oggetti comando associato all'identificativo di oggetti proprietà 
     * {@link PropertyType} passato per parametro, se presente, altrimenti lancia un'eccezione {@link NoSuchElementException}
     * @throws NoSuchElementException
     */
    public static Set<CommandType> getCommands(PropertyType propertyType) throws NoSuchElementException
    {
	if (!propertyCommandsCorrespondences.containsKey(propertyType))
		throw new NoSuchElementException();

	return propertyCommandsCorrespondences.get(propertyType);
    }
}
