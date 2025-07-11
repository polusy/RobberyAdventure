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

import adventure.Entity.types.GameDescription;
import adventure.identifiers.CommandType;
import adventure.Control.observers.TechnicalObserver;
import adventure.Control.observers.GameObserver;
import adventure.Entity.types.Command;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.types.Room;
import adventure.Entity.types.ParserOutput;
import adventure.identifiers.PrepositionType;
import adventure.identifiers.PropertyType;
import adventure.utilities.Utils;
import adventure.exceptions.*;
import adventure.Control.observers.*;


/**
 *
 * @author utente
 */
public class Engine {
    private final GameDescription game; 
    private final Parser parser;
    private final GameControl gameControl;
    private final Map<CommandType, TechnicalObserver> technicalObservers = new HashMap();
    private final Map<CommandType, GameObserver> gameObservers = new HashMap();
    private final PrintStream out;

    public Engine(GameDescription game){
        Set<String> stopwords = new HashSet();
        List<PrepositionType> prepositionTypes = Arrays.asList(PrepositionType.class.getEnumConstants());
        String sentencesSeparatorsRegex = "e|ed|poi|dopo";
        String wordsSeparatorsRegex = "\\s";

        this.game = game;
        GameControl gameControl = new GameControl();
        out = System.out;
        
        try {
            stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
        } catch (IOException exception){
            out.println(exception.getMessage());
        }
       
	BiPredicate<String, Command> commandTester = (token, command) -> command.getName().equals(token) || command.getAlias().contains(token);
	BiPredicate<String, AdvObject> objectTester = (token, object) -> object.getName().equals(token) || object.getAlias().contains(token);
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

        gameObservers.put(CommandType.INVENTORY, new InventoryCommandObserver());
        gameObservers.put(CommandType.LOOT_BAG, new LootBagCommandObserver());

        
    }

    public void execute() {
        List<ParserOutput> parserOutputs = new ArrayList();
        
        out.println("================================");
        out.println("* Robbery Adventure *");
        out.println("================================");
        out.println();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput parserOutput = null;
            parserOutputs.clear();
            
            try{
                
                try {
                    parserOutputs = parser.parse(command, game.getCommands(), game.getRooms(),
                        game.getCurrentRoom().getObjects(), game.getInventory().getObjects());
                } catch (NotValidTokenException excception){
                    
                }
            
                
                Iterator parserOutputsIterator = parserOutputs.iterator();
                
                while (parserOutputsIterator.hasNext()) {
                    parserOutput = (ParserOutput) parserOutputsIterator.next();
                    
                    gameControl.disambiguateMove(game, parserOutput);
                    notifyTechnicalObservers(game, parserOutput);
                    notifyGameObservers(game, parserOutput, out);
                    gameControl.nextMove(game, parserOutput, System.out);
                }
            }
            catch(AmbiguousCommandException exception){
                
            }
            catch(NotValidSentenceException exception) { // da definire}
            catch(NotValidTokenException exception) { // da definire}
                if (game.getCurrentRoom() == null) {
                    System.out.println("La tua avventura termina qui! Complimenti!");
                    System.exit(0);
                }
            }
            System.out.print("?> ");
        }
    }

    public void notifyTechnicalObservers(GameDescription game, ParserOutput parserOutput){

	StringBuilder message = null;
	CommandType commandType = parserOutput.getCommand().getCommandType();

	if technicalObservers.containsKey(commandType)
		technicalObservers.getValue(commandType).update(game, parserOutput, message);

	System.out.println(message);


}

    
}
