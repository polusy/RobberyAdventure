/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.io.PrintStream;

import adventure.Control.analyzers.*;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.types.*;
import adventure.Entity.properties.Property;
import adventure.Entity.properties.PropertyWithValue;
import adventure.identifiers.CommandType;
import adventure.identifiers.PropertyType;
import adventure.identifiers.ObjectId;
import adventure.exceptions.*;

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
            throws AmbiguousCommandException, NotValidSentenceException {
        
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
                        
                        for (String filteringWord : iteratedProperty.getType().getCommandFilteringWords()){
                            if (filteringWord.equals(token)){
                                if (foundPropertyType == null)
                                    foundPropertyType = iteratedProperty.getType();
                                else
                                    throw new AmbiguousCommandException();
                            }
                        }
                    }
                }
            }
            else if (parserOutput.getObjects().size() >= 1 && foundPreposition == false){
                for (AdvObject object: parserOutput.getObjects().keySet()) {
                    
                    if (object instanceof InteractiveObject) {
                        InteractiveObject iteratedObject = (InteractiveObject) object;

                        for (Property property : iteratedObject.getProperties())
                        {
                            if (property instanceof PropertyWithValue)
                            {
                                PropertyWithValue IteratedProperty = (PropertyWithValue) property;
                                for (String filteringWord : IteratedProperty.getType().getCommandFilteringWords())
                                {
                                    if (filteringWord.equals(token) && foundCandidate == false){
                                        foundPropertyType = IteratedProperty.getType();
                                        foundCandidate = true;
                                    }
                                    else if (filteringWord.equals(token) && foundCandidate == true){
                                        throw new AmbiguousCommandException();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        newCommandType = propertyCommandCorrespondences.get(foundPropertyType);
        Command newCommand = game.getCommandByType(newCommandType);
        parserOutput.setCommand(newCommand);
        }
    }



    public void nextMove(GameDescription game, ParserOutput parserOutput, PrintStream out) throws EndGameException{
	CommandType commandType = parserOutput.getCommand().getType();
	String message = null;
	CommandAnalysisResult commandAnalysisResult = null;
        GameActionResult gameActionResult = null;

	if (commandType == CommandType.NORTH || commandType == CommandType.SOUTH || 
                commandType == CommandType.EAST || commandType == CommandType.WEST)
            try {
                message = positionChangeHandler.handle(game, parserOutput);
            } catch (NotValidSentenceException exception){
                out.println(exception.getMessage());
            }
	else
            try {
                commandAnalysisResult = this.notifyAnalyzer(game, parserOutput);
            } catch (NotValidSentenceException exception){
                out.println(exception.getMessage());
            }
            catch (AmbiguousCommandException exception){
                out.println(exception.getMessage());
            }

	if (commandAnalysisResult != null && commandAnalysisResult.isAnalysisPassed()){
            
            if (parserOutput.getCommand().getType() != CommandType.LOOK_AT)
            {

                try {
                    gameActionResult = this.processCommandAnalysisResult(commandAnalysisResult, game, parserOutput);
                } 
                catch (NotValidSentenceException exception){
                    out.println(exception.getMessage());
                }
                catch (DuplicateException exception){
                    out.println(exception.getMessage());
                }
                message = gameActionResult.getMessage();
            }
            else {
                message = commandAnalysisResult.getMessage();
            }
	}

         out.println(message);

	if (gameActionResult != null && gameActionResult.getSpecialAction() != null)
        {
            try {
                gameActionResult.getSpecialAction().execute();
            } catch (PasswordGuessedException excetion){
                GameActionSpecificationProcesser.safeOpeningHandler((RobberyAdventure)game);
            }
            catch (EndGameException exception){
                out.println("Stai già immaginandoti su un'amaca ai Caraibi mentre sorseggi un cocktail "
                        + "da una noce di cocco. Hai rubato tutto quello che c'era di valore in questa casa "
                        + "e pensi che questo furto non sarebbe potuto andare meglio... con la vendita del quadro "
                        + "diventerai milionario!" + System.lineSeparator() + System.lineSeparator() +
                        "Stai sognando ad occhi aperti, ma all'improvviso vieni riportato alla realtà da un "
                                + "sinistro colpo di tosse... stai già sudando freddo, ti volti verso la porta... "
                        + "davanti a te c'è una decina di uomini grossi come armadi che si scrocchiano le nocche delle mani.");
                
                throw new EndGameException();
            }
        }
        
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


    private void removeCommandAnalyzer(CommandAnalyzer commandAnalyzer, CommandType commandType) throws NoSuchElementException 
    {
        if (!commandAnalyzers.containsKey(commandType)) {
            commandAnalyzers.put(commandType, null);
        }
	else{
            throw new NoSuchElementException();
	}
    }

    private CommandAnalysisResult notifyAnalyzer(GameDescription game, ParserOutput parserOutput) 
            throws NotValidSentenceException, AmbiguousCommandException {
	CommandAnalyzer commandAnalyzer= commandAnalyzers.get(parserOutput.getCommand().getType());
        
        return commandAnalyzer.analyze(game, parserOutput);
}
    

    private GameActionResult processCommandAnalysisResult(CommandAnalysisResult commandAnalysisResult, 
            GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, DuplicateException{
	GameActionSpecification gameActionSpecification = this.findGameActionSpecification(commandAnalysisResult, parserOutput);
       
            GameActionResult gameActionResult = GameActionSpecificationProcesser.process(gameActionSpecification, 
                    gameDescription, commandAnalysisResult);

	return gameActionResult;
    }

    private GameActionSpecification findGameActionSpecification(CommandAnalysisResult commandAnalysisResult,
            ParserOutput parserOutput) {
        
	return commandAnalysisResult.getTargetObject().getGameActionSpecification(commandAnalysisResult.getPropertyType(), parserOutput.getCommand().getType());
    }

    
    public void addPropertyCommandCorrespondence(PropertyType property, CommandType command) throws DuplicateException {
	if (propertyCommandCorrespondences.containsKey(property))
            throw new DuplicateException();

	propertyCommandCorrespondences.put(property, command);
    }    
}
