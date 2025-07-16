/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;

/** Contenitore di servizi generici di varia natur: operazioni su file, metodi per la gestione di stringhe e 
 * {@link Collection} generice
 *
 * @author Paolo
 */
public class Utils {
    
    
     /** Il metodo preleva delle stringhe dal file {@code file} passato per parametro e le restituisce
     *
     * @param file File da cui prelevare le stringhe
     * @return Insieme di stringhe prelevate dal file
     * @throws IOException
     */
    public static Set<String> loadFileListInSet(File file) throws IOException {
        Set<String> set = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            set.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
        return set;
    }
    
    /** Il metodo preleva delle stringhe dal file {@code file} passato per parametro e le restituisce come un'unica stringa
     *
     * @param file File da cui prelevare le stringhe
     * @return Stringa risultato della concatenazione delle stringhe prelevate dal file passato per parametro
     * @throws IOException
     */
    public static String loadFileDescriptionInString(File file) throws IOException{
        StringBuilder description = new StringBuilder("");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while (reader.ready()){
            description.append(reader.readLine());
        }
        reader.close();
        
        return new String(description);
    }
    
    /**  Il metodo divide la stringa {@code string} passata per parametro in token, individuandoli secondo l'espressione
     * regolare {@code separators} e li restituisce, scartando quelli contenuti nell'insieme {@code stopwords} passato
     * per parametro
     *
     * @param string Stringa da suddividere in token
     * @param stopwords Insieme di parole da ignorare nella costruzione del risultato
     * @param separators Espressione regolare che corrisponde ai separatori dei diversi token di cui si compone la stringa
     * {@code string} passata per parametro
     * @return Insieme dei token individuati dal metodo
     */
    public static List<String> parseString(String string, Set<String> stopwords, String separators) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split(separators);
        for (String t : split) {
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
        }
        return tokens;
    }
    
    /** Il metodo restituisce l'insieme unione a partire dalle due collection generiche passate per parametro
     *
     * @param <T>
     * @param collection1
     * @param collection2
     * @return Insieme unione i cui elementi provengono dalla {@code collection1} e dalla {@code collection2}
     */
    public static <T> Set<T> union(Collection<T> collection1, Collection<T> collection2) {
	Set<T> set = new HashSet<>();
	set.addAll(collection1);
	set.addAll(collection2);
	return set;
    }
    
    /** Il metodo inserisce nel parametro {@code tokens} i token estrapolati dalla stringa {@code sentence}
     * utilizzando l'espressione regolare {@code wordsSeparators} per individuare i token e scartando i token contenuti
     * nella lista {@code stopwords}. 
     * I token inseriti nel parametro {@code tokens} sono ricercati nella lista {@code words}
     * passata per parametro, dove ognuna di tali stringhe può contenere uno dei caratteri separatori specificati
     * nell'espressione regolare {@code wordsSeparators}
     *
     * @param words Parole tra cui riconosce i token da inserire nel parametro {@code tokens}
     * @param wordsIndex Indice della stringa {@code sentence} a partire dal quale iniziare il riconoscimento dei token
     * @param stopwords Insieme di parole da ignorare in caso di corrispondenza con un token, per il suo inserimento 
     * nel parametro {@code tokens}
     * @param wordsSeparators Espressione regolare secondo la quale individuare i token a partire dalla stringa 
     * {@code sentence}
     * @param tokens Lista di token che al termine dell'esecuzione del metodo conterrà tutti i token di interesse 
     * (scartando cioé quelli contenuti nelle {@code stopwords} ) estrapolati
     * dalla stringa {@code sentence}, nell'ordine in cui comparivano in essa
     * @param sentence Stringa da cui estrapolare i token da inserire nel parametr {@code tokens}
     */
    public static void tokenize(List<String> words, int wordsIndex, Set<String> stopwords, String wordsSeparators, List<String> tokens, String sentence)
    {

            if (!sentence.isEmpty())
            {
                    String previousSubsentence;
                    String successiveSubsentence;
                    boolean splitted = false;
                    int occurrenceIndex;

                    for (int i=wordsIndex ; i<words.size() && !splitted; i++)
                    {
                            if (sentence.contains(words.get(i)))
                            {
                                    splitted = true;
                                    occurrenceIndex = sentence.indexOf(words.get(i));
                                    previousSubsentence = sentence.substring(0, occurrenceIndex);
                                    successiveSubsentence = sentence.substring(occurrenceIndex + words.get(i).length(), sentence.length());			

                                    tokenize(words, i+1, stopwords, wordsSeparators, tokens, previousSubsentence);
                                    tokens.add(words.get(i));
                                    tokenize(words, i, stopwords, wordsSeparators, tokens, successiveSubsentence);
                            }
                    }

                    if (!splitted){
                            tokens.addAll(Utils.parseString(sentence, stopwords, wordsSeparators));
                    }

            }

    }
    
    /** Il metodo restituisce una lista di stringhe corrispondente alla lista {@code words} passata per parametro, dove
     * ogni stringa ha caratteri minuscoli
     *
     * @param words Lista di parole da trasformare in parole con caratteri tutti minuscoli
     * @return Lista di stringhe corrispondente alla lista {@code words} passata per parametro, dove ogni stringa
     * ha caratteri esclusivamente minuscoli
     */
    public static List<String> toLowerCaseStringList (List<String> words){
        List<String> lowerCaseStringList = words
                .stream()
                .map(s -> s.toLowerCase())
                .collect(Collectors.toList());
        
        return lowerCaseStringList;
    }

    
    
}
