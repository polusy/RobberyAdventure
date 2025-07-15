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
    private Inventory inventory = new Inventory(new ArrayList<>());
    private Room currentRoom;
    
    /**
     *
     * @throws InconsistentInitializationException
     * @throws PasswordGuessedException
     * @throws EndGameException
     */
    public abstract void init() throws InconsistentInitializationException, PasswordGuessedException, 
            EndGameException;

    /**
     *
     * @return
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     *
     * @param id
     * @return
     * @throws NoSuchElementException
     */
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

    /**
     *
     * @param commandType
     * @return
     * @throws NoSuchElementException
     */
    public Command getCommandByType(CommandType commandType) throws NoSuchElementException
    {
	for (Command command : commands)
	{
		if (command.getType() == commandType)
                    return command;
	}
	
	throw new NoSuchElementException();
    }

    /**
     *
     * @param roomId
     * @return
     * @throws NoSuchElementException
     */
    public Room getRoomById(RoomId roomId) throws NoSuchElementException
    {
	for (Room room : rooms)
	{
            if (room.getId() == roomId)
		return room;
	}

	throw new NoSuchElementException();
    }

    /**
     *
     * @param object
     * @throws DuplicateException
     */
    public void addObjectToinventory(AdvObject object) throws DuplicateException
    {
	if (!inventory.contains(object))
		inventory.add(object);
	else
            throw new DuplicateException();
    }

    /**
     *
     * @param object
     * @throws NoSuchElementException
     */
    public void removeObjectFromInventory(AdvObject object) throws NoSuchElementException
    {
	if (inventory.contains(object))
		inventory.remove(object);
	else
		throw new NoSuchElementException();
    }

    /**
     *
     * @param room
     * @param object
     * @throws DuplicateException
     */
    public void addObjectToRoom(Room room, AdvObject object) throws DuplicateException
    {
        if (room.contains(object))
            throw new DuplicateException();
        else
            room.addObject(object);
    }

    /**
     *
     * @param room
     * @param object
     * @throws NoSuchElementException
     */
    public void removeObjectFromRoom(Room room, AdvObject object) throws NoSuchElementException
    {
        if (!room.contains(object))
            throw new NoSuchElementException();
        else
            room.removeObject(object);
    }

    /**
     *
     * @return
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     *
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    
    
    

    
    
 
}
