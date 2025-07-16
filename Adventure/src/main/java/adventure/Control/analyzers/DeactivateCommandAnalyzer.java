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

/** La classe è specializzata nell'analisi semantica di un comando relativo al comando DEACTIVATE
 *
 * @author Paolo
 */
public class DeactivateCommandAnalyzer extends CommandAnalyzer{

    /** Costruisce l'oggetto DeactivateCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public DeactivateCommandAnalyzer(){
        super(" Non puoi disattivare un oggetto del genere...");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando DEACTIVATE
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
     * @throws NotValidSentenceException
     */
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput) throws NotValidSentenceException
    {
	return analyzeUnaryCommand(parserOutput, PropertyType.ACTIVATABLE, errorMessage);
    }
    
    
    
}
