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
 * 
 * La classe rappresenta la descrizione di una qualsiasi avventura/gioco. Può essere estesa per permettere
 * la personalizzazione dell'avventura/gioco.
 */
public abstract class GameDescription {
    
    /**
     * Lista dei comandi validi nell'avventura.
     */
    private final List<Command> commands = new ArrayList();
    
    /**
     * Lista delle stanze presenti nella mappa dell'avventura.
     */
    private final List<Room> rooms = new ArrayList();
    
    /**
     * Inventario della sessione corrente dell'avventura.
     */
    private Inventory inventory = new Inventory(new ArrayList<>());
    
    /**
     * Attributo che memorizza la stanza in cui si trova attualmente il giocatore.
     */
    private Room currentRoom;
    
    /**
     *
     * @throws InconsistentInitializationException
     * @throws PasswordGuessedException
     * @throws EndGameException
     * 
     * Metodo che permette l'inizializzazione di tutti gli oggetti, dei comandi, delle stanze, l'inserimento
     * degli oggetti in queste ultime.
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
     * @param id Id dell'oggetto da recuperare
     * @return Istanza dell'oggetto recuperato
     * @throws NoSuchElementException Se non viene trovata alcuna istanza dell'oggetto 
     * nell'inventario o nelle stanze dell'avventura.
     * 
     * Il metodo ricerca l'oggetto il cui {@link ObjectId} viene passato per parametro, all'interno
     * delle {@link Room} dell'avventura, oppure nell' {@link Inventory} dell'avventura.
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
     * @param commandType Tipo di comando da cercare
     * @return Istanza del comando cercato
     * @throws NoSuchElementException Se non viene trovato alcun comando con quel {@link CommandType}
     * 
     * Il metodo ricerca il command specifico attraverso il {@link CommandType} passato per parametro, e 
     * ne restituisce l'istanza associata ad esso.
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
     * @param roomId Id della stanza da cercare
     * @return Istanza della stanza cercata
     * @throws NoSuchElementException Se non viene trovata alcuna stanza con {@link RoomId} passato per parametro.
     * 
     * Il metodo restituisce la {@link Room} il cui {@link RoomId} è passato per parametro.
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
     * @param object Oggetto da rimuovere dall'inventario
     * @throws NoSuchElementException Se non viene trovato alcun oggetto {@code object} da rimuovere dall'inventario.
     * 
     * Il metodo rimuove un oggetto dall'inventario.
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
     * @param room Stanza nel quale deve essere aggiunto {@link AdvObject}.
     * @param object Oggetto che si vuole aggiungere alla {@link Room}.
     * @throws DuplicateException Se {@code object} è stato già aggiunto alla {@code room}.
     * 
     * Il metodo, data una {@code room} e un {@code object}, aggiunge {@code object} alla {@code room}.
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
     * @param room Stanza dal quale deve essere rimosso {@link AdvObject}.
     * @param object Oggetto che si vuole rimuovere dalla {@link Room}.
     * @throws NoSuchElementException Se {@code object} non è presente nella {@code room}.
     * 
     * Il metodo, data una {@code room} e un {@code object}, rimuove {@code object} dalla {@code room}.
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
