/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;


import adventure.Entity.objects.ValuableObject;
import adventure.Entity.objects.AdvObject;
import java.util.ArrayList;
import java.util.List;
import adventure.exceptions.*;

/**
 *
 * @author Paolo
 */
public class LootBag {
    
    private final List<ValuableObject> objects;
    
    public LootBag(){
        objects = new ArrayList();
    }

    public List<ValuableObject> getObjects() {
        return objects;
    }
    
    public void add(ValuableObject object) throws DuplicateException{
        
        if (objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
    }
    
    public int getTotalStolenAmount(){
        int sum = 0;
        for (ValuableObject valuableObject : objects){
            sum = sum + valuableObject.getValue();
        }
        return sum;
    }
    
    public boolean contains(AdvObject object){
        if (objects.contains(object))
               return true;
        else 
            return false;
    }
    
}
