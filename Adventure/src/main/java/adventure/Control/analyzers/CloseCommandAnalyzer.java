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

/** La classe è specializzata nell'analisi semantica di un comando relativo al comando {@code CLOSE}
 *
 * @author Alessandro
 */
public class CloseCommandAnalyzer extends CommandAnalyzer{

    /** Costruisce l'oggetto CloseCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public CloseCommandAnalyzer(){
        super("Non puoi chiudere questo oggetto!");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando {@code CLOSE}
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException, 
            AmbiguousCommandException
    {
        CommandAnalysisResult commandAnalysisResult = analyzeUnaryCommand(parserOutput, PropertyType.OPENABLE, errorMessage);
	
        OpenCommandAnalyzer.disambiguateDoor(commandAnalysisResult, gameDescription, parserOutput);
        
        return commandAnalysisResult;
    }

}
