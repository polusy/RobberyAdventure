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

/** La classe è specializzata nell'analisi semantica di un comando relativo al comando {@code CONSUME}
 *
 * @author Alessandro
 */
public class ConsumeCommandAnalyzer extends CommandAnalyzer {

    /** Costruisce l'oggetto ConsumeCommandAnalyzer, inizializzando il proprio messaggio di errore
     *
     */
    public ConsumeCommandAnalyzer(){
        super(" Non puoi in nessun modo utilizzare questa cosa! ");
    }
    
    /** Il metodo si occupa dell'analisi semantica del parserOutput, dove esso è relativo al comando {@code CONSUME}
     *
     * @param gameDescription Partita in esecuzione
     * @param parserOutput Risultato dell'elaborazione del parser della stringa inserita dall'utente
     * @return Risultato dell'analisi semantica dell'oggetto parserOutput
     * @throws NotValidSentenceException
     */
    @Override
    public CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException{
	return analyzeUnaryCommand(parserOutput, PropertyType.CONSUMABLE, errorMessage);
    }
}
