/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;
import adventure.Entity.objects.AdvObject;
import adventure.identifiers.ObjectId;
import java.util.List;
import java.util.ArrayList;
import adventure.exceptions.*;
import java.util.NoSuchElementException;


/**
 *
 * @author Paolo
 */
public class Inventory {
    
    private List<AdvObject> objects = new ArrayList<>();
    
    
    public Inventory(List<AdvObject> objects){
        this.objects = objects;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }
    
    public void remove(AdvObject object) throws NoSuchElementException{
        if (!objects.contains(object))
            throw new NoSuchElementException();
        
        objects.remove(object);
    }
    
    
    public void add(AdvObject object) throws DuplicateException{
        if (objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
        
    }
    
    public AdvObject getObjectById(ObjectId Id) throws NoSuchElementException{
        
        for (AdvObject object : objects){
            if (object.getId() == Id){
                return object;
            }
        }
        
        throw new NoSuchElementException();
    }
    
    
    public boolean contains(AdvObject object){
        if (objects.contains(object))
            return true;
        else
            return false;
                    
    }
    
    
}

