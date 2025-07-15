/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.AmbiguousCommandException;
import adventure.exceptions.NotValidSentenceException;
import adventure.identifiers.PrepositionType;
import adventure.identifiers.PropertyType;

/**
 *
 * @author Paolo
 */
public class BreakCommandAnalyzer extends CommandAnalyzer{
    
    /**
     *
     */
    public BreakCommandAnalyzer(){
        super("Non pensi che dovresti trovare un modo migliore per sfogare le tue frustrazioni?");
    }
    
    /**
     *
     * @param gameDescription
     * @param parserOutput
     * @return
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException, AmbiguousCommandException {
	CommandAnalysisResult commandAnalysisResult =  analyzeBinaryCommand(parserOutput, PropertyType.BREAKABLE, PrepositionType.BREAK, errorMessage);
        
        OpenCommandAnalyzer.disambiguateDoor(commandAnalysisResult, gameDescription, parserOutput);
	
	return commandAnalysisResult;
    }
    
}
