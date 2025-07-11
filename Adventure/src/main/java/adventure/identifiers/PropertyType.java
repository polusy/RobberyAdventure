/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package adventure.identifiers;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 *
 * @author utente
 */
public enum PropertyType{
    
    CONSUMABLE(new String[] {"consuma", "usa", "utilizza", "consumare", "usare", "utilizzare"}),

    ACTIVATABLE(new String[] {"accendi", "attiva", "usa", "utilizza", "avvia", "aziona", "spegni", "disattiva", 
    "arresta", "accendere", "attivare", "usare", "utilizzare", "avviare", "azionare", "spegnere", "disattivare",
    "arrestare"}),

    PUSHABLE(new String[]{"usa", "attiva", "spingi", "premi", "pressa", "schiaccia", "aziona", "usare", "attivare", 
    "spingere", "premere", "pressare", "schiacciare", "azionare"}),

    MOVABLE(new String[] {"muovi", "spingi", "sposta", "solleva", "abbassa", "tira", "trascina", "muovere", "spingere",
    "spostare", "sollevare", "abbassare", "tirare", "trascinare"}),

    OPENABLE(new String[] {"usa", "apri", "spalanca", "chiudi", "usare", "aprire", "spalancare", "chiudere"}),

    PICKUPABLE(new String[] {"raccogli", "prendi", "afferra", "lascia", "butta", "getta", "raccogliere", "prendere",
    "afferrare", "lasciare", "buttare", "gettare"}),

    FILLABLE(new String[] {"riempi", "mesci", "colma", "svuota", "riempire", "mescere", "colmare", "svuotare"}),

    BREAKABLE(new String[] {"rompi", "distruggi", "sfascia", "disintegra", "sfonda", "danneggia", "rompere", 
    "distruggere", "sfasciare", "disintregrare", "sfondare", "danneggiare"}),

    USABLE(new String[] {"usa", "usare", "utilizza", "utilizzare"});

    private Set<String> commandFilteringWords ;
    PropertyType(String[] commandFilteringWords)
    {
        this.commandFilteringWords = new HashSet<>(Arrays.asList(commandFilteringWords));
    }

    public Set<String> getCommandFilteringWords() {
        return commandFilteringWords;
    }
    
    



}
