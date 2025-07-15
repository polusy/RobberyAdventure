/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import java.util.Map;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashMap;

import adventure.Entity.objects.AdvObject;
import adventure.utilities.Preposition;

/**
 *
 * @author Paolo
 */
public class ParserOutput {
    
    private Command command;
    private String commandToken;
    private Map<AdvObject, Preposition> objects = new HashMap<>();
    private Room doorRoom = null;

    /**
     *
     * @param command
     * @param commandToken
     */
    public ParserOutput(Command command, String commandToken) {
        this.command = command;
        this.commandToken = commandToken;
    }
    
    /**
     *
     * @param object
     */
    public void addObject(AdvObject object){
        objects.put(object, null);
    }
    
    /**
     *
     * @param object
     * @param preposition
     * @throws NoSuchElementException
     */
    public void addPreposition(AdvObject object, Preposition preposition) throws NoSuchElementException 
    { 
	if  (this.containsObject(object))
		objects.put(object, preposition);
	else
            throw new NoSuchElementException();
    }

    /**
     *
     * @param object
     * @return
     */
    public Map.Entry<AdvObject, Preposition> getExpression(AdvObject object)
    {
	Set<Map.Entry<AdvObject, Preposition>> entrySet = objects.entrySet();
	Map.Entry<AdvObject, Preposition> returnedValue = null;

	for (Map.Entry<AdvObject, Preposition> entry : entrySet)
	{
		if (entry.getKey() == object)
		{
			returnedValue = entry;
		}	
	}
        
	return returnedValue;
    }

    /**
     *
     * @param object
     * @return
     */
    public boolean containsObject(AdvObject object)
    {
            return objects.containsKey(object);
    }

    /**
     *
     * @param room
     */
    public void addRoom(Room room){this.doorRoom = room;}

    /**
     *
     * @return
     */
    public Room getDoorRoom(){return this.doorRoom; }

    /**
     *
     * @return
     */
    public Map<AdvObject, Preposition> getObjects() {
        return objects;
    }

    /**
     *
     * @return
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /**
     *
     * @return
     */
    public String getCommandToken() {
        return commandToken;
    }
    
    /**
     *
     * @return
     */
    public boolean hasPreposition()
    {
            boolean foundPreposition = false;

                    for (Preposition preposition : objects.values())
                    {
                            if (preposition != null)
                                foundPreposition = true;
                    }

            return foundPreposition;
    }

    /**
     *
     * @return
     * @throws NoSuchElementException
     */
    public AdvObject getObjectWithPreposition() throws NoSuchElementException
    {
            if (!this.hasPreposition())
                    throw new NoSuchElementException();

            AdvObject objectWithPreposition = null;
            for (AdvObject object : objects.keySet())
                    if (objects.get(object) != null)
                            objectWithPreposition = object;

            return objectWithPreposition;
    }

    /**
     *
     * @return
     * @throws NoSuchElementException
     */
    public AdvObject getUniqueObjectWithoutPreposition() throws NoSuchElementException
    {
            if (!this.hasPreposition())
                    throw new NoSuchElementException();

            AdvObject objectWithoutPreposition = null;
            for (AdvObject object : objects.keySet())
                    if (objects.get(object) == null)
                            objectWithoutPreposition = object;

            return objectWithoutPreposition;
    }
    
    
    
    
    
    
}
