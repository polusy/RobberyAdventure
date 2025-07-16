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
 * 
 * 
 * la classe estende {@link Property} e si differenzia da {@link propertyWithValue}.
 * La classe modella la proprietà di Contenitore di oggetti, non conservando uno stato, ma una collection di oggetti
 * che un altro oggetto potrebbe contenere.
 * 
 * @author Paolo
 */
public class Container extends Property{
    
    /**
     * lista di oggetti
     * che l'oggetto Container ha al suo interno.
     */
    
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
     * Il metodo aggiunge un oggetto agli oggetti del Container.
     * 
     * @param objectId Id dell'oggetto da aggiungere al container.
     * @throws DuplicateException Sel'oggetto esiste già.
     * 
     * 
     */
    public void addObject(ObjectId objectId)throws DuplicateException {
       
       if (objects.contains(objectId))
           throw new DuplicateException();
       
       objects.add(objectId);    
   }
   
    /**
     *
     * Il metodo rimuove un oggetto dagli oggetti del Container.
     * 
     * @param objectId Id dell'oggetto da rimuovere
     * @throws NoSuchElementException Se non viene trovato l'oggetto da rimuovere.
     * 
     * 
     */
    public void removeObject(ObjectId objectId)throws NoSuchElementException {
       
       if (!objects.contains(objectId))
           throw new NoSuchElementException();
       
       objects.remove(objectId); 
   }
    
    /**
     *
     * Il metodo verifica che un certo oggetto sia o meno nel Container.
     * 
     * @param objectId Oggetto di cui si vuole verificare l'esistenza nel container
     * @return Boolean rappresentante esistenza dell'oggetto nel container.
     * 
     * 
     */
    public boolean hasObject(ObjectId objectId){
        return objects.contains(objectId);
    }
    
}
