/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.identifiers;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 *
 * @author Paolo
 */
public enum PrepositionType {

    DROP(new String[]{"negli", "nei", "nell", "nel", "nella", "nelle", "nello", "in", "dentro"}),

    BREAK(new String[]{"con", "usando", "coi", "col"}),

    OPEN(new String[]{"con", "usando", "coi", "col"}),
    
    ACTIVATE(new String[]{"con", "usando", "coi", "col"}),

    USE(new String[]{"con", "usando", "coi", "col"}),
    
    FILL(new String[]{"con", "usando", "coi", "col"}),
    
    PUSH(new String[]{}),
    
    CONSUME(new String[]{}),
    
    PICK_UP(new String[]{}),
    
    LOOK_AT(new String[]{}),

    END(new String[]{}),
    
    SAVE(new String[]{}),
    
    SEPARATOR(new String[]{"e", "ed", "poi", "dopo"});


    private Set<String> words;
    PrepositionType(String[] words)
    {
        this.words = new HashSet<>(Arrays.asList(words));
    }
    
    public Set<String> getWords(){
        return words;
    }
    
    
}
