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

/**
 *
 * @author Paolo
 */
public class MoveCommandAnalyzer extends CommandAnalyzer{

    /**
     *
     */
    public MoveCommandAnalyzer(){
        super("Non puoi spostare questo oggetto, e' inutile....");
    }
    
    /**
     *
     * @param gameDescription
     * @param parserOutput
     * @return
     * @throws NotValidSentenceException
     */
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException
    {
	CommandAnalysisResult commandAnalysisResult = analyzeUnaryCommand(parserOutput, PropertyType.MOVABLE, errorMessage);
        
        if (!commandAnalysisResult.isAnalysisPassed())
        {
            if(gameDescription.getInventory().contains(commandAnalysisResult.getTargetObject())){
                    commandAnalysisResult.setMessage("Ti svelo un segreto : spostare le cose nello zaino non lo rende più leggero...");
            }
        }
        
        return commandAnalysisResult;
    }
    
}
