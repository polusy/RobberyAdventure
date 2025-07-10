/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.exceptions.NotValidSentenceException;
import adventure.identifiers.PropertyType;
import adventure.identifiers.PrepositionType;

/**
 *
 * @author utente
 */
public class UseCommandAnalyzer extends CommandAnalyzer{
    private String errorMessage ;
    
    public CommandAnalyzer(){
        super(" Non vorrai mica usare questa cosa! ");
    }
    
    CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException {
	CommandAnalysisResult commandAnalysisResult =  analyzeBinaryCommand(parserOutput, PropertyType.USABLE, PrepositionType.USE, errorMessage);
	
	return commandAnalysisResult;
    }
    
}
