/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.RoomId;
import adventure.identifiers.ObjectId;
import adventure.Entity.objects.Door;
import adventure.Entity.objects.AdvObject;
import adventure.exceptions.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/** Stanza che compone la mappa del gioco
 * 
 * La classe conserva l'identificativo della stanza, il suo nome, la descrizione, i collegamenti verso le altre stanze
 * e le eventuali corrispondenti porte, dove la corrispodenza è rispettivamente tra il punto cardinale e la stanza ,
 * e il punto cardinale e la porta.
 * La classe conserva infine tutti gli oggetti presenti all'istante corrente nella stanza, nella sessione di gioco
 *
 * @author Paolo
 */
public class Room {
    
    final private RoomId id;
    private String name;
    private String description;
    private Map<CardinalPoint, Room> roomLinks = new HashMap<>();
    private Map<CardinalPoint, Door> doorLinks = new HashMap<>();
    private List<AdvObject> objects = new ArrayList<>();
    
    /**
     *
     * @param id Identificativo della stanza
     * @param name Nome della stanza
     * @param description Descrizione della stanza
     * @param roomLinks Collegamenti della stanza con altre stanze, ciascuna associata ad un punto cardianale
     * @param doorLinks Collegamenti della stanza con degli oggetti {@link Door}, ciacuno associato ad un punto cardinale
     * @param objects Oggetti del gioco presenti nella stanza all'istante corrente, nella sessione di gioco in corso
     */
    public Room(RoomId id, String name, String description, Map<CardinalPoint, Room> roomLinks, Map<CardinalPoint, Door> doorLinks, List<AdvObject> objects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roomLinks = roomLinks;
        this.doorLinks = doorLinks;
        this.objects = objects;
    }

    /**
     *
     * @param id Identificativo della stanza
     * @param name Nome della stanza
     * @param description Descrizione della stanza
     */
    public Room(RoomId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    /**
     *
     * @param object Oggetto del gioco da aggiungere alla stanza
     * @throws DuplicateException
     */
    public void addObject(AdvObject object) throws DuplicateException{
        if(objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
    }
    
    /**
     *
     * @param object Oggetto del gioco da rimuovere dalla stanza
     * @throws NoSuchElementException
     */
    public void removeObject(AdvObject object) throws NoSuchElementException{
        
        if(!objects.contains(object))
            throw new NoSuchElementException();
        
        objects.remove(object);
    }
    
    /** Il metodo associa la stanza {@code room} al punto cardinale {@code cardinalPoint}, se tale punto cardinale non è
     * già assegnato ad un'altra stanza, altrimenti lancia un'eccezione {@link DuplicateException}
     *
     * @param cardinalPoint Punto cardinale a cui associare la stanza
     * @param room Stanza da associare al punto cardinale
     * @throws DuplicateException
     */
    public void addRoomLink(CardinalPoint cardinalPoint, Room room) throws DuplicateException {

	if (roomLinks.containsKey(cardinalPoint))
		throw new DuplicateException();
	else
		roomLinks.put(cardinalPoint, room);
}

    /** Il metodo associa la porta {@code door} al punto cardinale {@code cardinalPoint}, se tale punto cardinale non è
     * già assegnato ad un'altra porta, altrimenti lancia un'eccezione {@link DuplicateException}
     *
     * @param cardinalPoint
     * @param door
     * @throws DuplicateException
     */
    public void addDoorLink(CardinalPoint cardinalPoint, Door door) throws DuplicateException {

	if (doorLinks.containsKey(cardinalPoint))
		throw new DuplicateException();
	else
		doorLinks.put(cardinalPoint, door);
    }

    /** Il metodo se la stanza sul quale il metodo viene invocato ha un collegamento verso la stanza {@code room}
     * passata per parametro
     * 
     * @param room Stanza rispetto a cui si vuole verificare la raggiungibilità
     * @return true se la stanza sul quale il metodo viene invocato può raggiungere direttamente la stanza {@code room}
     * passata per parametro, false altrimenti
     */
    public boolean reaches(Room room)
    {
	boolean result = false;
	
	if (roomLinks.values().contains(room))
		result = true;
	
	return result;
    }

    /**
     *
     * @param object
     * @return true se la stanza sulla quale il metodo viene invocato contiene l'oggetto {@code object} passato per parametro,
     * false altrimenti
     */
    public boolean contains(AdvObject object){
	return objects.contains(object);
}

    /** Il metodo restituisce l'oggetto presente nella stanza all'istante attuale, avente identificativo uguale al paramtro
     * {@code id}, se questo è presente, altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @param id Identificativo dell'oggetto che si desidera recuperare
     * @return Oggetto avente identificativo uguale al parametro {@code id}
     * @throws NoSuchElementException
     */
    public AdvObject getObject(ObjectId id) throws NoSuchElementException {
        for (AdvObject object : objects) {
            if (object.getId() == id) {
                return object;
            }
        }

        throw new NoSuchElementException();
    }

    /** Il metodo restituisce la stanza che la stanza sulla quale il metodo viene invocato, può raggiungere direttamente al
     * punto cardinale {@code cardinalPoint} se presente, altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @param cardinalPoint Punto cardinale del quale si desidera la stanza associata
     * @return Stanza associata al punto cardinale {@code cardinalPoint}
     * @throws NoSuchElementException
     */
    public Room getLinkedRoom(CardinalPoint cardinalPoint) throws NoSuchElementException
    {
	if (roomLinks.containsKey(cardinalPoint))
		return roomLinks.get(cardinalPoint);

	throw new NoSuchElementException();
    } 

    /** Il metodo restituisce la porta associata al punto cardinale {@code cardinalPoint} se presente, 
     * altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @param cardinalPoint Punto cardinale del quale si desidera la porta associata
     * @return Porta associata al punto cardinale {@code cardinalPoint}
     * @throws NoSuchElementException
     */
    public Door getLinkedDoor(CardinalPoint cardinalPoint) throws NoSuchElementException
    {
	if (doorLinks.containsKey(cardinalPoint))
		return doorLinks.get(cardinalPoint);

	throw new NoSuchElementException();
    }

    /** Il metodo restituisce il punto cardinale a cui la porta {@code door} passata per parametro è associata
     *
     * @param door Porta di cui si desidera il relativo punto cardinale
     * @return Punto cardinale a cui la porta passata per parametro è associata, null se tale associazione non esiste
     */
    public CardinalPoint getCardinalPointByDoor(Door door){
	CardinalPoint cardinalPoint = null;

	for (Map.Entry<CardinalPoint, Door> entry : doorLinks.entrySet())
		if (entry.getValue().equals(door))
			cardinalPoint = entry.getKey();

	return cardinalPoint;
    }

    /**
     *
     * @return
     */
    public RoomId getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    /** Il metodo restituisce il messaggio di descrizione della stanza in cui il giocatore si trova all'istante corrente, 
     * con gli oggetti contenuti al suo interno
     *
     * @return Descrizione della stanza corrente con l'elenco dei suoi oggetti, separati da virgole
     */
    public String getLook(){
        StringBuilder look = new StringBuilder("Vediamo un po', ci sono questi oggetti: " + System.lineSeparator());
        
        List<AdvObject> visibleObjects = objects.stream()
			.filter(object -> object.isVisible())
			.collect(Collectors.toList()); 

        List<String> objectNames = new ArrayList<>();
        objectNames.addAll(
            visibleObjects.stream()
                .filter(object -> !(object instanceof Door))
                .map(object -> object.getName())
                .collect(Collectors.toList())
        );
        
        objectNames.addAll(
            visibleObjects.stream()
                .filter(object -> object instanceof Door)
                .map(object -> { 
                    Door door = (Door) object;
                    return (door.getName() + " " + this.getLinkedRoom(this.getCardinalPointByDoor(door)).getName());
                })
                .collect(Collectors.toList())
        );

	for (int i = 0; i < objectNames.size(); i++)
	{
		look.append(objectNames.get(i));
		if (i  < objectNames.size() - 1)
                    look.append(", ");
	}

	return look.toString();
        
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     *
     * @return
     */
    public Map<CardinalPoint, Room> getRoomLinks() {
        return roomLinks;
    }

    /**
     *
     * @return
     */
    public Map<CardinalPoint, Door> getDoorLinks() {
        return doorLinks;
    }
    
    
    
    
    
    
    
    
    
    
    
}
