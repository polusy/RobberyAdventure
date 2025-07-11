/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import adventure.Control.analyzers.*;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.Entity.properties.Property;
import adventure.Entity.properties.PropertyWithValue;
import adventure.identifiers.CommandType;
import adventure.identifiers.PropertyType;
import adventure.exceptions.DuplicateException;
import adventure.exceptions.AmbiguousCommandException;
import adventure.exceptions.NotValidSentenceException;

/**
 *
 * @author utente
 */
public class GameControl {
    final private PositionChangeHandler positionChangeHandler;
    final private Map<CommandType, CommandAnalyzer> commandAnalyzers = new HashMap(); 
    final private Map<PropertyType, CommandType> propertyCommandCorrespondences = new HashMap();    
    
    // Constructor
    
    public GameControl(){
	
	positionChangeHandler = new PositionChangeHandler();
	
	try{
            this.addCommandAnalyzer(CommandType.OPEN, new OpenCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.CLOSE, new CloseCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.PUSH, new PushCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.PICK_UP, new PickUpCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.DROP, new DropCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.MOVE, new MoveCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.DEACTIVATE, new DeactivateCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.CONSUME, new ConsumeCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.LOOK_AT, new LookAtCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.BREAK, new BreakCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.ACTIVATE, new ActivateCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.FILL, new FillCommandAnalyzer());
            this.addCommandAnalyzer(CommandType.USE, new UseCommandAnalyzer());

        } catch(DuplicateException exception){};
    }


    public void disambiguateMove(GameDescription game, ParserOutput parserOutput) 
            throws AmbiguousCommandException, NotValidSentenceException
    {
	String token = parserOutput.getCommandToken();
	CommandType newCommandType = null;
	boolean foundPreposition = false;
	boolean foundCandidate = false;
	PropertyType foundPropertyType = null;

	if (parserOutput.getCommand().getType() == CommandType.USE){
            foundPreposition = parserOutput.hasPreposition();

            if(parserOutput.getObjects().size() > 1 && foundPreposition == true) 
            {
                InteractiveObject object = null;
                
                try {
                    AdvObject advObject = parserOutput.getUniqueObjectWithoutPreposition();
                    if (advObject instanceof InteractiveObject)
                        object = (InteractiveObject) advObject;
                    else
                        throw new NotValidSentenceException();
                } catch(NoSuchElementException exception){};

                for (Property property : object.getProperties()){
                    if (property instanceof PropertyWithValue)
                    {
                        PropertyWithValue iteratedProperty = (PropertyWithValue) property;
                        for (String filteringWord : iteratedProperty.getType().get())
                        {
                                if (filteringWord.equals(token))
                                {
                                        if (foundPropertyType == null)
                                                foundPropertyType = IteratedProperty.getPropertyType();
                                        else
                                                throw new AmbiguosCommandException();
                                }
                        }
                    }
                }
                }
                else if (parserOutput.getObjects.size() >= 1 && foundPreposition == false)
                {

                        for (AdvObject object: parserOutput.getObjects().keySet())
                        {
                                if (object istanceof InteractiveObject)
                                {
                                        InteractiveObject iteratedObject = (InteractiveObject) object;

                                        for (Property property : IiteratedObject.getProperties())
                                        {
                                                if (property istanceof PropertyWithStatus)
                                                {
                                                        PropertyWithStatus IteratedProperty = (PropertyWithStatus) property;
                                                        for (String filteringWord : IteratedProperty.getPropertyType().getCommandsFilteringWords())
                                                        {
                                                                if (filteringWord.equals(token) && foundCandidate == false)
                                                                {
                                                                        candidatePropertyType = IteratedProperty.getPropertyType();
                                                                        foundCandidate = true;
                                                                }
                                                                else if (filteringWord.equals(token) && foundCandidate == true)
                                                                {
                                                                        throw new AmbiguousCommandException();
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }

        newCommandType = propertyCommandCorrespondences.getValue(candidatePropertyType);
        Command newCommand = game.getCommandById(newCommandType);
        parserOutput.setCommand(newCommand):
    }
}



+ void nextMove(GameDescription game, ParserOutput parserOutput, PrintStream out) {
	CommandType commandType = parserOutput.getCommand().getCommandType();
	String message;
	CommandAnalysisResult commandAnalysisResult = null;

	if (commandType == CommandType.NORTH || commandType == CommandType.SOUTH || commandType == CommandType.EAST || commandType == CommandType.WEST)
		message = positionChangeHandler.handle(gameDescription, parserOutput);
	else
       		commandAnalysisResult = this.notifyAnalyzer(game, parserOutput);

	if (commandAnalysisResult != null && commandAnalysisResult.isAnalysisPassed())
	{
		GameActionResult gameActionResult = this.processCommandAnalysisResult(commandAnalysisResult, game, parserOutput);
		message = gameActionResult.getMessage();
	}

         out.println(message);

	if (gameActionResult.getSpecialAction() != null)
		gameActionResult.getSpecialAction().execute();
      
           
        }
    

 
    private void addCommandAnalyzer(CommandType commandType, CommandAnalyzer commandAnalyzer) throws DuplicateException 
    {
        if (!commandAnalyzers.containsKey(commandType)) {
            commandAnalyzers.put(commandType, commandAnalyzer);
        }
	else{
            throw new DuplicateException();
	}
    }


+ void removeCommandAnalyzer(CommandAnalyzer commandAnalyzer, CommandType commandType)  throws NoSuchElementException 
{
        if (!commandAnalyzers.containsKey(commandType)) {
            commandAnalyzers.put(commandType, null);
        }
	else{
		throw new NoSuchElementException();
	}
    }




- CommandAnalysisResult notifyAnalyzer(GameDescription game, ParserOutput parserOutput) {
	CommandAnalyzer commandAnalyzer= commandAnalyzers.getValue(parserOutput.getCommand().getCommandType());
	return commandAnalyzer.analyze(game, parserOutput);
}
    

-  GameActionResult processCommandAnalysisResult(CommandAnalysisResult commandAnalysisResult, GameDescription gameDescription, ParserOutput parserOutput)
{
	GameActionSpecification gameActionSpecification = this.findGameActionSpecification(commandAnalysisResult, parserOutput);
	GameActionResult gameActionResult = GameActionSpecificationProcesser.process(gameActionSpecification, commandAnalysisResult);

	return gameActionResult;
}

- GameActionSpecification findGameActionSpecification(CommandAnalysisResult commandAnalysisResult, ParserOutput parserOutput)
{
	return commandAnalysisResult.getTargetObject().getGameActionSpecification(commandAnalysisResult.getPropertyType(), parserOutput.getCommandType());
}



+ void addPropertyCommandCorrespondence(PropertyType property, CommandType command) throws DuplicateException
{
	if (propertyCommandCorrespondences.containsKey(property))
		throws new DuplicateException();

	propertyCommandCorrespondences.put(property, command);
}    
}
