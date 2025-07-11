/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.ValuableObject;
import adventure.identifiers.CommandType;
import adventure.utilities.Utils;
import adventure.identifiers.RoomId;
import adventure.exceptions.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        initRooms();
    
    
    
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
    
    
    private void initRooms(){
        
        try{
            addRoom(new Room(RoomId.ANTE_VAULT, "ante vault", Utils.loadFileDescriptionInString(new File("./resources/descriptions/ante_vault"))));
            addRoom(new Room(RoomId.BATHROOM, "bagno", Utils.loadFileDescriptionInString(new File("./resources/descriptions/bathroom"))));     
            addRoom(new Room(RoomId.BEDROOM, "camera da letto", Utils.loadFileDescriptionInString(new File("./resources/descriptions/bedroom"))));     
            addRoom(new Room(RoomId.EAST_GARDEN, "giardino est", Utils.loadFileDescriptionInString(new File("./resources/descriptions/east_garden"))));
            addRoom(new Room(RoomId.GARAGE, "garage", Utils.loadFileDescriptionInString(new File("./resources/descriptions/garage"))));     
            addRoom(new Room(RoomId.GYM, "palestra", Utils.loadFileDescriptionInString(new File("./resources/descriptions/gym"))));   
            addRoom(new Room(RoomId.KITCHEN, "cucina", Utils.loadFileDescriptionInString(new File("./resources/descriptions/kitchen"))));
            addRoom(new Room(RoomId.LAUNDRY, "lavanderia", Utils.loadFileDescriptionInString(new File("./resources/descriptions/laundry"))));     
            addRoom(new Room(RoomId.LIBRARY, "biblioteca", Utils.loadFileDescriptionInString(new File("./resources/descriptions/library"))));   
            addRoom(new Room(RoomId.LIVING_ROOM, "salone", Utils.loadFileDescriptionInString(new File("./resources/descriptions/living_room"))));
            addRoom(new Room(RoomId.MIDDLE_GARDEN, "giardino centrale", Utils.loadFileDescriptionInString(new File("./resources/descriptions/middle_garden"))));     
            addRoom(new Room(RoomId.PAINTINGS_ROOM, "stanza dei quadri", Utils.loadFileDescriptionInString(new File("./resources/descriptions/paintings_room"))));   
            addRoom(new Room(RoomId.SECRET_ROOM, "stanza segreta", Utils.loadFileDescriptionInString(new File("./resources/descriptions/secret_room"))));
            addRoom(new Room(RoomId.SIDEWALK, "marciapiede", Utils.loadFileDescriptionInString(new File("./resources/descriptions/sidewalk"))));     
            addRoom(new Room(RoomId.STAIRCASE, "scalinata", Utils.loadFileDescriptionInString(new File("./resources/descriptions/staircase"))));   
            addRoom(new Room(RoomId.VAULT, "vault", Utils.loadFileDescriptionInString(new File("./resources/descriptions/vault"))));
            addRoom(new Room(RoomId.WEST_GARDEN, "giardino ovest", Utils.loadFileDescriptionInString(new File("./resources/descriptions/west_garden"))));  
            
        }catch(DuplicateException exception){}
        catch(IOException exception){};
        
        
    }
        
  
    
    private void addCommand(CommandType commandType, String name, String[] alias) throws NullPointerException{
        
        if (commandType == null)
            throw new NullPointerException();
        
        Command command = new Command(commandType, name, alias);
        this.getCommands().add(command);
        
    }
    
    private void addRoom(Room room)throws DuplicateException{
        if (this.getRooms().contains(room))
            throw new DuplicateException();
        
        this.getRooms().add(room);
           
    }
    
    
}
