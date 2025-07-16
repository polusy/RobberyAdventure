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
 
/** La classe è specializzata nell'analisi semantica di un comando relativo al comando MOVE
 *
 * @author Paolo
 */
public class MoveCommandAnalyzer extends CommandAnalyzer{

    /** Costruisce l'oggetto MoveCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public MoveCommandAnalyzer(){
        super("Non puoi spostare questo oggetto, e' inutile....");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando MOVE
     * 
     * Nel caso in cui il comando sia riferito ad un oggetto dell'inventario, il messaggio di errore viene modificato con un altro
     * più opportuno
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
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
