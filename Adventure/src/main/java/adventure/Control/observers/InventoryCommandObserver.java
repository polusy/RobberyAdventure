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
 * @author Paolo
 */
public class InventoryCommandObserver implements GameObserver{
    
    @Override
    public void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out) throws NotValidSentenceException
    {
	if (parserOutput.getDoorRoom() != null || parserOutput.getObjects() != null)
		throw new NotValidSentenceException();


	StringBuilder inventoryItemsString = new StringBuilder("Il tuo inventario: " + System.lineSeparator());
	
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
