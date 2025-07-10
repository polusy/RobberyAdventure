/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.objects.AdvObject;
import adventure.identifiers.*;
import adventure.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 *
 * @author Paolo
 */
public abstract class GameDescription {
    
    private final List<Command> commands = new ArrayList();
    private final List<Room> rooms = new ArrayList();
    private Inventory inventory;
    private Room currentRoom;
    
    
    public abstract void init();
    
    
    public AdvObject getObjectById(ObjectId id) throws NoSuchElementException
    {
	AdvObject object = null;
	Room room;
	Iterator roomsIterator = rooms.iterator();
	
	while (roomsIterator.hasNext() && object == null)
	{
		room = (Room) roomsIterator.next();
		try {
			object = room.getObject(id);
		} catch (NoSuchElementException exception){};
	}
	
	if (object == null)
	{
            object = inventory.getObjectById(id);
	}
	
	return object;
    }

    public Command getCommandById(CommandType commandType) throws NoSuchElementException
    {
	for (Command command : commands)
	{
		if (command.getType() == commandType)
                    return command;
	}
	
	throw new NoSuchElementException();
    }



    public Room getRoomById(RoomId roomId) throws NoSuchElementException
    {
	for (Room room : rooms)
	{
            if (room.getId() == roomId)
		return room;
	}

	throw new NoSuchElementException();
    }

    public void addObjectToinventory(AdvObject object) throws DuplicateException
    {
	if (!inventory.contains(object))
		inventory.add(object);
	else
            throw new DuplicateException();
    }

    public void removeObjectFromInventory(AdvObject object) throws NoSuchElementException
    {
	if (inventory.contains(object))
		inventory.remove(object);
	else
		throw new NoSuchElementException();
    }

    public void addObjectToRoom(Room room, AdvObject object) throws DuplicateException
    {
        if (room.contains(object))
            throw new DuplicateException();
        else
            room.addObject(object);
    }

    public void removeObjectFromRoom(Room room, AdvObject object) throws NoSuchElementException
    {
        if (!room.contains(object))
            throw new NoSuchElementException();
        else
            room.removeObject(object);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    

    
    
 
}
