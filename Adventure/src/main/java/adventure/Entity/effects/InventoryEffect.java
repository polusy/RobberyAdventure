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
 * sulla situazione corrente nell'inventario della sessione.
 * 
 * L'effetto viene conservato come composizione di un possibile {@link ObjectId}, il cui oggetto associato è da aggiungere all'inventory
 * e un possibile {@link ObjectId}, il cui oggetto associato  è da rimuovere dall'inventory.
 *
 * {@code addingObject} sarà usato per ricercare l'oggetto nella stanza e inserirlo nell'inventario.
 * {@code removingObject} sarà usato per ricercare l'oggetto nell'inventario, rimuoverlo dall'inventario.
 */
public class InventoryEffect {
    private ObjectId addingObject;
    private ObjectId removingObject;

    /**
     *
     * @param addingObject
     * @param removingObject
     */
    public InventoryEffect(ObjectId addingObject, ObjectId removingObject) {
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
