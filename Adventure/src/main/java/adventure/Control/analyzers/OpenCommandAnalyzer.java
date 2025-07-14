/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.Door;
import adventure.exceptions.AmbiguousCommandException;
import adventure.exceptions.NotValidSentenceException;
import adventure.identifiers.PrepositionType;
import adventure.identifiers.PropertyType;
import adventure.Entity.types.CardinalPoint;
import adventure.Entity.types.Room;
import java.util.NoSuchElementException;


import java.util.Map;

/**
 *
 * @author Paolo
 */
public class OpenCommandAnalyzer extends CommandAnalyzer{
    
        public OpenCommandAnalyzer(){
        super(" Ma come pretendi di aprire quest ooggetto! ");
    }
    
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException, AmbiguousCommandException {
	CommandAnalysisResult commandAnalysisResult =  analyzeBinaryCommand(parserOutput, PropertyType.OPENABLE, PrepositionType.OPEN, errorMessage);
	
	return commandAnalysisResult;
    }
    
    public static void disambiguateDoor(CommandAnalysisResult commandAnalysisResult, GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException,AmbiguousCommandException{
        AdvObject toBeOpenedDoor = commandAnalysisResult.getTargetObject();
	
	if (toBeOpenedDoor instanceof Door)
	{
            Door door = (Door) toBeOpenedDoor;
            if (door.isSpecial())
            {
                if (parserOutput.getDoorRoom() != null)
                {
                    throw new NotValidSentenceException();
                }

            }
            else{
                    if (parserOutput.getDoorRoom() != null)
                    {
                        if (gameDescription.getCurrentRoom().reaches(parserOutput.getDoorRoom()))
                        {
                            for( Map.Entry<CardinalPoint, Room> roomLink : gameDescription.getCurrentRoom().getRoomLinks().entrySet())
                            {
                                if (roomLink.getValue().equals(parserOutput.getDoorRoom()))
                                {
                                    try{
                                            commandAnalysisResult.setTargetObject(gameDescription.getCurrentRoom().getLinkedDoor(roomLink.getKey()));
                                    }catch(NoSuchElementException exception){};
                                }
                            }
                        }
                        else
                        {
                            String message = "Non c'è una porta per questa stanza!!!";
                            commandAnalysisResult.setMessage(message);
                            commandAnalysisResult.setAnalysisPassed(false);
                        }
                    }
                    else{
                        throw new AmbiguousCommandException();
                    }
                }

	}
    }
}
