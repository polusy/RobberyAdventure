/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;

import java.util.Set;
import java.util.List;
import java.util.function.BiPredicate;

import adventure.utilities.Preposition;
import adventure.Entity.types.Command;
import adventure.Entity.objects.AdvObject;


/**
 *
 * @author utente
 */
public class Parser {
    final private Set<String> stopwords;
    final private List<Preposition> prepositions;
    final private BiPredicate<String, Command> commandTester;
    final private BiPredicate<String, Preposition>prepositionTester; 
    final private BiPredicate<String,AdvObject> objectTester;
    final private BiPredicate<String, Room> roomTester;
    final private String sentencesSeparators;
    final private String wordsSeparators;
    final private String regex;
     
}
