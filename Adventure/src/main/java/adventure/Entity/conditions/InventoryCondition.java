/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;

import adventure.identifiers.ObjectId;
import adventure.Entity.objects.AdvObject;
import java.util.List;

/**
 *
 * 
 * 
 * La classe rappresenta una condizione sull'inventario (lista di oggetti necessariamente presenti in esso) che deve essere rispettata per permettere di modificare
 * una qualsiasi delle proprietà di un oggetto.
 * 
 * @author Paolo
 * 
 */
public class InventoryCondition {
    
    /*
    * Lista degli oggetti che devono essere necessariamente presenti nell'inventario per il superamento della condizione sull'inventario.
    */
    final private List<ObjectId> necessaryObjects;

    /**
     *
     * @param necessaryObjects
     */
    public InventoryCondition(List<ObjectId> necessaryObjects) {
        this.necessaryObjects = necessaryObjects;
    }

    /**
     *
     * @return
     */
    public List<ObjectId> getNecessaryObjects() {
        return necessaryObjects;
    }
    
    /**
     *
     * @param object Oggetto di cui si vuole verificare l'esistenza nella lista d'istanza.
     * @return booleano rappresentante il risultato della ricerca dell'oggetto nella lista.
     */
    public boolean contains(AdvObject object){
        if (necessaryObjects.contains(object))
            return true;
        else
            return false;
        
    }
    
    /**
     *
     * @param objectId Id di Oggetto di cui si vuole verificare l'esistenza nella lista d'istanza.
     * @return booleano rappresentante il risultato (esistenza o inesistenza) della ricerca dell'oggetto nella lista.
     */
    public boolean contains(ObjectId objectId){
    if (necessaryObjects.contains(objectId))
        return true;
    else
        return false;

    }
    
    
    
}
