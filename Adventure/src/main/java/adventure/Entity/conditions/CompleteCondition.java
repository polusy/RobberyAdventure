/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.conditions;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import adventure.identifiers.ObjectId;


/**
 *
 * @author utente
 * 
 * La classe rappresenta la condizione di soddisfacimento completa (relativa ad ogni proprietà di oggetto) necessaria per  eseguire effetti
 * sulla sessione di gioco (Modifica di proprietà di altri oggetti, etc...) ed individuare il messaggio relativo
 * al superamento delle condizioni necessarie.
 */
public class CompleteCondition {
    final private List<InventoryCondition> inventoryConditionOptions; 
    final private Map<ObjectId, ObjectCondition> objectsConditions;

    /**
     *
     * @param inventoryConditionOptions Condizioni sull'inventario della sessione di gioco.
     * @param objectsConditions Condizioni sulle proprietà degli altri oggetti del gioco.
     */
    public CompleteCondition(List<InventoryCondition> inventoryConditionOptions, Map<ObjectId, ObjectCondition> objectsConditions) {
        this.inventoryConditionOptions = inventoryConditionOptions;
        this.objectsConditions = objectsConditions;
    }
    
    /**
     *
     * @return L'unica condizione di inventario, se singola.
     * @throws NoSuchElementException Se non viene trovata alcuna condizione di inventario.NoSuchElementException
     * 
     * Il metodo restituisce l'unica condizione di inventario (lista di oggetti che necessariamente devono essere presenti
     * nell'inventario per modificare quella proprietà dell'oggetto), presente nella lista delle possibili condizioni dell'inventario.
     */
    public InventoryCondition getUniqueInventoryCondition() throws NoSuchElementException{
	if (inventoryConditionOptions != null && inventoryConditionOptions.size() == 1){
		return inventoryConditionOptions.get(0);
	}
	else{
		throw new NoSuchElementException();
	}
    }   

    /**
     *
     * @return
     */
    public List<InventoryCondition> getInventoryConditionOptions() {
        return inventoryConditionOptions;
    }

    /**
     *
     * @return
     */
    public Map<ObjectId, ObjectCondition> getObjectsConditions() {
        return objectsConditions;
    }
    
    
    
}
