/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.exceptions.NotValidSentenceException;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.Door;
import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public class LookAtCommandAnalyzer extends CommandAnalyzer {
    
    CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException
    {
	CommandAnalysisResult commandAnalysisResult = new CommandAnalysisResult(null, null, null, true, null);

	if (parserOutput.getRoom() == null && parserOutput.getObjects().isEmpty()){
            commandAnalysisResult.setMesssage(gameDescription.getCurrentRoom().getLook());
	}
	else if(parserOutput.getObjects().size() == 1 && !parserOutput.hasPreposition())
	{
            for (AdvObject object : parserOutput.getObjects.keySet())
            {
                if (parserOutput.getRoom() != null)
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
