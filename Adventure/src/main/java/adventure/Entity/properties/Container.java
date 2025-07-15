/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.properties;
import adventure.identifiers.ObjectId;
import adventure.exceptions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author Paolo
 */
public class Container extends Property{
    
   private final Set<ObjectId> objects;
   
    /**
     *
     * @param objects
     */
    public Container(ObjectId[] objects){
       this.objects = new HashSet<>(Arrays.asList(objects));
   }
   
    /**
     *
     * @param objectId
     * @throws DuplicateException
     */
    public void addObject(ObjectId objectId)throws DuplicateException {
       
       if (objects.contains(objectId))
           throw new DuplicateException();
       
       objects.add(objectId);    
   }
   
    /**
     *
     * @param objectId
     * @throws NoSuchElementException
     */
    public void removeObject(ObjectId objectId)throws NoSuchElementException {
       
       if (!objects.contains(objectId))
           throw new NoSuchElementException();
       
       objects.remove(objectId); 
   }
    
    /**
     *
     * @param objectId
     * @return
     */
    public boolean hasObject(ObjectId objectId){
        return objects.contains(objectId);
    }
    
}
