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
 */
public class GameEffect {
    private CurrentPositionEffect currentPositionEffect;
    private InventoryEffect inventoryEffect;
    private LootBagEffect lootBagEffect;
    private RoomEffect roomEffect;
    private Map<ObjectId, ObjectEffect> objectsEffects;
    private SpecialAction specialAction;    

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

    public CurrentPositionEffect getCurrentPositionEffect() {
        return currentPositionEffect;
    }

    public InventoryEffect getInventoryEffect() {
        return inventoryEffect;
    }

    public LootBagEffect getLootBagEffect() {
        return lootBagEffect;
    }

    public RoomEffect getRoomEffect() {
        return roomEffect;
    }

    public Map<ObjectId, ObjectEffect> getObjectsEffects() {
        return objectsEffects;
    }

    public SpecialAction getSpecialAction() {
        return specialAction;
    }
    
    
}
