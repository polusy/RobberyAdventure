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
 * 
 * La classe gestisce la richiesta dell'utente di visualizzazione della refurtiva (lootBag nel gergo inglese).
 */
public class LootBagCommandObserver implements GameObserver{
    
    /**
     *
     * @param game Descrizione di gioco corrente
     * @param parserOutput Rappresenta l'output del parser al momento del parsing della stringa inserita nell'input stream
     * dell'utente attraverso CLI.
     * @param out
     * 
     * @throws NotValidSentenceException Se l'utente inserisce una frase non corretta (Contestualmente al comando di visualizzazione della lootBag).
     * 
     * Il metodo stampa su un printStream generico i nomi degli oggetti contenuti nella refurtiva, assieme al loro valore,
     * ed infine stampa il totale rubato (presente nella lootbag).
     */
    @Override
    public void update(GameDescription gameDescription, ParserOutput parserOutput, PrintStream out) throws NotValidSentenceException{
        
        StringBuilder message = new StringBuilder("La tua refurtiva si compone di: ").append(System.lineSeparator());
	if (parserOutput.getDoorRoom() == null && parserOutput.getObjects().isEmpty())
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
