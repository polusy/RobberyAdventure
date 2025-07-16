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

/** La classe è specializzata nell'analisi semantica di un comando relativo al comando ACTIVATE
 *
 * @author Paolo
 */
public class ActivateCommandAnalyzer extends CommandAnalyzer{
    
    /** Costruisce l'oggetto ActivateCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public ActivateCommandAnalyzer(){
        super("Mi sembra impossibile attivare questo oggetto!");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando ACTIVATE
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, AmbiguousCommandException
    {
	CommandAnalysisResult commandAnalysisResult = analyzeBinaryCommand(parserOutput, PropertyType.ACTIVATABLE, PrepositionType.ACTIVATE, errorMessage);
        return commandAnalysisResult;
    }
    
    
}
