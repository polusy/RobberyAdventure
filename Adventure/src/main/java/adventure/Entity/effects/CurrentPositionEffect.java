/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;
import adventure.identifiers.RoomId;

/**
 *
 * @author Paolo
 */
public class CurrentPositionEffect {
    
    private RoomId newCurrentRoom;

    public CurrentPositionEffect(RoomId newCurrentRoom) {
        this.newCurrentRoom = newCurrentRoom;
    }

    public RoomId getNewCurrentRoom() {
        return newCurrentRoom;
    }

    public void setNewCurrentRoom(RoomId newCurrentRoom) {
        this.newCurrentRoom = newCurrentRoom;
    }
    
    
    
    
    
    
    
    
    
}
