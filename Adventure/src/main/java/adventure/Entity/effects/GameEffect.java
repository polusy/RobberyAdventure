/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.effects;

import java.util.Map;

import adventure.Entity.effects.CurrentPositionEffect;
import adventure.identifiers.ObjectId;
import adventure.exceptions.InconsistentInitializationException;


/**
 *
 * @author utente
 * 
 * La classe rappresenta l'insieme di tutti i possibili effetti che potrebbero essere eseguiti
 * sulla situazione corrente della sessione di gioco.
 * 
 * L'effetto viene conservato come composizione di effetti:
 * - su posizione corrente del giocatore nella mappa del gioco {@link CurrentPositionEffect}.
 * - sull'inventario del gioco (rimozione o inserimento di oggetti) {@link InventoryEffect}.
 * - sulla stanza corrente del gioco (riomozione o inserimento di oggetti){@link LootBagEffect}.
 * - sulla lootBag del gioco (inserimento di oggetti){@link RoomEffect}.
 * - sugli oggetti del gioco (modifica di valori di alcune proprietà di oggetti o della loro visibilità){@link objectsEffects}.
 * - azione speciale generica sulla sessione di gioco{@link specialAction}.
 *
 */
public class GameEffect {
    private CurrentPositionEffect currentPositionEffect;
    private InventoryEffect inventoryEffect;
    private LootBagEffect lootBagEffect;
    private RoomEffect roomEffect;
    private Map<ObjectId, ObjectEffect> objectsEffects;
    private SpecialAction specialAction;    

    /**
     *
     * @param currentPositionEffect Nuova Posizione corrente del giocatore nella mappa del gioco.
     * @param inventoryEffect Effetto sull'inventario del gioco (rimozione o inserimento di oggetti).
     * @param lootBagEffect Effetto sulla lootBag del gioco (inserimento di oggetti).
     * @param roomEffect Effetto sulla stanza del gioco (rimozione o inserimento di oggetti).
     * @param objectsEffects Effetto sugli oggetti del gioco (modifica di valori di alcune proprietà di oggetti o della loro visibilità).
     * @param specialAction Azione speciale generica sulla sessione di gioco.
     * 
     * @throws InconsistentInitializationException Se si cerca di inizializzare gli effetti portandoli in uno 
     * stato inconsistente.
     * 
     * Il metodo è un banale costruttore, tuttavia si accerta che non ci siano assegnamenti inconsistenti tra gli effetti
     * da eseguire sul gioco.
     * e.g. rimozione di oggetto dalla stanza e rimozione dello stesso oggetto dall'inventario, etc...
     */
    public GameEffect(CurrentPositionEffect currentPositionEffect, InventoryEffect inventoryEffect,
           LootBagEffect lootBagEffect, RoomEffect roomEffect, Map<ObjectId, ObjectEffect> objectsEffects,
           SpecialAction specialAction) throws InconsistentInitializationException {
        
        
        if (roomEffect != null && inventoryEffect != null && lootBagEffect != null)
        {
            
                if (!inventoryEffect.getRemovingObject().equals(roomEffect.getAddingObject()))
                    throw new InconsistentInitializationException();

                if (!inventoryEffect.getAddingObject().equals(roomEffect.getRemovingObject()))
                    throw new InconsistentInitializationException();
            
                if (!lootBagEffect.getAddingObject().equals(roomEffect.getRemovingObject()))
                    throw new InconsistentInitializationException();
                

                    if (!roomEffect.getRemovingObject().equals(inventoryEffect.getAddingObject()) 
                            || !roomEffect.getRemovingObject().equals(lootBagEffect.getAddingObject()))
                    {
                            throw new InconsistentInitializationException();
                    }
                   
            
        }

            ContainerEffect containerEffect = null;
        
        if (objectsEffects != null)
        {

            for (Map.Entry<ObjectId, ObjectEffect> objectEffects: objectsEffects.entrySet())
            {
                if (objectEffects.getValue().getContainerEffect() != null)
                {
                    containerEffect = objectEffects.getValue().getContainerEffect();
                    if (!containerEffect.getRemovingObject().equals(roomEffect.getRemovingObject()))
                        throw new InconsistentInitializationException();   
                }
            }
        }
        
        this.currentPositionEffect = currentPositionEffect;
        this.inventoryEffect = inventoryEffect;
        this.lootBagEffect = lootBagEffect;
        this.roomEffect = roomEffect;
        this.objectsEffects = objectsEffects;
        this.specialAction = specialAction;
    }

    /**
     *
     * @return
     */
    public CurrentPositionEffect getCurrentPositionEffect() {
        return currentPositionEffect;
    }

    /**
     *
     * @return
     */
    public InventoryEffect getInventoryEffect() {
        return inventoryEffect;
    }

    /**
     *
     * @return
     */
    public LootBagEffect getLootBagEffect() {
        return lootBagEffect;
    }

    /**
     *
     * @return
     */
    public RoomEffect getRoomEffect() {
        return roomEffect;
    }

    /**
     *
     * @return
     */
    public Map<ObjectId, ObjectEffect> getObjectsEffects() {
        return objectsEffects;
    }

    /**
     *
     * @return
     */
    public SpecialAction getSpecialAction() {
        return specialAction;
    }
    
    
}
