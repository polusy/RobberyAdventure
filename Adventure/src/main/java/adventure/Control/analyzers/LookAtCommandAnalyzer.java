/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.AmbiguousCommandException;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.Door;
import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public class LookAtCommandAnalyzer extends CommandAnalyzer {
    
    public LookAtCommandAnalyzer(){
        super("");
    }
    
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
                    if (!(object instanceof InteractiveObject))
                        throw new NotValidSentenceException();
                    
                    commandAnalysisResult.setTargetObject((InteractiveObject)object);
                }
            }
            
            String message;

            message = commandAnalysisResult.getTargetObject().getDescription();
            
            InteractiveObject targetObject = commandAnalysisResult.getTargetObject();
            if (targetObject.hasProperty(PropertyType.BREAKABLE)){
                if (targetObject.getPropertyByType(PropertyType.BREAKABLE).getValue() == true)
                    message = targetObject.getBrokenDescription();
            }
            
            commandAnalysisResult.setMessage(message);
	}
	else{
		throw new NotValidSentenceException();
	}

	return commandAnalysisResult;	
    }
     
}
