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
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Paolo
 */
public class Room {
    
    final private RoomId id;
    private String name;
    private String description;
    Map<CardinalPoint, Room> roomLinks;
    Map<CardinalPoint, Door> doorLinks;
    final private List<AdvObject> objects;
    

    public Room(RoomId id, String name, String description, Map<CardinalPoint, Room> roomLinks, Map<CardinalPoint, Door> doorLinks, List<AdvObject> objects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roomLinks = roomLinks;
        this.doorLinks = doorLinks;
        this.objects = objects;
    }
    
    
    public void addObject(AdvObject object) throws DuplicateException{
        if(objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
    }
    
    public void removeObject(AdvObject object) throws NoSuchElementException{
        
        if(!objects.contains(object))
            throw new NoSuchElementException();
        
        objects.remove(object);
    }
    
    
    public void addRoomLink(CardinalPoint cardinalPoint, Room room) throws DuplicateException {

	if (roomLinks.containsKey(cardinalPoint))
		throw new DuplicateException();
	else
		roomLinks.put(cardinalPoint, room);
}

    public void addDoorLink(CardinalPoint cardinalPoint, Door door) throws DuplicateException {

	if (doorLinks.containsKey(cardinalPoint))
		throw new DuplicateException();
	else
		doorLinks.put(cardinalPoint, door);
    }

    public boolean reaches(Room room)
{
	boolean result = false;
	
	if (roomLinks.values().contains(room))
		result = true;
	
	return result;
}

    public boolean contains(AdvObject object){
	return objects.contains(object);
}
    public AdvObject getObject(ObjectId id) throws NoSuchElementException {
        for (AdvObject object : objects) {
            if (object.getId() == id) {
                return object;
            }
        }

        throw new NoSuchElementException();
    }

    public Room getLinkedRoom(CardinalPoint cardinalPoint) throws NoSuchElementException
    {
	if (roomLinks.containsKey(cardinalPoint))
		return roomLinks.get(cardinalPoint);

	throw new NoSuchElementException();
    } 

    public Door getLinkedDoor(CardinalPoint cardinalPoint) throws NoSuchElementException
    {
	if (doorLinks.containsKey(cardinalPoint))
		return doorLinks.get(cardinalPoint);

	throw new NoSuchElementException();
    }

    public CardinalPoint getCardinalPointByDoor(Door door){
	CardinalPoint cardinalPoint = null;

	for (Map.Entry<CardinalPoint, Door> entry : doorLinks.entrySet())
		if (entry.getValue().equals(door))
			cardinalPoint = entry.getKey();

	return cardinalPoint;
    }

    public RoomId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    
    public String getLook(){
        StringBuilder look = new StringBuilder("Vediamo un po', ci sono questi oggetti: %n");
        
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
                    return (door.getName() + this.getLinkedRoom(this.getCardinalPointByDoor(door)).getName());
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

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

    public String getDescription() {
        return description;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }
    
    
    
    
    
    
    
    
    
}
