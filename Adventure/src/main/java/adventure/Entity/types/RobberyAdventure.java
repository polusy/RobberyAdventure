/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.ValuableObject;
import adventure.exceptions.*;

/**
 *
 * @author Paolo
 */
public class RobberyAdventure extends GameDescription{
    
    private LootBag lootBag;

    public LootBag getLootBag() {
        return lootBag;
    }
    
    public void addObjectToLootBag(AdvObject object)throws DuplicateException, IllegalArgumentException{
        
        if (lootBag.contains(object))
            throw new DuplicateException();
        
        if (object instanceof ValuableObject)
            lootBag.add((ValuableObject)object);
        else 
            throw new IllegalArgumentException();
    }
    
    
    
    @Override
    public void init(){};
    
    
    
}
