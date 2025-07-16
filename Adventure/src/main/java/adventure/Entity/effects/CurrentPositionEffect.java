/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;
import adventure.identifiers.RoomId;

/**
 *
 * @author Paolo
 * 
 * La classe rappresenta un effetto (eseguito solo dopo aver superato la completeCondition)
 * sulla posizione corrente del giocatore nella partita.
 * 
 * L'effetto viene conservato come l'id della nuova stanza corrente.
 * 
 * Esso sarà usato per impostare la stanza associata a questo id
 * come nuova currentRoom all'interno della gameDescription.
 */
public class CurrentPositionEffect {
    
    private RoomId newCurrentRoom;

    /**
     *
     * @param newCurrentRoom
     */
    public CurrentPositionEffect(RoomId newCurrentRoom) {
        this.newCurrentRoom = newCurrentRoom;
    }

    /**
     *
     * @return
     */
    public RoomId getNewCurrentRoom() {
        return newCurrentRoom;
    }

    /**
     *
     * @param newCurrentRoom
     */
    public void setNewCurrentRoom(RoomId newCurrentRoom) {
        this.newCurrentRoom = newCurrentRoom;
    }
    
    
    
    
    
    
    
    
    
}
