/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;
import java.lang.StringBuilder;

import adventure.utilities.Preposition;
import adventure.Entity.types.Command;
import adventure.Entity.objects.AdvObject;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.NotValidTokenException;
import adventure.utilities.Utils;
import adventure.Entity.objects.Door;
import adventure.Entity.types.Room;
import adventure.Entity.types.ParserOutput;
import adventure.identifiers.PrepositionType;


/**
 *
 * @author utente
 */
public class Parser {
    final private Set<String> stopwords;
    final private List<PrepositionType> prepositions;
    final private BiPredicate<String, Command> commandTester;
    final private BiPredicate<String, PrepositionType>prepositionTester; 
    final private BiPredicate<String,AdvObject> objectTester;
    final private BiPredicate<String, Room> roomTester;
    final private String sentencesSeparators;
    final private String wordsSeparators;
    final private String regex;
     
    public Parser(Set<String> stopwords, List<PrepositionType> prepositions, BiPredicate<String, Command> commandTester,
    BiPredicate<String, AdvObject> objectTester, BiPredicate<String, Room> roomTester, 
    BiPredicate<String, PrepositionType> prepositionTester, String sentencesSeparators, String wordsSeparators) { 

        this.stopwords = stopwords; 
        this.prepositions = prepositions;
        this.commandTester = commandTester;
        this.prepositionTester = prepositionTester;
        this.objectTester = objectTester;
        this.roomTester = roomTester;
        this.sentencesSeparators = sentencesSeparators;
        this.wordsSeparators = wordsSeparators;
        this.regex = "^command(((( preposition)? inventoryObject){1,2}|" + 
        "(( preposition)? roomObject)?(( preposition)? inventoryObject)|" +
        "(( preposition)? door)( room)?(( preposition)? inventoryObject)?|" +
        "(( preposition)? inventoryObject)?(( preposition)? roomObject)|" +
        "(( preposition)? inventoryObject)?((( preposition)? door)( room)?))?)" + 
        "&&[( preposition (inventoryObject|roomObject)){2,2}]$";
    }

    public List<ParserOutput> parse(String originalSentence, List<Command> commands, List<Room> rooms,
            List<AdvObject> roomObjects, List<AdvObject> inventoryObjects) 
            throws NotValidTokenException, NotValidSentenceException {
  	List<String> sentences = Utils.parseString(originalSentence, stopwords, sentencesSeparators);
	List<String> tokens = new ArrayList();
	List <ParserOutput> parserOutputs = new ArrayList();
	List<AdvObject> allObjects = new ArrayList();
	List<String> names = new ArrayList();

	allObjects.addAll(roomObjects);
	allObjects.addAll(inventoryObjects);
	names = getAllNames(allObjects);
	names.sort(Comparator.comparingInt(s -> s.length()));

        Iterator sentencesIterator = sentences.iterator();
	while (sentencesIterator.hasNext()){
            ParserOutput parserOutput = null;
            Preposition preposition =  null;
            StringBuilder mappedString = new StringBuilder();

            String sentence = (String) sentencesIterator.next();
            Utils.tokenize(names, 0, stopwords, wordsSeparators, tokens, sentence);
		
            Iterator tokensIterator = tokens.iterator();
            while (tokensIterator.hasNext())
            {
                String token = (String) tokensIterator.next();
                int index = -1;

                try {
                    index = getCommandIndex(token, commands);
                    parserOutput = new ParserOutput(commands.get(index), token);
                    mappedString.append("command");
                } catch (NoSuchElementException exception){};

                try {
                    getPrepositionIndex(token, prepositions);
                    preposition = new Preposition(token);
                    mappedString.append(" preposition");
                } catch (NoSuchElementException exception){};

                try {
                    index = getObjectIndex(token, roomObjects);
                    parserOutput.addObject(roomObjects.get(index));
                    
                    if (preposition != null)
                        parserOutput.addPreposition(roomObjects.get(index), preposition);

                    preposition = null;

                    if (roomObjects.get(index) instanceof Door)
                        mappedString.append(" door");
                    else
                        mappedString.append(" roomObject");
                } catch (NoSuchElementException exception){};

                try {
                    index = getRoomIndex(token, rooms);
                    mappedString.append(" room");
                    parserOutput.addRoom(rooms.get(index));
                } catch(NoSuchElementException exception){};

                try {
                    index = getObjectIndex(token, inventoryObjects);
                    parserOutput.addObject(inventoryObjects.get(index));
                    if (preposition != null)
                            parserOutput.addPreposition(inventoryObjects.get(index), new Preposition(token));

                    preposition = null;
                    mappedString.append(" inventoryObject");
                } catch (NoSuchElementException exception){};

                if (index == -1)
                        throw new NotValidTokenException();
            }

            if (!(new String(mappedString)).matches(regex)){
                    throw new NotValidSentenceException();
            }
            else{
                    parserOutputs.add(parserOutput);
            }

        }    
        
        return parserOutputs;
    }
    
    
    private <T> int getElementIndex(String token, List<T> elements, BiPredicate<String, T> tester) throws NoSuchElementException {
	for (int i = 0; i < elements.size(); i++) {
            if (tester.test(token, elements.get(i)))
                return i;
            }

	throw new NoSuchElementException();
    }
    
    private int getCommandIndex(String token, List<Command> commands) throws NoSuchElementException{
        return getElementIndex(token, commands, commandTester);
    }

    private int getPrepositionIndex(String token, List<PrepositionType> prepositions) throws NoSuchElementException{
        return getElementIndex(token, prepositions, prepositionTester);
    }

    private int getObjectIndex(String token, List<AdvObject> objects) throws NoSuchElementException{
        return getElementIndex(token, objects, objectTester);
    }

    private int getRoomIndex(String token, List<Room> rooms) throws NoSuchElementException {
        return getElementIndex(token, rooms, roomTester);
    }

    private List<String> getAllNames(List<AdvObject> objects){
	List<String> names = new ArrayList();
        
	for (AdvObject object : objects){
            names.add(object.getName());
            names.addAll(object.getAlias());
        }

	return names;	
    }





    
}
