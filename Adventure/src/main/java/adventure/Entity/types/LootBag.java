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

/** Zaino della refurtiva del giocatore, in una sessione di gioco
 *
 * @author Paolo
 */
public class LootBag {
    
    private final List<ValuableObject> objects;
    
    /** Costruisce un oggetto LootBag che non contiene alcun oggetto del gioco
     *
     */
    public LootBag(){
        objects = new ArrayList();
    }

    /**
     *
     * @return Oggetti contenuti nello zaino dela refurtiva rappresentato dall'oggetto sul quale il metodo viene invocato
     */
    public List<ValuableObject> getObjects() {
        return objects;
    }
    
    /** Il metodo inserisce l'oggetto rappresentato dal parametro object nello zaino della refurtiva, se non già presente; 
     * altrimenti lancia un'eccezione {@link DuplicateException}
     *
     * @param object Oggetto da inserire nello zaino della refurtiva
     * @throws DuplicateException
     */
    public void add(ValuableObject object) throws DuplicateException{
        
        if (objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
    }
    
    /** Il metodo restituisce l'importo totale degli oggetti rubati, contenuti nello zaino della refurtiva
     *
     * @return Valore dell'importo totale degli oggetti rubati contenuti nello zaino
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
    
    /** Il metodo restituisce {@code true} se l'oggetto rappresentato dal parametro object è presente nell'oggetto {@link LootBag}
     * sul quale il metodo viene invocato, {@code false} altrimenti
     *
     * @param object Oggetto di cui verificare l'appartenenza allo zaino della refurtiva
     * @return {@code true} se l'oggetto è contenuto nello zaino della refurtiva, {@code false} altrimenti
     */
    public boolean contains(ObjectId objectId){
        boolean contained = false;
        
        for (AdvObject object : objects){
            if (object.getId() == objectId)
                contained = true;
        }    
        
        return contained;
    }
    
    /** Il metodo restituisce l'oggetto dello zaino della refurtiva avente identificativo uguale al parametro Id se presente, 
     * altrimenti lancia l'eccezione {@link NoSuchElementException}
     *
     * @param Id Identificativo dell'oggetto che il metodo deve restituire, se presente
     * @return Oggetto avente identificativo uguale al parametro Id, se presente nell'oggetto {@link LootBag} sul quale il
     * metodo è invocato
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
