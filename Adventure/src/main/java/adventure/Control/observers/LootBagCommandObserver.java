/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;


import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.Entity.types.RobberyAdventure;
import adventure.exceptions.NotValidSentenceException;
import adventure.Entity.objects.ValuableObject;
import java.io.PrintStream;
/**
 *
 * @author Paolo
 */
public class LootBagCommandObserver implements GameObserver{
    
    @Override
    public void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out) throws NotValidSentenceException{
        StringBuilder message = new StringBuilder("La tua refurtiva si compone di: ").append(System.lineSeparator());
	if (parserOutput.getDoorRoom() == null && parserOutput.getObjects() == null)
	{
            if (gameDescription instanceof RobberyAdventure)
            {
                RobberyAdventure robberyAdventure = (RobberyAdventure) gameDescription;
		for (ValuableObject object : robberyAdventure.getLootBag().getObjects())
		{
                   message.append(object.getName()).append(", valore : ").append(object.getValue()).append(System.lineSeparator());
		}

		message.append("Totale rubato : " + robberyAdventure.getLootBag().getTotalStolenAmount());
		out.println(message);
            }
	}
	else{
		throw new NotValidSentenceException();
	}
    }
    
}
