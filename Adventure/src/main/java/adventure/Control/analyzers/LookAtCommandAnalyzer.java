/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Control.analyzers.OpenCommandAnalyzer;
import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.AmbiguousCommandException;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.Door;
import adventure.identifiers.PropertyType;

/** La classe è specializzata nell'analisi semantica di un comando relativo al comando LOOK_AT
 *
 * @author Alessandro
 */
public class LookAtCommandAnalyzer extends CommandAnalyzer {
    
    /** Costruisce l'oggetto LookAtCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public LookAtCommandAnalyzer(){
        super("");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando LOOK_AT
     * 
     * Nel caso in cui il comando sia riferito ad un oggetto rotto, il metodo seleziona la relativa descrizione
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, AmbiguousCommandException
    {
	CommandAnalysisResult commandAnalysisResult = new CommandAnalysisResult(null, null, null, true, null);

	if (parserOutput.getDoorRoom() == null && parserOutput.getObjects().isEmpty()){
            commandAnalysisResult.setMessage(gameDescription.getCurrentRoom().getLook());
	}
	else if(parserOutput.getObjects().size() == 1 && !parserOutput.hasPreposition())
	{
            for (AdvObject object : parserOutput.getObjects().keySet())
            {
                if (parserOutput.getDoorRoom() != null)
                {
                    if (object instanceof Door)
                    {
                        Door door = (Door) object;
                        if (!door.isSpecial())
                        {
                            OpenCommandAnalyzer.disambiguateDoor(commandAnalysisResult, gameDescription, parserOutput);
                            commandAnalysisResult.setTargetObject(door);
                        }
                        else{
                            throw new NotValidSentenceException();
                        }
                    }
                    else{
                        throw new NotValidSentenceException();
                    }
                }
                else{
                    commandAnalysisResult.setTargetObject(object);
                }
            }
            
            String message;

            message = commandAnalysisResult.getTargetObject().getDescription();
            
            if (commandAnalysisResult.getTargetObject() instanceof InteractiveObject)
            {
                
                InteractiveObject targetObject = (InteractiveObject)commandAnalysisResult.getTargetObject();

                if (targetObject.hasProperty(PropertyType.BREAKABLE)){
                    if (targetObject.getPropertyByType(PropertyType.BREAKABLE).getValue() == true)
                        message = targetObject.getBrokenDescription();
                }
            }
            
            commandAnalysisResult.setMessage(message);
	}
	else{
		throw new NotValidSentenceException();
	}

	return commandAnalysisResult;	
    }
     
}
