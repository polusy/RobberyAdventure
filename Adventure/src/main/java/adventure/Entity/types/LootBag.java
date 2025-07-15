/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import java.util.NoSuchElementException;

import adventure.Entity.objects.ValuableObject;
import adventure.Entity.objects.AdvObject;
import java.util.ArrayList;
import java.util.List;
import adventure.exceptions.*;
import adventure.identifiers.ObjectId;

/**
 *
 * @author Paolo
 */
public class LootBag {
    
    private final List<ValuableObject> objects;
    
    /**
     *
     */
    public LootBag(){
        objects = new ArrayList();
    }

    /**
     *
     * @return
     */
    public List<ValuableObject> getObjects() {
        return objects;
    }
    
    /**
     *
     * @param object
     * @throws DuplicateException
     */
    public void add(ValuableObject object) throws DuplicateException{
        
        if (objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
    }
    
    /**
     *
     * @return
     */
    public int getTotalStolenAmount(){
        int sum = 0;
        for (ValuableObject valuableObject : objects){
            sum = sum + valuableObject.getValue();
        }
        return sum;
    }
    
    /**
     *
     * @param object
     * @return
     */
    public boolean contains(AdvObject object){
        if (objects.contains(object))
               return true;
        else 
            return false;
    }
    
    /**
     *
     * @param objectId
     * @return
     */
    public boolean contains(ObjectId objectId){
        boolean contained = false;
        
        for (AdvObject object : objects){
            if (object.getId() == objectId)
                contained = true;
        }    
        
        return contained;
    }
    
    /**
     *
     * @param Id
     * @return
     * @throws NoSuchElementException
     */
    public AdvObject getObjectById(ObjectId Id) throws NoSuchElementException{
        
        for (AdvObject object : objects){
            if (object.getId() == Id){
                return object;
            }
        }
        
        throw new NoSuchElementException();
    }    
    
    
}
