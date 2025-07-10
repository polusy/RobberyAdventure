/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;

import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.identifiers.PropertyType;

/**
 *
 * @author utente
 */
public class PickUpCommandAnalyzer extends CommandAnalyzer{
    public PickUpCommandAnalyzer(){
        super(" Pensi veramente di riuscire a mettere questa cosa nello zaino!? ");
    }
    
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException
    {
	return CommandAnalyzer.analyzeUnaryCommand(parserOutput, PropertyType.PICKUPABLE, errorMessage);
    }
     
}
