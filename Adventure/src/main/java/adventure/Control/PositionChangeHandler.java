/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import adventure.Entity.types.GameDescription;
import adventure.Entity.properties.PropertyWithValue;
import adventure.Entity.types.ParserOutput;
import adventure.Entity.types.CardinalPoint;
import adventure.exceptions.NotValidSentenceException;
import adventure.Entity.types.GameDescription;
import adventure.identifiers.CommandType;
import adventure.Entity.types.Room;
import adventure.Entity.objects.Door;
import adventure.identifiers.PropertyType;
import java.util.NoSuchElementException;

/** La classe è specializzata nell'analisi semantica del risultato dell'elaborazione della stringa inserita dall'utente
 * da parte del Parser rispetto a un comando di movimento tra le stanze del gioco, e nella modifica coerente della poszione
 * del giocatore nella mappa del gioco
 *
 * @author Paolo
 */
public class PositionChangeHandler {
    
    /** Il metodo si occupa dell'analisi semantica del risultato dell'elaborazione della stringa inserita dall'utente
 * da parte del Parser, rappresentato dal parametro parserOutput, rispetto a un comando di movimento tra le stanze del gioco.
 * 
 * Se la mossa specificata dal parserOutput è legale, il metodo provvede alla modifica della posizione corrente del giocatore
 * nella mappa del gioco
     *
     * @param gameDescription a un comando di movimento tra le stanze del gioco
     * @param parserOutput a un comando di movimento tra le stanze del gioco
     * @return Messaggio risultato dell'analisi del parserOutput
     * @throws NotValidSentenceException
     * @throws NoSuchElementException
     */
    public String handle(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, NoSuchElementException{
        String message = null;
	CommandType commandType = parserOutput.getCommand().getType();
	Door linkedDoor = null;
	Room linkedRoom = null;
	CardinalPoint cardinalPoint = null;
	
	if (commandType == CommandType.NORTH)
		cardinalPoint = CardinalPoint.NORTH;
	else if (commandType == CommandType.SOUTH)
		cardinalPoint = CardinalPoint.SOUTH;
	else if (commandType == CommandType.EAST)
		cardinalPoint = CardinalPoint.EAST;
	else if (commandType == CommandType.WEST)
		cardinalPoint = CardinalPoint.WEST;

	if (parserOutput.getDoorRoom() == null && parserOutput.getObjects().isEmpty())
	{
		try {
			linkedRoom = gameDescription.getCurrentRoom().getLinkedRoom(cardinalPoint);
		} catch (NoSuchElementException exception) {
                    message = "Non puoi andare in questa direzione";
		}

		if (linkedRoom != null)
                {
			try{
                            linkedDoor = gameDescription.getCurrentRoom().getLinkedDoor(cardinalPoint);
			} catch (NoSuchElementException exception){};

			if (linkedDoor== null)
			{
                            gameDescription.setCurrentRoom(linkedRoom);
                            String newRoomName = gameDescription.getCurrentRoom().getName();
                            message = System.lineSeparator() + "\t" + newRoomName + System.lineSeparator() +
                                    "===========================================" + System.lineSeparator() +
                            gameDescription.getCurrentRoom().getDescription();
			}
			else if (linkedDoor != null)
			{
                            if (linkedDoor.hasProperty(PropertyType.OPENABLE))
                            {
                                try {
                                        PropertyWithValue openable = linkedDoor.getPropertyByType(PropertyType.OPENABLE);
                                        if (openable.getValue() == true)
                                        {
                                            gameDescription.setCurrentRoom(linkedRoom);
                                            String newRoomName = gameDescription.getCurrentRoom().getName();
                                            message = System.lineSeparator() + "\t" + newRoomName + System.lineSeparator() +
                                                    "===========================================" + System.lineSeparator() +
                                            gameDescription.getCurrentRoom().getDescription();                                            
                                        }
                                        else{
                                            message = "C'e' una porta, devi aprirla per poter andare in questa direzione.";
                                        }
                                } catch (NoSuchElementException exception){};
                            }
                            else{
                                message = "C'e' una porta, ma non si può aprire in nessun modo!" ;
                            }
			}
		 }
                else{
                    message = "Nessuna stanza in questa direzione...";
                }
            }
            else{
                    throw new NotValidSentenceException();
            }

	return message;
    }
    
}
