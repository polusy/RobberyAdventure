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
 */
public class CurrentPositionEffect {
    /**
     * L'effetto viene conservato come {@link RoomId} della nuova stanza corrente {@code newCurrentRoom}.
     * Esso sarà usato per impostare la stanza associata a questo id
     * come nuova currentRoom all'interno della gameDescription.
     */
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
