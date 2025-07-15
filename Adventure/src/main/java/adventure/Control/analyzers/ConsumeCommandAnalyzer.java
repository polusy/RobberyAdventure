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

/**
 *
 * @author utente
 */
public class ConsumeCommandAnalyzer extends CommandAnalyzer {

    /**
     *
     */
    public ConsumeCommandAnalyzer(){
        super(" Non puoi in nessun modo utilizzare questa cosa! ");
    }
    
    /**
     *
     * @param gameDescription
     * @param parserOutput
     * @return
     * @throws NotValidSentenceException
     */
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException{
	return analyzeUnaryCommand(parserOutput, PropertyType.CONSUMABLE, errorMessage);
    }
}
