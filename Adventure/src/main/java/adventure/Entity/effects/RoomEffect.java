/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.identifiers.ObjectId;

/**
 *
 * @author utente
 * 
  * La classe rappresenta un effetto (eseguito solo dopo aver superato la completeCondition)
 * sulla situazione corrente di una stanza del gioco.
 * 
 * L'effetto viene conservato come composizione di un possibile oggetto {@link ObjectId} da aggiungere alla stanza
 * e un possibile oggetto (id) da rimuovere dalla stanza.
 *
 * {@code addingObject} sarà usato per ricercare l'oggetto nell'inventario e inserirlo nella stanza.
 * {@code removingObject} sarà usato per ricercare l'oggetto nella stanza, rimuoverlo dalla stanza.
 */
public class RoomEffect {
    final private ObjectId addingObject;
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
