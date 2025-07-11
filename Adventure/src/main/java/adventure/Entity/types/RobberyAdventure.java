/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.ValuableObject;
import adventure.identifiers.CommandType;
import adventure.exceptions.*;

/**
 *
 * @author Paolo
 */
public class RobberyAdventure extends GameDescription{
    
    private LootBag lootBag;

    public LootBag getLootBag() {
        return lootBag;
    }
    
    public void addObjectToLootBag(AdvObject object)throws DuplicateException, IllegalArgumentException{
        
        if (lootBag.contains(object))
            throw new DuplicateException();
        
        if (object instanceof ValuableObject)
            lootBag.add((ValuableObject)object);
        else 
            throw new IllegalArgumentException();
    }
    
    
    
    @Override
    public void init(){
        
        initCommands();
    
    
    
    };
    
    private void initCommands(){
        
        addCommand(CommandType.NORTH, "nord", new String[]{"n"});
	addCommand(CommandType.SOUTH, "sud", new String[]{"s"});
	addCommand(CommandType.EAST, "est", new String[]{"e"});
	addCommand(CommandType.WEST, "ovest", new String[]{"o"});
	addCommand(CommandType.LOOT_BAG, "bottino", new String[]{"sacco, zaino, refurtiva, borsa"});
	addCommand(CommandType.INVENTORY, "inventario", new String[]{"inv"});
	addCommand(CommandType.END, "esci", new String[]{"end", "fine","finire", "terminare", "termina", "exit", "basta"});
	addCommand(CommandType.SAVE, "salva", new String[]{" salvataggio", "salvare"});
	addCommand(CommandType.MENU, "menu", new String[]{});
	addCommand(CommandType.OPEN, "apri", new String[]{"spalancare", "spalanca", "aprire"});
	addCommand(CommandType.CLOSE, "chiudi", new String[]{"chiudere"});
	addCommand(CommandType.PUSH, "premi", new String[]{"premere", "spingi", "spingere", "pressare", "pressa","schiacciare", "schiaccia"});
	addCommand(CommandType.BREAK, "rompi", new String[]{"rompere", "distruggere", "sfasciare", "disintegrare" , "rompere", 	"distruggi", "sfascia", "disintegra","disintegrare", "sfonda","sfondare", "danneggia", "danneggiare"});
	addCommand(CommandType.PICK_UP, "raccogli", new String[]{"raccogliere", "prendere", "afferrare", "prendi", "afferra"});
        addCommand(CommandType.USE, "usa", new String[]{"utilizza", "utilizzare", "usare"});
	addCommand(CommandType.CONSUME, "consuma", new String[]{"consumare"});
	addCommand(CommandType.ACTIVATE, "attiva", new String[]{"accendi", "accendere", "attivare", "avvia", "avviare", "aziona"});
	addCommand(CommandType.DEACTIVATE, "disattiva", new String[]{"spegni", "spegnere", "disattivare", "arresta", 	"arrestare"});
	addCommand(CommandType.MOVE, "muovi", new String[]{"muovere", "sposta", "spostare", "solleva", "abbassa", "abbassare", "sollevare", 	"tira", "tirare", "trascina", "trascinare"});
	addCommand(CommandType.FILL, "riempi", new String[]{"riempire", "mesci", "mescere", "colma", "colmare"});
	addCommand(CommandType.LOOK_AT, "osserva", new String[]{"osservare", "guardare", "guarda", "vedere", "vedi", "descrivere", 	"descrivi"});

	}
        
  
    
    private void addCommand(CommandType commandType, String name, String[] alias) throws NullPointerException{
        
        if (commandType == null)
            throw new NullPointerException();
        
        Command command = new Command(commandType, name, alias);
        this.getCommands().add(command);
        
    }
    
    
}
