/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import java.util.function.BiPredicate;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.sql.SQLException;

import adventure.Entity.types.GameDescription;
import adventure.identifiers.CommandType;
import adventure.Control.observers.TechnicalObserver;
import adventure.Control.observers.GameObserver;
import adventure.Entity.types.Command;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.types.Room;
import adventure.Entity.types.RobberyAdventure;
import adventure.Entity.types.ParserOutput;
import adventure.identifiers.PrepositionType;
import adventure.identifiers.PropertyType;
import adventure.utilities.Utils;
import adventure.exceptions.*;
import adventure.Control.observers.*;
import adventure.Boundary.DatabaseManager;


import adventure.Boundary.ServerManager;
import adventure.Boundary.services.*;


/**
 * La classe costituisce l'ambiente di esecuzione astratto dell'applicazione. 
 * Coordina gli aspetti tecnici legati al funzionamento dell'applicazione e delega la gestione della logica del gioco
 * vero e proprio a classi specializzate.
 * 
 *
 * @author Alessandro
 */
public class Engine {
    private GameDescription game; 
    private final Parser parser;
    private final GameControl gameControl;
    private final Map<CommandType, TechnicalObserver> technicalObservers = new HashMap();
    private final Map<CommandType, GameObserver> gameObservers = new HashMap();
    private final PrintStream out;
    
    /**
     *
     * @param game È la partita della quale l'Engine coordinerà il funzionamento
     */
    public Engine(GameDescription game){
        Set<String> stopwords = new HashSet();
        List<PrepositionType> prepositionTypes = Arrays.asList(PrepositionType.class.getEnumConstants());
        String sentencesSeparatorsRegex = "\\s(e|ed|poi|dopo)\\s";
        String wordsSeparatorsRegex = "\\s";

        this.game = game;
        gameControl = new GameControl();
        out = System.out;
        
        try {
            stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
        } catch (IOException exception){
            out.println(exception.getMessage());
        }
       
	BiPredicate<String, Command> commandTester = (token, command) -> command.getName().equals(token) || command.getAlias().contains(token);
	BiPredicate<String, AdvObject> objectTester = (token, object) -> object.getName().toLowerCase().equals(token) || 
                Utils.toLowerCaseStringList(new ArrayList(object.getAlias())).contains(token);
	BiPredicate<String, Room> roomTester = (token, room) -> room.getName().equals(token);
	BiPredicate<String, PrepositionType> prepositionTester = (token, preposition) -> preposition.getWords().contains(token);

	try {
            gameControl.addPropertyCommandCorrespondence(PropertyType.CONSUMABLE, CommandType.CONSUME);
            gameControl.addPropertyCommandCorrespondence(PropertyType.ACTIVATABLE, CommandType.ACTIVATE);
            gameControl.addPropertyCommandCorrespondence(PropertyType.PUSHABLE, CommandType.PUSH);
            gameControl.addPropertyCommandCorrespondence(PropertyType.OPENABLE, CommandType.OPEN);
            gameControl.addPropertyCommandCorrespondence(PropertyType.USABLE, CommandType.USE);
	} catch (DuplicateException exception){};

        
	parser = new Parser(stopwords, prepositionTypes, commandTester, objectTester, roomTester,
                prepositionTester, sentencesSeparatorsRegex, wordsSeparatorsRegex);
        
       
        technicalObservers.put(CommandType.END, new EndCommandObserver());
        technicalObservers.put(CommandType.MENU, new MenuCommandObserver());
        technicalObservers.put(CommandType.SAVE, new SaveCommandObserver());

        gameObservers.put(CommandType.INVENTORY, new InventoryCommandObserver());
        gameObservers.put(CommandType.LOOT_BAG, new LootBagCommandObserver());

        
    }

    /** Avvia l'esecuzione della partita e si occupa del coordinamento delle attività di alto livello legate ad essa.
     *  
     * Avvia l'esecuzione dei thread che si occupano dell'interazione del gioco con la base di dati per il salvataggio 
     * della partita e del caricamento di partite salvate in precedenza.
     *
     * @throws InconsistentInitializationException
     */
    public void execute() throws InconsistentInitializationException{
        List<ParserOutput> parserOutputs = new ArrayList();
        boolean exit = false;
        boolean gameOver = false;
        boolean isTechnicalCommand = false;
        boolean isGameObserverCommand = false;
        ServerManager objectsManager = new ServerManager("http://localhost", 8080, ObjectService.class);
        ServerManager gameSavingsManager = new ServerManager("http://localhost", 8081, GameService.class);
               
        try {
            game.init();
        } 
        catch (PasswordGuessedException exception){ }
        catch (EndGameException exception){ }
        
        out.println(System.lineSeparator());
        out.println("==================================================================================================================");
        out.println("\t\t\t\t\tRobbery Adventure");
        out.println("==================================================================================================================");
        out.println(System.lineSeparator());
        
        out.println(game.getWelcomeMessage());
       
        //starting server 
        objectsManager.run();
        gameSavingsManager.run();
        
        
        try{//initializing games savings tables in db
            DatabaseManager dbmanager = new DatabaseManager(); 
            dbmanager.createGamesTable();
        }catch(SQLException exception){out.println(exception.getSQLState());};
        

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine() && !exit && !gameOver) {
            String command = scanner.nextLine();
            ParserOutput parserOutput = null;
            parserOutputs.clear();
            
            try{  
                parserOutputs = parser.parse(command, game.getCommands(), game.getRooms(),
                    game.getCurrentRoom().getObjects(), game.getInventory().getObjects());
                
                Iterator parserOutputsIterator = parserOutputs.iterator();
                
                while (parserOutputsIterator.hasNext()) {
                    parserOutput = (ParserOutput) parserOutputsIterator.next();
                    
                    gameControl.disambiguateMove(game, parserOutput);
                    isTechnicalCommand = notifyTechnicalObservers(game, parserOutput);
                    isGameObserverCommand = notifyGameObservers(game, parserOutput, out);
                    gameControl.policeArrivalHandler(game, parserOutput, out);
                    
                    
                    if (!isTechnicalCommand && !isGameObserverCommand){
                        try {
                            gameControl.nextMove(game, parserOutput, System.out);
                        } catch (EndGameException exception){
                            gameOver = true;
                        }
                    }
                }
            }
            catch (NotValidTokenException excception){
                out.println("Non ho capito cosa vuoi dire");
            }
            catch(AmbiguousCommandException exception){
                out.println(exception.getMessage());
            }
            catch(NotValidSentenceException exception) { 
                out.println(exception.getMessage());
            }
            catch (EndGameException exception){
                if (gameControl.getSecurityCameraThread().isAlreadyActivated()){
                    out.println(System.lineSeparator() + "Troppo tardi! La telecamera ha allertato la polizia"
                            + " e nsei stato arrestato!"+ System.lineSeparator());
                    exit = true;
                }
                
                out.println("Probabilmente come ladro non vali granche'" + '(' + "e menomale)" +')');
                exit = true;
            }
            
            System.out.print("?> ");
        }
    }

    /** Notifica il risultato dell'elaborazione del parser della stringa inserita dall'utente a tutti i {@link TechnicalObserver},
     * che lo processano in caso esso sia di loro competenza
     *
     * @param game Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return true se il comando estrapolato dalla stringa inserita dall'utente da parte del parser è di pertinenza di 
     * un {@link TechnicalObserver}, false altrimenti
     * @throws EndGameException
     * @throws NotValidSentenceException
     */
    public boolean notifyTechnicalObservers(GameDescription game, ParserOutput parserOutput) 
            throws EndGameException, NotValidSentenceException {
        StringBuilder message = null;
        boolean recognizedCommand = false;
        
	CommandType commandType = parserOutput.getCommand().getType();


	if (technicalObservers.containsKey(commandType)){
            recognizedCommand = true;
            technicalObservers.get(commandType).update(game, parserOutput, message);
        }
        
        return recognizedCommand;
    }
    
    /** Notifica il risultato dell'elaborazione del parser della stringa inserita dall'utente a tutti i {@link GameObserver},
     * che lo processano in caso esso sia di loro competenza
     *
     * @param game Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @param out Stream di output sul quale un gameObserver può eventualmente stampare un messaggio
     * @return true se il comando estrapolato dalla stringa inserita dall'utente da parte del parser è di pertinenza di 
     * un GameObserver, false altrimenti
     * @throws NotValidSentenceException
     */
    public boolean notifyGameObservers(GameDescription game, ParserOutput parserOutput, PrintStream out) 
            throws NotValidSentenceException {
        
	CommandType commandType = parserOutput.getCommand().getType();
        boolean recognizedCommand = false;

	if (gameObservers.containsKey(commandType)){
            recognizedCommand = true;
            gameObservers.get(commandType).update(game, parserOutput, out);
        }
        
        return recognizedCommand;
    }
    
    /** Metodo main che viene eseguito con l'avvio dell'applicazione.
     * Costruisce un nuovo oggetto {@link Engine} e ne avvia l'attività
     *
     * @param args parametri inseriti da linea di comando. Vengono ignorati
     * @throws InconsistentInitializationException
     */
    public static void main(String[] args) throws InconsistentInitializationException{
        Engine engine = new Engine(new RobberyAdventure());
        engine.execute();
    }

    
}
