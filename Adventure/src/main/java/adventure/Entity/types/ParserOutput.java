/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import java.util.Map;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashMap;

import adventure.Entity.objects.AdvObject;
import adventure.utilities.Preposition;

/** Risultato dell'elaborazione da parte di un oggetto {@link Parser} della stringa inserita dall'utente.
 * 
 * La classe conserva l'oggetto {@link Command} estrapolato da parte del {@link Parser} dal token corrispondente 
 * proveniente dalla stringa inserita dall'utente e il token stesso, quest'ultimo utile per la disambiguazione del comando
 * indicato dall'utente, che viene operata da un metodo specializzato di un'altra classe.
 * 
 * La classe conserva una mappa che associa ad ogni oggetto estrapolato dalla stringa da parte del {@link Parser} l'eventuale
 * preposizione fornita dall'utente nella scrittura della mossa.
 * 
 * La classe conserva la stanza che il {@link Parser} ha eventualmente estrapolato dal token corrispodente proveniente
 * dalla stringa inserita dall'utente, per la disambiguazione della porta della stanza in cui il giocatore si trova all'istante
 * corrente, alla quale viene fatto riferimento nella mossa
 *
 * @author Paolo
 */
public class ParserOutput {
    
    private Command command;
    private String commandToken;
    private Map<AdvObject, Preposition> objects = new HashMap<>();
    private Room doorRoom = null;

    /** Cosruisce un oggetto di classe ParserOutput inizializzando esclusivamente gli attributi corrispondenti ai due
     * parametri, lasciando vuota la mappa che associa ad ogni oggetto l'eventuale preposizione, che verrà successivamente
     * eventualmente riempita dal {@link Parser}
     *
     * @param command Comando estrapolato da parte del {@link Parser} dalla stringa inserita dall'utente
     * @param commandToken Token corrispodente al comando, proveniente direttamente dalla stringa inserita dall'utente
     */
    public ParserOutput(Command command, String commandToken) {
        this.command = command;
        this.commandToken = commandToken;
    }
    
    /**
     *
     * @param object Inserisce l'oggetto object come chiave della mappa delle associazioni tra oggetti e preposizioni
     */
    public void addObject(AdvObject object){
        objects.put(object, null);
    }
    
    /** Associa la preposizione rappresentata dal paramtetro preposition all oggetto rappresentato dal parametro object, 
     * se questo è presente come chiave della mappa, altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @param object Oggetto a cui associare la preposizione, se questo è presente
     * @param preposition Preposizione da associare all'oggetto, se questo è presente
     * @throws NoSuchElementException
     */
    public void addPreposition(AdvObject object, Preposition preposition) throws NoSuchElementException 
    { 
	if  (this.containsObject(object))
		objects.put(object, preposition);
	else
            throw new NoSuchElementException();
    }

    /** Restituisce l'espressione oggetto-preposizione contenuta nell'oggetto {@link ParserOutput}, dove l'oggetto è rappresentato
     * dal parametro object
     *
     * @param object Oggetto chiave della coppia, che costituisce l'espressione richiesta
     * @return Espressione oggetto-preposizione
     */
    public Map.Entry<AdvObject, Preposition> getExpression(AdvObject object)
    {
	Set<Map.Entry<AdvObject, Preposition>> entrySet = objects.entrySet();
	Map.Entry<AdvObject, Preposition> returnedValue = null;

	for (Map.Entry<AdvObject, Preposition> entry : entrySet)
	{
		if (entry.getKey() == object)
		{
			returnedValue = entry;
		}	
	}
        
	return returnedValue;
    }

    /**
     *
     * @param object Oggetto del quale verificare l'appartenenza all'insieme delle chiavi delle espressioni oggetto-preposizione
     * @return {@code true} se l'oggetto è chiave di una coppia oggetto-preposizione, {@code false} altrimenti
     */
    public boolean containsObject(AdvObject object)
    {
            return objects.containsKey(object);
    }

    /**
     *
     * @param room Stanza di cui si fa menzione nella stringa inserita dall'utente, processata dal {@link Parser}
     */
    public void addRoom(Room room){this.doorRoom = room;}

    /**
     *
     * @return Stanza contenuta nell'oggetto {@link ParserOutput}, estrapolata da parte del {@link Parser} dalla stringa
     * inserita dall'utente
     */
    public Room getDoorRoom(){return this.doorRoom; }

    /**
     *
     * @return Insieme delle espressioni oggetto del gioco - preposizione
     */
    public Map<AdvObject, Preposition> getObjects() {
        return objects;
    }

    /**
     *
     * @return 
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /**
     *
     * @return
     */
    public String getCommandToken() {
        return commandToken;
    }
    
    /** 
     *
     * @return true se esiste un'espressione contenuta nell'oggetto {@link ParserOutput} che contiene
     * una preposizione, false altrimenti
     */
    public boolean hasPreposition()
    {
            boolean foundPreposition = false;

                    for (Preposition preposition : objects.values())
                    {
                            if (preposition != null)
                                foundPreposition = true;
                    }

            return foundPreposition;
    }

    /** Il metodo restituisce l'unico oggetto delle coppie oggetto del gioco - preposizione che ha la preposizione 
     * associata nella relativa espressione, se questo esiste, altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @return L'unico oggetto dell'oggetto ParserOutput a cui è associata la preposizione
     * @throws NoSuchElementException
     */
    public AdvObject getObjectWithPreposition() throws NoSuchElementException
    {
            if (!this.hasPreposition())
                    throw new NoSuchElementException();

            AdvObject objectWithPreposition = null;
            for (AdvObject object : objects.keySet())
                    if (objects.get(object) != null)
                            objectWithPreposition = object;

            return objectWithPreposition;
    }

    /** Il metodo restituisce l'unico oggetto delle coppie oggetto del gioco - preposizione che non ha la preposizione 
     * associata nella relativa espressione, se questo esiste, altrimenti lancia un'eccezione {@link NoSuchElementException}
     *
     * @return L'unico oggetto dell'oggetto ParserOutput a cui non è associata alcuna preposizione nella propria espressione
     * @throws NoSuchElementException
     */
    public AdvObject getUniqueObjectWithoutPreposition() throws NoSuchElementException
    {
            if (!this.hasPreposition())
                    throw new NoSuchElementException();

            AdvObject objectWithoutPreposition = null;
            for (AdvObject object : objects.keySet())
                    if (objects.get(object) == null)
                            objectWithoutPreposition = object;

            return objectWithoutPreposition;
    }
    
    
    
    
    
    
}
