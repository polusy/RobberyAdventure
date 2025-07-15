/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.AmbiguousCommandException;
import adventure.identifiers.PropertyType;
import adventure.identifiers.PrepositionType;

/**
 *
 * @author utente
 */
public class UseCommandAnalyzer extends CommandAnalyzer{
    
    /**
     *
     */
    public UseCommandAnalyzer(){
        super(" Non vorrai mica usare questa cosa! ");
    }
    
    /**
     *
     * @param gameDescription
     * @param parserOutput
     * @return
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException, AmbiguousCommandException {
	CommandAnalysisResult commandAnalysisResult =  analyzeBinaryCommand(parserOutput, PropertyType.USABLE, PrepositionType.USE, errorMessage);
	
	return commandAnalysisResult;
    }
    
}
