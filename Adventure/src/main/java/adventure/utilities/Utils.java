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

/**
 *
 * @author Paolo
 */
public class Utils {
    
    
       /**
     *
     * @param file
     * @return
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
    
    
    public static String loadFileDescriptionInString(File file) throws IOException{
        StringBuilder description = new StringBuilder("");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while (reader.ready()){
            description.append(reader.readLine());
        }
        reader.close();
        
        return new String(description);
    }
    
    
    
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
    
    
    
    public static <T> Set<T> union(Collection<T> collection1, Collection<T> collection2) {
	Set<T> set = new HashSet<>();
	set.addAll(collection1);
	set.addAll(collection2);
	return set;
    }
    
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
    
    public static List<String> toLowerCaseStringList (List<String> words){
        List<String> lowerCaseStringList = words
                .stream()
                .map(s -> s.toLowerCase())
                .collect(Collectors.toList());
        
        return lowerCaseStringList;
    }

    
    
}
