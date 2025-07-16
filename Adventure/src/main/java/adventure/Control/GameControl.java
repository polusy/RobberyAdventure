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

import adventure.utilities.SecurityCameraThread;
import java.util.concurrent.locks.Lock;

/** La classe è specializzata nel funzionamento di alto livello della partita
 * 
 * La classe si occupa del coordinamento generale delle attività inerenti al funzionamento della partita, delegando l'analisi
 * della semantica dei comandi inseriti dall'utente e l'esecuzione delle operazioni sulla partita a classi specializzate
 *
 * @author Alessandro
 */
public class GameControl {
    final private PositionChangeHandler positionChangeHandler;
    final private Map<CommandType, CommandAnalyzer> commandAnalyzers = new HashMap(); 
    final private Map<PropertyType, CommandType> propertyCommandCorrespondences = new HashMap();
    private SecurityCameraThread securityCameraThread;
    
    // Constructor

    /**
     * Il metodo inizializza i {@link CommandAnalyzer} e il {@link PositionChangeHandler}
     */
    
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
        
        this.securityCameraThread = new SecurityCameraThread();
        
        SecurityCameraThread.policeArrivalLock.lock();
    }

    /** Il metodo si occupa della disambiguazione della tipologia di comando estrapolato dalla stringa inserita dall'utente
     * da parte del {@link Parser}.
     *
     * @param game Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @throws AmbiguousCommandException
     * @throws NotValidSentenceException
     */
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
                foundPropertyType = PropertyType.USABLE;
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
                        foundPropertyType = PropertyType.USABLE;
                    }
                    else{
                        throw new NotValidSentenceException();
                    }
                }
            }

        newCommandType = propertyCommandCorrespondences.get(foundPropertyType);
        Command newCommand = game.getCommandByType(newCommandType);
        parserOutput.setCommand(newCommand);
        }
    }

    /** Il metodo si occupa dell'elaborazione di alto livello della stringa inserita dall'utente.
     * 
     * Il metodo sottopone l'oggetto {@link ParserOutput} all'analizzatore semantico di pertinenza
     *
     * @param game Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @param out Stream di output sul quale il metodo può stampare un messaggio
     * @throws EndGameException
     */
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
                    if (gameActionResult != null)
                        message = gameActionResult.getMessage();
                } 
                catch (NotValidSentenceException exception){
                    out.println(exception.getMessage());
                }
                catch (DuplicateException exception){
                    out.println(exception.getMessage());
                }
               
            }
            else {
                message = commandAnalysisResult.getMessage();
            }
	}else if(commandAnalysisResult != null && !commandAnalysisResult.isAnalysisPassed()){
            message = commandAnalysisResult.getMessage();
        }
        
        
        if (message != null)
            out.println(message + System.lineSeparator());
        
        

	if (gameActionResult != null && gameActionResult.getSpecialAction() != null)
        {
            try {
                gameActionResult.getSpecialAction().execute();
            } catch (PasswordGuessedException excetion){
                GameActionSpecificationProcesser.safeOpeningHandler((RobberyAdventure)game);
            }
            catch (EndGameException exception){
                out.println("Stai gia' immaginandoti su un'amaca ai Caraibi mentre sorseggi un cocktail "
                        + "da una noce di cocco. Hai rubato tutto quello che c'era di valore in questa casa "
                        + "e pensi che questo furto non sarebbe potuto andare meglio... con la vendita del quadro "
                        + "diventerai milionario!" + System.lineSeparator() + System.lineSeparator() +
                        "Stai sognando ad occhi aperti, ma all'improvviso vieni riportato alla realtà da un "
                                + "sinistro colpo di tosse... stai già sudando freddo, ti volti verso la porta... "
                        + "davanti a te c'e' una decina di uomini grossi come armadi che si scrocchiano le nocche delle mani.");
                
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
        
        if (commandAnalysisResult.getTargetObject() instanceof InteractiveObject)
        {
            return ((InteractiveObject)commandAnalysisResult.getTargetObject()).getGameActionSpecification(commandAnalysisResult.getPropertyType(), parserOutput.getCommand().getType());
        }
        else
            return null;
    }

    /** Inserisce, se non già presente, una corrispodenza tra il parametro property e il parametro command in un proprio 
     * attributo dedicato
     *
     * @param property Identificativo della proprietà 
     * @param command Identificativo del comando che deve essere fatto corrispondere al parametro property
     * @throws DuplicateException
     */
    public void addPropertyCommandCorrespondence(PropertyType property, CommandType command) throws DuplicateException {
	if (propertyCommandCorrespondences.containsKey(property))
            throw new DuplicateException();

	propertyCommandCorrespondences.put(property, command);
    }


    public Lock getSecurityCameraLock(){
        return SecurityCameraThread.policeArrivalLock ;
    }
    
    public void policeArrivalHandler(GameDescription game, ParserOutput parserOutput, PrintStream out) throws EndGameException{
        if (!securityCameraThread.isAlreadyActivated() && parserOutput.getCommand().getType() == CommandType.NORTH){
            if (!securityCameraThread.isAlive()){
                out.println("Sull'ingresso della villa è installata una telecamera che ti sta inquadrando tutto bene ..."
                        + " se non fosse che hai il passamontagna e sei l'archetipo del ladro dei film ...");

                securityCameraThread.start();
            }
        }
              
        if (securityCameraThread.isAlreadyActivated() && securityCameraThread.isAlive()){
            if (((InteractiveObject)game.getObjectById(
                    ObjectId.SECURITY_CAMERA)).getPropertyByType(
                            PropertyType.BREAKABLE).getValue() == true){
            
                securityCameraThread.interrupt();
            }
            else{
                out.println(System.lineSeparator() + "Il tempo scorre..." + System.lineSeparator());
            }
        }

        else if (securityCameraThread.isAlreadyActivated() && !securityCameraThread.isAlive() &&
                ((InteractiveObject)game.getObjectById(ObjectId.SECURITY_CAMERA)).getPropertyByType(
                            PropertyType.BREAKABLE).getValue() == false){
            throw new EndGameException();
        }

            
        
    }

    public SecurityCameraThread getSecurityCameraThread() {
        return securityCameraThread;
    }
    
    
}
