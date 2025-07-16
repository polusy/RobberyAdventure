/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import java.util.stream.Collectors;
import java.util.List;
import java.io.PrintStream;

/**
 *
 * 
 * 
 * La classe si occupa di gestire la richiesta dell'utente di visualizzazione dell'inventario.
 * 
 * @author Paolo
 */
public class InventoryCommandObserver implements GameObserver{
    
    /**
     *
     * 
     * Il metodo costruisce una stringa contenente tutti i nomi degli oggetti dell'inventario
     * 
     * @param game Descrizione di gioco corrente
     * @param parserOutput Rappresenta l'output del parser al momento del parsing della stringa inserita nell'input stream
     * dell'utente attraverso CLI.
     * @param out
     * @throws NotValidSentenceException Se l'utente inserisce una frase non corretta (contestualmente al comando di visual. dell'inventario).
     * 
     *.
     */
    @Override
    public void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out) throws NotValidSentenceException
    {
        //verifico validità dell'output del parser (nessun oggetto o stanza nel p. output).
	if (parserOutput.getDoorRoom() != null || !parserOutput.getObjects().isEmpty())
		throw new NotValidSentenceException();


	StringBuilder inventoryItemsString = new StringBuilder("Il tuo inventario: " + System.lineSeparator());
	
        //pipeline per collezionare i nomi degli oggetti in una lista
	List<String> objectNames = gameDescription.getInventory().getObjects().stream()
		.map(object -> object.getName())
		.collect(Collectors.toList());

	for (String objectName : objectNames){
		inventoryItemsString.append("- ").append(objectName).append(System.lineSeparator());
	}

	inventoryItemsString.append(System.lineSeparator());
        
        out.println(inventoryItemsString);

    }
    
}
