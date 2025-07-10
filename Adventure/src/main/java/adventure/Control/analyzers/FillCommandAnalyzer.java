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
public class FillCommandAnalyzer extends CommandAnalyzer{
    
    public FillCommandAnalyzer(){
        super("Cosa stai cercando di riempire? Guarda meglio...");
    }
    
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException, AmbiguousCommandException {
	CommandAnalysisResult commandAnalysisResult =  analyzeBinaryCommand(parserOutput, PropertyType.FILLABLE, PrepositionType.FILL, errorMessage);
	
	return commandAnalysisResult;
    }
}
