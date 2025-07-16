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
 * 
 * la classe eredita da Property e si differenzia da una propertyWithValue, in quanto conserva solo una lista di oggetti
 * che l'oggetto Container ha al suo interno.
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
     * @param objectId Id dell'oggetto da aggiungere al container.
     * @throws DuplicateException Sel'oggetto esiste già.
     * 
     * Il metodo aggiunge un oggetto agli oggetti del Container.
     */
    public void addObject(ObjectId objectId)throws DuplicateException {
       
       if (objects.contains(objectId))
           throw new DuplicateException();
       
       objects.add(objectId);    
   }
   
    /**
     *
     * @param objectId Id dell'oggetto da rimuovere
     * @throws NoSuchElementException Se non viene trovato l'oggetto da rimuovere.
     * 
     * Il metodo rimuove un oggetto dagli oggetti del Container.
     */
    public void removeObject(ObjectId objectId)throws NoSuchElementException {
       
       if (!objects.contains(objectId))
           throw new NoSuchElementException();
       
       objects.remove(objectId); 
   }
    
    /**
     *
     * @param objectId Oggetto di cui si vuole verificare l'esistenza nel container
     * @return Boolean rappresentante esistenza dell'oggetto nel container.
     * 
     * Il metodo verifica che un certo oggetto sia o meno nel Container.
     */
    public boolean hasObject(ObjectId objectId){
        return objects.contains(objectId);
    }
    
}
