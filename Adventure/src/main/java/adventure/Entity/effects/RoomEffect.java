/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.identifiers.ObjectId;

/**
 *
 * 
 * 
  * La classe rappresenta un effetto (eseguito solo dopo aver superato la completeCondition)
 * sulla situazione corrente di una stanza del gioco.
 * 
 * L'effetto viene conservato come composizione di un possibile oggetto {@link ObjectId} da aggiungere alla stanza
 * e un possibile oggetto {@link ObjectId} da rimuovere dalla stanza.
 *
 * @author utente
 */
public class RoomEffect {
    
    /**
     * {@code addingObject} sarà usato per ricercare l'oggetto nell'inventario e inserirlo nella stanza.
     */
    final private ObjectId addingObject;
    
    /**
     * {@code removingObject} sarà usato per ricercare l'oggetto nella stanza, rimuoverlo dalla stanza.
     */
    final private ObjectId removingObject;

    /**
     *
     * @param addingObject
     * @param removingObject
     */
    public RoomEffect(ObjectId addingObject, ObjectId removingObject) {
        this.addingObject = addingObject;
        this.removingObject = removingObject;
    }

    /**
     *
     * @return
     */
    public ObjectId getAddingObject() {
        return addingObject;
    }

    /**
     *
     * @return
     */
    public ObjectId getRemovingObject() {
        return removingObject;
    }
}
