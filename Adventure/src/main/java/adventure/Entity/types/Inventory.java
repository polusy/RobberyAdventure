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


/** Inventario del giocatore in una sessione di gioco
 * 
 * Nell'inventario vengono inseriti gli oggetti non di valore, cioé che non sono di classe {@link ValueableObject}, 
 * che vengono raccolti dal giocatore durante la partita. È possible inoltre rimuovere oggetti dall'inventario
 * 
 *
 * @author Paolo
 */
public class Inventory {
    
    private List<AdvObject> objects = new ArrayList<>();
    
    /** Costruisce un oggetto Inventory inserendo al suo interno la lista di {@link AdvObject} passata per parametro
     *
     * @param objects oggetti da inserire nell'inventario
     */
    public Inventory(List<AdvObject> objects){
        this.objects = objects;
    }

    /**
     *
     * @return Oggetti contenuti nell'inventario
     */
    public List<AdvObject> getObjects() {
        return objects;
    }
    
    /** Il metodo rimuove l'oggetto rappresentato dal parametro object dall'inventario, se presente; altrimenti
     * lancia un'eccezione {@link NoSuchElementException}
     *
     * @param object Oggetto da rimuovere dall'inventario
     * @throws NoSuchElementException
     */
    public void remove(AdvObject object) throws NoSuchElementException{
        if (!objects.contains(object))
            throw new NoSuchElementException();
        
        objects.remove(object);
    }
    
    /** Il metodo inserisce l'oggetto rappresentato dal parametro object nell'inventario, se non già presente; 
     * altrimenti lancia un'eccezione {@link DuplicateException}
     *
     * @param object Oggetto da inserire nell'inventario
     * @throws DuplicateException
     */
    public void add(AdvObject object) throws DuplicateException{
        if (objects.contains(object))
            throw new DuplicateException();
        
        objects.add(object);
        
    }
    
    /** Il metodo restituisce l'oggetto dell'inventario avente identificativo uguale al parametro Id se presente, 
     * altrimenti lancia l'eccezione {@link NoSuchElementException}
     *
     * @param Id Identificativo dell'oggetto che il metodo deve restituire, se presente
     * @return Oggetto avente identificativo uguale al parametro Id, se presente nell'oggetto {@link Inventory} sul quale il
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
    
    /** Il metodo restituisce {@code true} se l'oggetto rappresentato dal parametro object è presente nell'oggetto {@link Inventory}
     * sul quale il metodo viene invocato, {@code false} altrimenti
     *
     * @param object Oggetto di cui verificare l'appartenenza all'inventario
     * @return {@code true} se l'oggetto è contenuto nell'inventario, {@code false} altrimenti
     */
    public boolean contains(AdvObject object){
        if (objects.contains(object))
            return true;
        else
            return false;
                    
    }
    
    
}

