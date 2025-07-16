/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import adventure.identifiers.ObjectId;

/**
 *
 * @author Paolo
 * 
 * La classe rappresenta un effetto (eseguito solo dopo aver superato la completeCondition)
 * sulla situazione corrente nella lootBag della sessione.
 * 
 * L'effetto viene conservato come composizione di un possibile oggetto (id) da aggiungere alla lootBag
 *
 * L'id addingObject sarà usato per ricercare l'oggetto nella stanza e inserirlo nella lootBag.
 */
public class LootBagEffect {

    private ObjectId addingObject;

    /**
     *
     * @param addingObject
     */
    public LootBagEffect(ObjectId addingObject) {
        this.addingObject = addingObject;
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
     * @param addingObject
     */
    public void setAddingObject(ObjectId addingObject) {
        this.addingObject = addingObject;
    }
    
}
