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
import adventure.identifiers.PropertyType;
import adventure.identifiers.PrepositionType;

/**
 *
 * @author Paolo
 */
public class ActivateCommandAnalyzer extends CommandAnalyzer{
    
        public ActivateCommandAnalyzer(){
        super("Mi sembra impossibile attivare questo oggetto!");
    }
    
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, AmbiguousCommandException
    {
	CommandAnalysisResult commandAnalysisResult = analyzeBinaryCommand(parserOutput, PropertyType.ACTIVATABLE, PrepositionType.ACTIVATE, errorMessage);
        return commandAnalysisResult;
    }
    
    
}
