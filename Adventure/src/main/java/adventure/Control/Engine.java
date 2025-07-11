/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import adventure.Entity.types.GameDescription;
import adventure.identifiers.CommandType;
import adventure.Control.observers.TechnicalObserver;

/**
 *
 * @author utente
 */
public class Engine {
    private final GameDescription game; 
    private final Parser parser;
    private final GameControl gameControl;
    private final Map<CommandType, TechincalObserver> technicalObservers;

    public Engine(GameDescription game)
{
	this.game = game;
	GameControl gameControl = new GameControl();

	// da definire
	//costruire regex per il parser
	BiPredicate<String, Command> commandTester = (token, command) -> command.getName().equals(token) || command.getAlias().contains(token);
	BiPredicate<String, AdvObject> objectTester = (token, object) -> object.getName().equals(token) || object.getAlias().contains(token);
	BiPredicate<String, Room> roomTester = (token, room) -> room.getName().equals(token);
	BiPredicate<String, Preposition> prepositionTester = (token, preposition) -> preposition.getWords().contains(token);

	try {
		gameControl.addPropertyCommandCorrespondence(CONSUMABLE, CONSUME);
		gameControl.addPropertyCommandCorrespondence(ACTIVATABLE, ACTIVATE);
		gameControl.addPropertyCommandCorrespondence(PUSHABLE, PUSH);
		gameControl.addPropertyCommandCorrespondence(OPENABLE, OPEN);
		gameControl.addPropertyCommandCorrespondence(USABLE, USE);
	} catch (DuplicateException exception);

	parser = new Parser(stopwords, prepositions, commandTester, objectTester, roomTester, prepositionTester, sentencesSeparators, wordsSeparators);
	// da definire
}
public void execute() {
        System.out.println("================================");
        System.out.println("* Adventure v. 0.4 - 2023-2024 *");
        System.out.println("*         developed by         *");
        System.out.println("*       Pierpaolo Basile       *");
        System.out.println("================================");
        System.out.println();
        System.out.println(game.getWelcomeMsg());
        System.out.println();
        System.out.println("Ti trovi qui: " + game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescription());
        System.out.println();
        System.out.print("?> ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
	try{
            ParserOutput parserOutput = parser.parse(command, game.getCommands(), game.getRooms(), game.getCurrentRoom().getObjects(), game.getInventory());
		gameControl.disambiguateMove(game, parserOutput);
		notifyTechnicalObservers(game, parserOutput);
		gameControl.nextMove(game, parserOutput, System.out);

	}catch(AmbiguousCommandException exception){ // da definire}
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

+ void notifyTechnicalObservers(GameDescription game, ParserOutput parserOutput){

	StringBuilder message = null;
	CommandType commandType = parserOutput.getCommand().getCommandType();

	if technicalObservers.containsKey(commandType)
		technicalObservers.getValue(commandType).update(game, parserOutput, message);

	System.out.println(message);


}

    
}
