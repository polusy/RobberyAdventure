/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;


import java.lang.IllegalArgumentException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import adventure.Entity.properties.*;
import adventure.Entity.conditions.*;
import adventure.Entity.effects.*;
import adventure.Entity.objects.*;
import adventure.identifiers.*;
import adventure.Boundary.ClientManager;


import adventure.utilities.Utils;
import adventure.exceptions.*;
import adventure.Entity.types.PropertyValue;
import adventure.Boundary.GUI.SafeDialogGUI;


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
    public void init() throws InconsistentInitializationException, PasswordGuessedException, EndGameException{
        
        ObjectId objectId = null;
        Property property = null;
        CommandType commandType = null;
        Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications = new HashMap();
        GameActionSpecification gameActionSpecification = null;
        CompleteCondition completeCondition = null;
        List<InventoryCondition> inventoryConditionOptions = new ArrayList<>();
        InventoryCondition inventoryCondition = null;
        Map<ObjectId, ObjectCondition> objectsConditions = new HashMap<>();
        ObjectCondition objectCondition = null;
        Set<PropertyValue> propertyWithValueConstraints = new HashSet<>();
        PropertyValue propertyValue = null;
        FailingConditionMessages failingConditionMessages = null;
        Map<ObjectId, String> missingNecessaryObjectsMessages = new HashMap<>();
        String failingInventoryConditionMessage = null;
        Map<ObjectId, Map<PropertyType, String>> failingObjectsConditionsMessages = new HashMap<>();
        Map<ObjectId, String> failingVisibilityConditionMessages = new HashMap<>();
        final String failingVisibilityConditionMessage = "Qui non c'e' un oggetto simile, guardati meglio intorno!" ;
        PassingConditionResult passingConditionResult = null;
        GameEffect gameEffect = null;
        CurrentPositionEffect currentPositionEffect = null;
        InventoryEffect inventoryEffect = null;
        LootBagEffect lootBagEffect = null;
        RoomEffect roomEffect = null;
        Map<ObjectId, ObjectEffect> objectsEffects = new HashMap<>();
        ObjectEffect objectEffect = null;
        Set<PropertyValue> propertyWithValueResults = new HashSet<>();
        ContainerEffect containerEffect = null;
        SpecialAction specialAction = null;
        String passingConditionMessage = null;
        InteractiveObject interactiveObject = null;
        AdvObject advObject = null;
        Door door = null;
        ValuableObject valuableObject = null;
        Room room1 = null;
        Room room2 = null;
              

        ClientManager clientManager = new ClientManager("http://localhost:8080");
        
        this.lootBag = new LootBag();
        initCommands();
        initRooms();
        this.setCurrentRoom(this.getRoomById(RoomId.SIDEWALK));
        

        // ==================================================================================================
        objectId = ObjectId.SLING;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, null, null, InteractiveObject.class, "Hai raccolto la fionda. "
                + "Assomiglia a quella che avevi da piccolo, che il vicino ti ha sequestrato dopo che gli hai "
                + "mandato in frantumi la finestra.", "Hai gettato la fionda");
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.EAST_GARDEN);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
        
        
        // ==================================================================================================
        objectId = ObjectId.VASE;
        
        gameActionSpecifications = new HashMap();
        property = new Movable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        commandType = CommandType.MOVE;
        
        
        // CompleteCondition
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
                
        propertyValue = new PropertyValue(PropertyType.MOVABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.VASE, objectCondition);
        
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.SECURITY_CAMERA, objectCondition);        
        
        
        completeCondition = new CompleteCondition(null, objectsConditions);
        
        // FailingConditionMessages
        
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        
        failingObjectsConditionsMessages.put(ObjectId.VASE, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.VASE).put(PropertyType.MOVABLE,
                "Hai gia' spostato questo oggetto, non e' questo il momento per rimetterti in forma!");
        
        failingObjectsConditionsMessages.put(ObjectId.SECURITY_CAMERA, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.SECURITY_CAMERA).put(PropertyType.BREAKABLE,
                "La telecamera ti sta guardando...rompila prima che qualcuno ti scopra..!");
        
        failingVisibilityConditionMessages.put(ObjectId.VASE, failingVisibilityConditionMessage);
        
        failingConditionMessages = new FailingConditionMessages(null,
        null, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        
        propertyValue = new PropertyValue(PropertyType.MOVABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(ObjectId.VASE, objectEffect);
        
                
        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.GARAGE_KEY, objectEffect);        
        
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Sotto il vaso c'era una chiave, un classico." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);             


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        "La telecamera ora e' decisamente innocua. Il sasso deve essersi conficcato in profondita'",
                advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.STAIRCASE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
       
        // ==================================================================================================    
        objectId = ObjectId.GARAGE_KEY;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, null, null, InteractiveObject.class, "Hai raccolto la chiave. "
                , "Hai lasciato la chiave");    
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.STAIRCASE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // =================================================================================================
        objectId = ObjectId.SECURITY_CAMERA;
        
        gameActionSpecifications = new HashMap();
        property = new Breakable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.BREAK;
          
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.SLING});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        
        objectCondition = new ObjectCondition(null, true);
        
        objectsConditions.put(ObjectId.SLING, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.SLING, "Non sapevo che potessi sparare"
                + " raggi laser dagli occhi, wow! Forse hai bisogno di qualcosa per romperla...");
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.BREAKABLE, "Hai gia' rotto"
                + " la telecamera, non penso possa riprenderti con quel buco nell'obiettivo!");
        
        failingVisibilityConditionMessages.put(ObjectId.SLING, failingVisibilityConditionMessage);
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);
        
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Il sasso che hai lanciato ha centrato la telecamera "
                + " di videosorveglianza, un'ottima mira! " ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.STAIRCASE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
        
        // =================================================================================================
        

            objectId = ObjectId.VAULT_DOOR;
            
            
            gameActionSpecifications = new HashMap();
            property = new Breakable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
            
            commandType = CommandType.BREAK;        
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.WELDING_MASK, objectId.THERMAL_LANCE});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.THERMAL_LANCE, objectCondition);
        
        
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        objectsConditions.put(ObjectId.VAULT_DOOR, objectCondition);
        
        objectCondition = new ObjectCondition(null, true);
        objectsConditions.put(ObjectId.WELDING_MASK, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.WELDING_MASK, "Ti manca qualcosa per proteggere gli occhi...una maschera..");
        missingNecessaryObjectsMessages.put(ObjectId.THERMAL_LANCE, "Ti manca lo strumento potentissimo per distruggere la porta..!");
        failingInventoryConditionMessage = "Ma pensi di riuscire a romperla con un pugno?";
        
        failingObjectsConditionsMessages.put(ObjectId.THERMAL_LANCE, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.THERMAL_LANCE).put(PropertyType.ACTIVATABLE, "La lancia termica deve essere attivata!");
        
        failingVisibilityConditionMessages.put(ObjectId.THERMAL_LANCE, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        failingVisibilityConditionMessages.put(ObjectId.WELDING_MASK, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueResults.add(propertyValue);
        
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(ObjectId.VAULT_DOOR, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai aperto e rotto la mega porta del vault...prego..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        ObjectId[] containerObjects = new ObjectId[]{ObjectId.FINGERPRINT_SCANNER};
        
        gameActionSpecifications.put(new Container(containerObjects), null);
        gameActionSpecifications.put(new Openable(false), null);
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        
        door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
        "la porta del vault e' rotta....chissa' chi ha combinato questo casino", advObject.getAlias(), true, gameActionSpecifications, false);
        
        room2 = this.getRoomById(RoomId.VAULT);
        room1 = this.getRoomById(RoomId.ANTE_VAULT);
        
        
        try{
            this.addDoor(door, room1, room2);
            this.addLinks(CardinalPoint.NORTH, door, room1, room2);
        }catch(AlreadyLinkedException exception){}
        catch(DuplicateException exception){};
        

        
        
        // ==========================================================================================
        
        objectId = ObjectId.FINGERPRINT_SCANNER;
        
        gameActionSpecifications = new HashMap();
        property = new Usable(true);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.USE;
        
        
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = null;
        
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = null;
        failingVisibilityConditionMessages = null;
        failingInventoryConditionMessage = null;
                

        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        objectsEffects = null;
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
                
        containerEffect = null;
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Benissimo, hai messo il pollice sopra...ma evidentemente non funziona..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.ANTE_VAULT);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
        
        
        // ==========================================================================================
        
        
        
        // =================================================================================================

            objectId = ObjectId.PAINTINGS_ROOM_DOOR;
            
            gameActionSpecifications = new HashMap();;

            containerObjects = new ObjectId[]{ObjectId.DOORKNOB};
            gameActionSpecifications.put(new Container(containerObjects), null);
            
            property = new Openable(false);
            gameActionSpecifications.put(property, null);
            
            List<ObjectId[]> newAuxiliaryObjectsList = new ArrayList<>();
            newAuxiliaryObjectsList.add(new ObjectId[]{ObjectId.RUBBER_GLOVES});
            
            this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, newAuxiliaryObjectsList, null, 
            null, InteractiveObject.class, "Hai aperto la porta della stanza dei quadri...che uomo fortunato...stai per trovare il vero bottino!", "Hai chiuso la porta della stanza dei quadri...");
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.VAULT);
            room2 = this.getRoomById(RoomId.PAINTINGS_ROOM);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.NORTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        
        
        // ========================================================================================== 
        //                              
        
            objectId = ObjectId.DOORKNOB;
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.VAULT);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
            
            
        
        
        // ==========================================================================================
        
        objectId = ObjectId.LOCKERS;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.GOLD_INGOTS, ObjectId.VAULT_ROLL_OF_BILLS};
        gameActionSpecifications.put(new Container(containerObjects), null);

        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, null, null, 
        containerObjects, InteractiveObject.class, "Hai aperto gli armadietti del vault....ruba il piu' possibile caro amico...!", null);
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        
        room1 = this.getRoomById(RoomId.VAULT);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
          
        // ========================================================================================== 
        //                              
        
            objectId = ObjectId.GOLD_INGOTS;
        
            gameActionSpecifications = new HashMap();
            property = new Pickupable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId,null, ObjectId.LOCKERS,
            null, ValuableObject.class, "Hai raccolto dei lingotti preziosi...", null);
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), false, gameActionSpecifications, 320400);


            room1 = this.getRoomById(RoomId.VAULT);
            try{
                room1.addObject(valuableObject);
            }catch(DuplicateException exception){};
        
        
        
          // ==========================================================================================
           
            objectId = ObjectId.VAULT_ROLL_OF_BILLS;
        
            gameActionSpecifications = new HashMap();
            property = new Pickupable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId,null, ObjectId.LOCKERS, 
                    null, ValuableObject.class, "Hai raccolto delle stupende mazzette di contanti", null);
            
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), false, gameActionSpecifications, 200000);


            room1 = this.getRoomById(RoomId.VAULT);
            try{
                room1.addObject(valuableObject);
            }catch(DuplicateException exception){}
            
            
        // ========================================================================================== 
        
        
            objectId = ObjectId.SECRET_ROOM_DOOR;
            
            gameActionSpecifications = new HashMap();
            property = new Openable(false);
            gameActionSpecifications.put(property, null);
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.SECRET_ROOM);
            room2 = this.getRoomById(RoomId.LIBRARY);
        
            try{
                room1.addDoorLink(CardinalPoint.WEST, door);
                room1.addRoomLink(CardinalPoint.WEST, room2);
            }catch(DuplicateException exception){};
        
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.BUTTON;
        
        gameActionSpecifications = new HashMap();
        property = new Pushable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.PUSH;
        

        
        objectsEffects = new HashMap<>();        
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        propertyValue = new PropertyValue(PropertyType.PUSHABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BUTTON, objectCondition);
        
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.SECRET_ROOM_DOOR, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
        
        failingInventoryConditionMessage = null;

        
        failingObjectsConditionsMessages.put(ObjectId.SECRET_ROOM_DOOR, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.SECRET_ROOM_DOOR).put(PropertyType.OPENABLE, "Il varco e' gia' aperto....");
        
        failingVisibilityConditionMessages.put(ObjectId.SECRET_ROOM_DOOR, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        failingVisibilityConditionMessages.put(ObjectId.BUTTON, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        containerEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, true);
        propertyWithValueResults.add(propertyValue);
                
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.SECRET_ROOM_DOOR, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai aperto il varco segreto per la biblioteca..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification); 
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.SECRET_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        // ==========================================================================================
          
          
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.STEP_LADDER;
        
        gameActionSpecifications = new HashMap();
        property = new Usable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.USE;
        

        
        objectsEffects = new HashMap<>();        
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = null;
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = null;
        failingVisibilityConditionMessages = null;
        failingInventoryConditionMessage = null;
;
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        
        objectsEffects = null;
        
        currentPositionEffect = new CurrentPositionEffect(RoomId.SECRET_ROOM);
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
                
        containerEffect = null;
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Sei giunto nella famosa stanza segreta...cosi' segreta che non c'e' niente..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification); 
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.ANTE_VAULT);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        // ==========================================================================================
          
          
        // ========================================================================================== 
                                   
        
             objectId = ObjectId.THE_STARRY_NIGHT;
        
            gameActionSpecifications = new HashMap();
            property = new Pickupable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property,objectId,null, null,
                    null, ValuableObject.class, "Hai raccolto il quadro dei tuoi sogni....", null); 
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, 1500000);


            room1 = this.getRoomById(RoomId.PAINTINGS_ROOM);
            try{
                room1.addObject(valuableObject);
            }catch(DuplicateException exception){}
        
        
        
          // ==========================================================================================
          
            objectId = ObjectId.VOCAZIONE_SAN_MATTEO;
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.PAINTINGS_ROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
            
            // ==========================================================================================
          
            objectId = ObjectId.THE_SCREAM;
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.PAINTINGS_ROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
            
            // ==========================================================================================
          
            objectId = ObjectId.THE_KISS;
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.PAINTINGS_ROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};

            // ==========================================================================================
          
            objectId = ObjectId.THE_DEATH_OF_MARAT;
            
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.PAINTINGS_ROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
            
            
            // ==========================================================================================
            

          
            
            
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.WASHING_MACHINE;
        
        gameActionSpecifications = new HashMap();
        property = new Usable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.USE;
        

        
        objectsEffects = new HashMap<>();        
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.DETERGENT});
        inventoryConditionOptions.add(inventoryCondition);
        
        objectCondition = new ObjectCondition(null, true);
        objectsConditions.put(ObjectId.DETERGENT, objectCondition);
        
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
        failingObjectsConditionsMessages = null;
                
        missingNecessaryObjectsMessages.put(ObjectId.DETERGENT, "Vuoi usare la lavatrice senza detersivo....che matto!");
        failingInventoryConditionMessage = null;
        
        failingVisibilityConditionMessages.put(ObjectId.DETERGENT, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        

        objectsEffects = null;
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;

                
        containerEffect = null;
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "La lavatrice non funziona, prova a fare altro....spostala magari..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification); 
        
        
        
        
        gameActionSpecifications = new HashMap();
        property = new Movable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        addStandardGameActionSpecification_Movable(gameActionSpecifications, ObjectId.WASHING_MACHINE,
                ObjectId.TRAPDOOR, "Hai spostato la lavatrice e hai trovato una botola...chissa' dove porta!");
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        
        
          // ==========================================================================================
          
          
         // ========================================================================================== 
        //                             
        
        objectId = ObjectId.TRAPDOOR;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, null);

        
        ObjectId[] auxiliaryObjects = new ObjectId[]{ObjectId.TRAPDOOR_KEY};
        List<ObjectId[]> newAuxiliaryObjects = new ArrayList<>();
        newAuxiliaryObjects.add(auxiliaryObjects);
        
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.TRAPDOOR, newAuxiliaryObjects,
        null, null, InteractiveObject.class, "Sei riuscito ad aprire la botola, grandioso....", "Hai chiuso la botola" );
        
        
        gameActionSpecifications = new HashMap();
        property = new Usable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.USE;
           
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.TRAPDOOR, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
        
        failingInventoryConditionMessage = null;
        
        failingObjectsConditionsMessages.put(ObjectId.TRAPDOOR, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.TRAPDOOR).put(PropertyType.OPENABLE, "La botola ha una serratura...sciocco...");
        
        failingVisibilityConditionMessages.put(ObjectId.TRAPDOOR, "VUoi usare un oggetto invisibile....?");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult

        objectsEffects = null;
        
        currentPositionEffect = new CurrentPositionEffect(RoomId.ANTE_VAULT);
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;

                
        containerEffect = null;  
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Che volo...ahhhhh! sei arrivato in una stanza stranissima con una mega porta blindata..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification); 
        
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.RIGHT_SHELVING_UNIT;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.FUEL_CAN, ObjectId.BATTERIES, ObjectId.BATTERY_CHARGER};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        // ==========================================================================================   
        
            objectId = ObjectId.FUEL_CAN;
            
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.FUEL_CAN, null, ObjectId.RIGHT_SHELVING_UNIT, null,
        InteractiveObject.class, "Hai raccolto una tanica...!", "Hai posato la tanica qui da qualche parte nella stanza...");
        
        
        property = new Fillable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.FILL;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.TUBE, ObjectId.FUEL_CAN});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.FUEL_FILLER_NECK, objectCondition);
        
        
        propertyValue = new PropertyValue(PropertyType.FILLABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.FUEL_CAN, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.TUBE, "Ti manca qualcosa con cui prendere la benzina....");
        failingInventoryConditionMessage = null;
        
        failingObjectsConditionsMessages.put(ObjectId.FUEL_FILLER_NECK, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.FUEL_FILLER_NECK).put(PropertyType.OPENABLE, "Per prendere la benzina devi aprire la bocchetta...genio!");
        failingObjectsConditionsMessages.put(ObjectId.FUEL_CAN, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.FUEL_CAN).put(PropertyType.FILLABLE, "Hai gia' riempito la tanica...");
        
        failingVisibilityConditionMessages.put(ObjectId.FUEL_CAN, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        failingVisibilityConditionMessages.put(ObjectId.FUEL_FILLER_NECK, "Qui non c'e' un oggetto simile, guardati meglio intorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.FILLABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        containerEffect = null;
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.FUEL_CAN, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai riempito la tanica di benzina, grande....Adesso usala per caricare qualcosa....!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
          // ========================================================================================== 
          
          
        objectId = ObjectId.BATTERIES;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BATTERIES, null, ObjectId.RIGHT_SHELVING_UNIT, null,
        InteractiveObject.class, "Hai raccolto le pile...wow...prendi e metti in saccoccia...!", "Hai posato le pile qui da qualche parte...");
        
        
        property = new Activatable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.ACTIVATE;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.BATTERIES, ObjectId.BATTERY_CHARGER});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BATTERIES, objectCondition);
        
        objectCondition = new ObjectCondition(null, true);
        objectsConditions.put(ObjectId.BATTERY_CHARGER, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.BATTERIES, "Cosa vuoi caricare, il nulla??...vai a cercarti delle batterie...!");
        missingNecessaryObjectsMessages.put(ObjectId.BATTERY_CHARGER, "Cosa vuoi caricare senza caricatore? vattelo a cercare....");
        failingInventoryConditionMessage = null;
        
        failingObjectsConditionsMessages.put(ObjectId.BATTERIES, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.BATTERIES).put(PropertyType.ACTIVATABLE, "Le pile sono state gia' attivate...stupidino!");
        
        failingVisibilityConditionMessages.put(ObjectId.BATTERIES, "Qui non c'e' un oggetto simile, guardati intorno!");
        failingVisibilityConditionMessages.put(ObjectId.BATTERY_CHARGER, "Qui non c'e' un oggetto simile, guardati intorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        containerEffect = null;
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.BATTERIES, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai finalmente attivato le pile....che bravissima persona...guardati intorno e cerca un trapano da attivare, gia' che ci sei...." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        // ==========================================================================================
        
        
        objectId = ObjectId.BATTERY_CHARGER;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BATTERY_CHARGER, null, ObjectId.RIGHT_SHELVING_UNIT, null,
        InteractiveObject.class, "Hai raccolto un caricatore...vedi se riesci a caricare delle pile...!", "Hai lasciato il caricabatterie qui nella stanza...");
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.LEFT_SHELVING_UNIT;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.DETERGENT, ObjectId.DRILL};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        // ========================================================================================== 
        
        
        objectId = ObjectId.DETERGENT;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BATTERY_CHARGER, null, ObjectId.LEFT_SHELVING_UNIT, null,
        InteractiveObject.class, "Hai raccolto un detersivo...hai voglia di fare una bella lavatrice ?...", "Hai lasciato il detersivo da qualche parte nella stanza...");
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        

        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.DRILL;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.DRILL, null, ObjectId.LEFT_SHELVING_UNIT, null,
        InteractiveObject.class, "Hai raccolto un trapano...buona fortuna...", "Hai lasciato il trapano da qualche parte nella stanza...");
        
        property = new Activatable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.ACTIVATE;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.DRILL, ObjectId.BATTERIES});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BATTERIES, objectCondition);
        
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.DRILL, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.DRILL, "Vuoi attivare il trapano...senza il trapano..!");
        missingNecessaryObjectsMessages.put(ObjectId.BATTERIES, "Ti mancano delle batterie per attivare questo aggeggione!");
        failingInventoryConditionMessage = null;
        
        failingObjectsConditionsMessages.put(ObjectId.DRILL, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.DRILL).put(PropertyType.ACTIVATABLE, "Il trapano e' gia' attivato...che stupido!");
        failingObjectsConditionsMessages.put(ObjectId.BATTERIES, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.BATTERIES).put(PropertyType.ACTIVATABLE, "Le pile nel tuo inventario sono scariche! tutto ti devo spiegare?!");
        
        failingVisibilityConditionMessages.put(ObjectId.DRILL, "Qui non c'e' un oggetto simile, guardati intorno...");
        failingVisibilityConditionMessages.put(ObjectId.BATTERIES, "Qui non c'e' un oggetto simile, guardati intorno...");
        failingInventoryConditionMessage = null;
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        containerEffect = null;
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.DRILL, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai attivato il trapano...drrrrrr...potrai scassinare molte cassaforti!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification); 
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.LAUNDRY);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        // ========================================================================================== 
        //                             
        
            objectId = ObjectId.DOUBLE_BED;
        
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.BEDROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
        
        
        
        // ========================================================================================== 
        
        objectId = ObjectId.CHEST;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.PHOTO_ALBUM};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.CHEST, null,
        null, containerObjects, InteractiveObject.class, "Hai aperto il baule e hai trovato un grazioso album fotografico del matrimonio del magnate...",
        "Hai chiuso il baule,...bravo");
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BEDROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.PHOTO_ALBUM;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.PHOTO_ALBUM, null,
        objectId.CHEST, null, InteractiveObject.class, "Hai raccolto l'album fotografico del magnate....sii contento e guardalo un po'!",
        "Hai lasciato l'album qui da qualche parte nella stanza...!");
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BEDROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
         // ========================================================================================== 
        //                              
        
        objectId = ObjectId.BEDSIDE_TABLE;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.WEDDING_RINGS};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BEDROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        // ========================================================================================== 
        
        
                
        objectId = ObjectId.WEDDING_RINGS;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
           
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.WEDDING_RINGS, null,
        objectId.BEDSIDE_TABLE, null, ValuableObject.class, "Hai rubato le fedi del magnate....dovresti essere felice!",
        null);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications, 22103);


        room1 = this.getRoomById(RoomId.BEDROOM);
        try{
            room1.addObject(valuableObject);
        }catch(DuplicateException exception){}
        
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.WARDROBE; //AdvObject
        
        
            //adding object to room
            
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.BEDROOM);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};

        
        
        
        // ========================================================================================== 
        //                              
        
        objectId = ObjectId.LIVINGROOM_EAST_DOOR;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.LIVINGROOM_EAST_DOOR, null,
        null, null, InteractiveObject.class, "Hai aperto la porta che collega il salone alla camera da letto...vai in giro per la casa ora!",
        "Hai chiuso la porta est del salone...complimenti!");
        
        
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.LIVING_ROOM);
            room2 = this.getRoomById(RoomId.BEDROOM);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.EAST, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        

        
        // ==========================================================================================
           
        objectId = ObjectId.WC; //ContainerOnly
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.TOILET_TANK};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        // ==========================================================================================
        
        
        objectId = ObjectId.TOILET_TANK; //Container and openable
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.BRACELET};
        gameActionSpecifications.put(new Container(containerObjects), null);

        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.TOILET_TANK, null,
        null, containerObjects, InteractiveObject.class, "Hai aperto la cassetta dello scarico e noti un braccialetto d'oro...chissa' che ci fa' qua dentro...",
        "Hai chiuso la cassetta dello scarico finalmente...c'era una puzza tremenda!");
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        
        
        
        // ==========================================================================================
           
        objectId = ObjectId.BRACELET;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BRACELET, null,
        ObjectId.TOILET_TANK, null, ValuableObject.class, "Hai raccolto un braccialetto d'oro, metti in saccoccia!",
        null);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications, 1200);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(valuableObject);
        }catch(DuplicateException exception){}
  
        
        // ==========================================================================================
           
        objectId = ObjectId.BAR_OF_SOAP;  //advObject
        
                
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
        
        // ==========================================================================================
        
        objectId = ObjectId.WASH_BASIN; 
        
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.BAR_OF_SOAP, ObjectId.DRAWER};
        gameActionSpecifications.put(new Container(containerObjects), null);
        
      
        property = new Usable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        commandType = CommandType.USE;
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = null;
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = null;
        failingVisibilityConditionMessages = null;
               
        failingInventoryConditionMessage = null;
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        objectsEffects = null;
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        containerEffect = null;
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Ti sei lavato le mani...che sfaticato...non vuoi proprio rubare!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
                   
        
      
        // ==================================================================================================    
        objectId = ObjectId.GARAGE_SHUTTER;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        List<ObjectId[]> necessaryObjectsId = new ArrayList<>();
        necessaryObjectsId.add(new ObjectId[] {ObjectId.GARAGE_KEY});
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                necessaryObjectsId, null, null, Door.class, 
                "Hai aperto la serranda del garage! "
                        + "Speriamo che il proprietario non ci tenga dentro le solite cianfrusaglie. ", 
                "Hai chiuso la serranda del garage."); 
        
 
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        
        door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications, true);
        
        room1 = this.getRoomById(RoomId.WEST_GARDEN);
        room2 = this.getRoomById(RoomId.GARAGE);
        
        
        try{
            this.addDoor(door, room1, room2);
            this.addLinks(CardinalPoint.NORTH, door, room1, room2);
        }catch(AlreadyLinkedException exception){}
        catch(DuplicateException exception){};        
        
       
        
        
        // ==================================================================================================    
        objectId = ObjectId.FUEL_FILLER_NECK;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, null, null, InteractiveObject.class, 
                "Hai aperto la bocchetta per il rifornimento del carburante. Non vedi l'ora di allontanarti, "
                        + "tu odi l'odore del gasolio", 
                "Hai chiuso la boccherra per il rifornimento, (finalmente, "
                        + "quell'odore ti dava la nausea"); 


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ==================================================================================================    
        objectId = ObjectId.TOOL_CABINET;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.HAMMER, ObjectId.ELECTRIC_SAW, ObjectId.WRENCH, 
            ObjectId.POWER_UNIT, ObjectId.THERMAL_LANCE});
        gameActionSpecifications.put(property, null);
        
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, objectId, new ObjectId[] {ObjectId.HAMMER, 
                    ObjectId.ELECTRIC_SAW, ObjectId.WRENCH, ObjectId.POWER_UNIT}, InteractiveObject.class, 
                "Hai aperto l'armadio degli attrezzi. Sei stato avvolto da una "
                        + "densa nuvola di polvere che ti sta facendo tossire fino ad avere le lacrime agli occhi.", 
                "Hai chiuso l'armadio del garage. C'erano piu' acari li' dentro che nel "
                        + "vecchio tappeto di tua nonna.");
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        // ==================================================================================================
        objectId = ObjectId.HAMMER;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai preso il martello. Sei cosi' maldestro che ci e' mancato poco che ti cadesse su un piede",
                "Hai buttato il martello, di' la verita' che non ce la facevi piu' a "
                        + "portarlo con te in giro");
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};   
        
        
  
  // ========================================================================================================
        objectId = ObjectId.DRAWER;
        
        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.BANDAGES};
        gameActionSpecifications.put(new Container(containerObjects), null);
  
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.DRAWER, null,
        null, containerObjects, InteractiveObject.class, "Hai aperto il cassetto del lavandino...trovi delle bende sfuse!",
        "Hai chiuso il cassetto del lavandino!"); 
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
        

        // ==================================================================================================
        objectId = ObjectId.ELECTRIC_SAW;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai preso la sega elettrica, fai attenzione a non amputarti un dito!",
                "Hai gettato la sega elettrica");

        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ==================================================================================================
        objectId = ObjectId.WRENCH;

        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());        
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai raccolto la chiave inglese, probabilmente e' l'unico strumento che "
                        + "riconosceresti in una ferramenta.!",
                "Hai buttato la chiave inglese");      
      

        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.EAST_GARDEN);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ==========================================================================================
        objectId = ObjectId.BANDAGES;
      
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
      
         this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BANDAGES, null,
        ObjectId.DRAWER, null, InteractiveObject.class, "Hai raccolto delle bende dal cassetto...bravoh!",
        "Hai lasciato delle bende in giro per la casa!");
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}     
        

        // ==================================================================================================
        objectId = ObjectId.POWER_UNIT;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai raccolto il generatore elettrico ",
                "Ti sei liberato del generatore elettrico, all'inizio non sembrava cosi'"
                        + "pesante!"); 

        property = new Activatable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.ACTIVATE;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.FUEL_CAN, objectId});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        

        propertyValue = new PropertyValue(PropertyType.FILLABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.FUEL_CAN, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
        
        missingNecessaryObjectsMessages.put(objectId, "Non hai questo oggetto nell'inventario");        
        missingNecessaryObjectsMessages.put(ObjectId.FUEL_CAN, "Il generatore elettrico non 'genera' "
                + "l'energia elettrica dal nulla. La legge di Lavoisier si fa in secondo superiore!");
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.ACTIVATABLE, 
                "Hai gia' attivato il generatore! Non senti il rumore che fa?");
        
        failingObjectsConditionsMessages.put(ObjectId.FUEL_CAN, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.FUEL_CAN).put(PropertyType.FILLABLE, 
                "Magari il generatore avrebbe bisogno di qualcosa da bruciare per funzioanre...");
        
        failingVisibilityConditionMessages.put(objectId, failingVisibilityConditionMessage);
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);
        
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Hai attivato il generatore! Fa un baccano assordante" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);                
        

        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};


        
        // ==================================================================================================
        objectId = ObjectId.THERMAL_LANCE;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai raccolto la lancia termica. Non ne avevi mai vista una da vicino, "
                        + "e' proprio come quelle dei film", "Hai buttato la lancia termica"); 

        property = new Activatable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.ACTIVATE;
      
      
        // CompleteCondition
        
        inventoryConditionOptions = new ArrayList<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.POWER_UNIT, objectId, ObjectId.WELDING_MASK});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        

        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.POWER_UNIT, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.POWER_UNIT, "Mi dispiace ma la lancia termica non funziona a pile!");
        missingNecessaryObjectsMessages.put(ObjectId.WELDING_MASK, "Hai intenzione di bruciarti tutta "
                + "la faccia?");
        missingNecessaryObjectsMessages.put(objectId, "Non hai questo oggetto nell'inventario");
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.ACTIVATABLE, 
                "Hai gia' attivato la lancia termica!");
        
        failingObjectsConditionsMessages.put(ObjectId.POWER_UNIT, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.POWER_UNIT).put(PropertyType.ACTIVATABLE, 
                "Forse devi fare funzionare qualcosa... oltre al cervello");
        
        failingVisibilityConditionMessages.put(objectId, failingVisibilityConditionMessage);
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);
        
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Hai acceso la lancia termica, sta diventando incandescente..." ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);        


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};

        
        // ==========================================================================================
        
       
        objectId = ObjectId.BIDET;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
            
            
        // ==========================================================================================
        
       
        objectId = ObjectId.SHOWER;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
            
            
        // ==========================================================================================
        
       
        objectId = ObjectId.TOWEL;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.BATHROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
        
        
        // ==========================================================================================
           
        objectId = ObjectId.BATHROOM_DOOR;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.BATHROOM_DOOR, null,
        null, null, InteractiveObject.class, "Hai aperto la porta che collega il bagno alla camera da letto...e sii un po' felice!",
        "Hai chiuso la porta del bagno, bravissimo!");
        
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.BATHROOM);
            room2 = this.getRoomById(RoomId.BEDROOM);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.NORTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        
        
        
        // ==========================================================================================
           
        objectId = ObjectId.BATHROOM_WINDOW;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<>());
        
        commandType = CommandType.OPEN;
        
        // CompleteCondition
        inventoryConditionOptions = null;
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BATHROOM_WINDOW, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = null;
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        
        failingObjectsConditionsMessages.put(ObjectId.BATHROOM_WINDOW, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.BATHROOM_WINDOW).put(PropertyType.OPENABLE, "la finestra e' gia' aperta...che strano uomo che sei!");
        
        failingVisibilityConditionMessages.put(ObjectId.BATHROOM_WINDOW, "Nessun oggetto da queste parti, vediti meglio attorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = null;
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
                
        containerEffect = null;
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "la finestra non puo' essere aperta cosi' semplicemente...!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        
        
        property = new Breakable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        commandType = CommandType.BREAK;
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.ELECTRIC_SAW});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BATHROOM_WINDOW, objectCondition);
        
        objectCondition = new ObjectCondition(null, true);
        objectsConditions.put(ObjectId.ELECTRIC_SAW, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.ELECTRIC_SAW, "Ti manca qualcosa di elettrico per riuscire a rompere la finestra!");
        failingInventoryConditionMessage = null;
        
        failingObjectsConditionsMessages.put(ObjectId.BATHROOM_WINDOW, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.BATHROOM_WINDOW).put(PropertyType.OPENABLE, "la finestra e' gia' aperta...che strano uomo che sei!");
        failingObjectsConditionsMessages.get(ObjectId.BATHROOM_WINDOW).put(PropertyType.BREAKABLE, "la finestra e' gia' rotta...scemo!");
        
        failingVisibilityConditionMessages.put(ObjectId.ELECTRIC_SAW, "Nessun oggetto da queste parti, vediti meglio attorno!");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = null;
        inventoryEffect = null;
        lootBagEffect = null;
        roomEffect = null;
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, true);
        propertyWithValueResults.add(propertyValue);
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        containerEffect = null;
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.BATHROOM_WINDOW, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "Hai finalmente un punto d'ingresso, bravo ladro! cosi' si rompono le finestre!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
        
        
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            "La finestra del bagno e' rotta....sei stato grandioso!", advObject.getAlias(), true, gameActionSpecifications, true);

            room1 = this.getRoomById(RoomId.EAST_GARDEN);
            room2 = this.getRoomById(RoomId.BATHROOM);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.NORTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
      

         // ==================================================================================================    
        objectId = ObjectId.SHELF;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.WELDING_MASK, ObjectId.TUBE});
        gameActionSpecifications.put(property, null);      
        
 
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
         // ==================================================================================================
        objectId = ObjectId.WELDING_MASK;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.SHELF, null, InteractiveObject.class, 
                "Hai raccolto la maschera da saldatore, chissa' com'e' vederci attraverso",
                "Hai buttato la maschera da saldatore");
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};

        
        
        // ==================================================================================================
        objectId = ObjectId.TUBE;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.SHELF, null, InteractiveObject.class, 
                "Hai raccolto il tubo di gomma, almeno con questo non c'e' il rischio di farti male",
                "Ti sei liberato del tubo");
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        

         // ==================================================================================================    
        objectId = ObjectId.DOGHOUSE;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.DOG_TAG});
        gameActionSpecifications.put(property, null);    
        
    
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        

        
        // ==================================================================================================
        objectId = ObjectId.DOG_TAG;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.DOG_TAG, null, InteractiveObject.class, 
                "Hai raccolto la medaglietta del cane del proprietario",
                "Hai gettato la medaglietta del cane. Comunque era piu' bella del tuo portachiavi!");

        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        

         // ==================================================================================================    
        objectId = ObjectId.LIVINGROOM_TABLE;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.SHOPPING_LIST, ObjectId.LUGGAGE_OBJECTS_LIST,
        ObjectId.NEWSPAPER_PAGE});
        gameActionSpecifications.put(property, null);
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        


        // ==================================================================================================    
        objectId = ObjectId.DISPLAY_CABINET;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.SPOON});
        gameActionSpecifications.put(property, null);
        
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, objectId, new ObjectId[] {ObjectId.SPOON}, InteractiveObject.class, 
                "Hai spalancato le ante della cristalliera e ti stai ricordando "
                        + "quella volta in cui da piccolo prendesti in pieno la collezione dei vetri di Murano "
                        + "di tua madre con una pallonata", "Hai chiuso la cristalliera."
                                + " Quanta roba raffinata li' dentro!");
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        

        // ==================================================================================================
        objectId = ObjectId.SPOON;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.DISPLAY_CABINET, null, InteractiveObject.class, 
                "Hai preso il cucchiaio. Che bello, scintilla!",
                "Hai lasciato il cucchiaio");


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
         // ==================================================================================================    
        objectId = ObjectId.SOFA;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.REMOTE_CONTROL});
        gameActionSpecifications.put(property, null); 
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        


        // ==================================================================================================
        objectId = ObjectId.REMOTE_CONTROL;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.SOFA, null, InteractiveObject.class, 
                "Hai raccolto il telecomando dal divano.",
                "Hai gettato il telecomando, d'altronde non e' questo il momento per "
                        + "guardare la televisione");


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};
        
        
        // ==================================================================================================
        objectId = ObjectId.TV;
        
        gameActionSpecifications = new HashMap();

        property = new Activatable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.ACTIVATE;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.REMOTE_CONTROL});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
        
        missingNecessaryObjectsMessages.put(ObjectId.REMOTE_CONTROL, "I telecomandi li hanno inventati parecchi anni fa...");        
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.ACTIVATABLE, 
                "La televisione e' gia' accesa!");
        
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages,
        null);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);
        
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "La televisione si e' accesa e hai una gran voglia di buttarti sul divano "
                + "e bere una birra" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);           
       
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        
        
        // ==================================================================================================    
        objectId = ObjectId.LIVINGROOM_WEST_DOOR;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        necessaryObjectsId = new ArrayList<>();
        necessaryObjectsId.add(new ObjectId[] {ObjectId.SPOON});
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                necessaryObjectsId, null, null, Door.class, 
                "Hai aperto la porta",
                        "Hai chiuso la porta, facendo un gran baccano. "
                                + "Devi aver crepato gli stipiti...");
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        
        door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications, false);
        
        room2 = this.getRoomById(RoomId.GYM);
        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        
        
        try{
            this.addDoor(door, room1, room2);
            this.addLinks(CardinalPoint.WEST, door, room1, room2);
        }catch(AlreadyLinkedException exception){}
        catch(DuplicateException exception){};        
        
        
        // ==================================================================================================    
        objectId = ObjectId.RACK;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.DUMBBELL});
        gameActionSpecifications.put(property, null);       
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        

        // ==================================================================================================
        objectId = ObjectId.DUMBBELL;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.RACK, null, InteractiveObject.class, 
                "Hai raccolto (con grande fatica) i manubri",
                "Hai lasciato cadere i manubri sul pavimento, puoi dire di "
                        + "avere fatto esercizio almeno!");

        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        

        // ==========================================================================================         
        objectId = ObjectId.MIRROR;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.HAND_RAIL});
        gameActionSpecifications.put(property, null);          
        
        property = new Breakable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.BREAK;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.WRENCH});
        inventoryConditionOptions.add(inventoryCondition);
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.DUMBBELL});
        inventoryConditionOptions.add(inventoryCondition);    
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.HAMMER});
        inventoryConditionOptions.add(inventoryCondition);  
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        failingInventoryConditionMessage = "Se desideri distruggere lo specchio a testate, chi sono io per fermarti..."
                + " ma poi chi pulisce?";
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.BREAKABLE, "Stai letteralmente"
                + " calpestando un tappeto di schegge ... una volta qui c'era uno specchio");
        
        
        failingConditionMessages = new FailingConditionMessages(null,
        failingInventoryConditionMessage, failingObjectsConditionsMessages, null);
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueResults.add(propertyValue);
                  
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);
                  
        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.OUTER_SAFE, objectEffect);

        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.OUTER_NUMERIC_KEYPAD, objectEffect);           

        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Hai mandato in mille pezzi lo specchio. Auguri, ti sei "
                + "appena guadagnato sette anni di disgrazie!" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);        

        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        "C'e' una grossa apertura nella parte centrale dello specchio, fai attenzione a non tagliarti "
                + "quando ci passi attraverso!", advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ========================================================================================== 
        objectId = ObjectId.HAND_RAIL;
        
        gameActionSpecifications = new HashMap();
        property = new Usable(false);
        gameActionSpecifications.put(property, null);
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ========================================================================================== 
        objectId = ObjectId.OUTER_SAFE;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.OUTER_NUMERIC_KEYPAD, ObjectId.INNER_SAFE, 
        ObjectId.JEWELS});
        gameActionSpecifications.put(property, null); 
        
        property = new Breakable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.BREAK;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.DRILL});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        objectsConditions.put(objectId, objectCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        objectsConditions.put(ObjectId.DRILL, objectCondition);        
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.DRILL, "Quella e' una delle casseforti piu' "
                + "robuste che tu possa mai aver visto, sembra a prova di bomba. Pero' forse c'e' qualcosa"
                + " in casa che puo' fare al caso tuo");
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.BREAKABLE, 
                "La serratura della cassaforte e' gia' completamente distrutta, forse "
                        + "potevi andarci meno pesante...");
        
        failingObjectsConditionsMessages.put(ObjectId.DRILL, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.DRILL).put(PropertyType.ACTIVATABLE, 
                "Quel trapano sarebbe piu' utile se la punta girasse su se' stessa ad "
                        + "altissima velocita', che dici?");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        null, failingObjectsConditionsMessages, null);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        propertyValue = new PropertyValue(PropertyType.BREAKABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(objectId, objectEffect);

        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.INNER_SAFE, objectEffect);

        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.INNER_NUMER_KEYPAD, objectEffect);  
        
        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(ObjectId.JEWELS, objectEffect);         
        
        gameEffect = new GameEffect(null, null,
                null, null, objectsEffects, null);
        passingConditionMessage = "Sei riuscito finalmente a forare la serratura della cassaforte! Avevi"
                + " iniziato a perdere le speranza" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);  
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        "La serratura della cassaforte ha ora un foro gigantesco e ci sono trucioli "
                + "di acciao dappertutto...", advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        // ========================================================================================== 
        objectId = ObjectId.INNER_SAFE;
        
        gameActionSpecifications = new HashMap();
        
        property = new Container(new ObjectId[] {ObjectId.INNER_NUMER_KEYPAD, ObjectId.SAFE_ROLL_OF_BILLS, 
        ObjectId.TRAPDOOR_KEY});
        gameActionSpecifications.put(property, null); 
        
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.OPEN;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        propertyWithValueConstraints = new HashSet<>();
        
        
        propertyValue = new PropertyValue(PropertyType.OPENABLE, false);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(objectId, objectCondition);
        
      
        completeCondition = new CompleteCondition(null, objectsConditions);
        

        // FailingConditionMessages

        failingObjectsConditionsMessages = new HashMap<>();

        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.OPENABLE, 
                "Hai gia' aperto la cassaforte!");
        
        
        failingConditionMessages = new FailingConditionMessages(null,
        null, failingObjectsConditionsMessages,
        null);
        
        // PassingConditionResult
        
        passingConditionMessage = "Usa il tastierino e spera di inserire la combinazione corretta in meno di "
                + "quattro secoli" ;
        
        passingConditionResult = new PassingConditionResult(null, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);               
        
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};        
        
        
        // ========================================================================================== 
        objectId = ObjectId.INNER_NUMER_KEYPAD;
        
        gameActionSpecifications = new HashMap();
        property = new Usable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.USE;
           
        
        // CompleteCondition
        objectsConditions = new HashMap<>();
        
        objectCondition = new ObjectCondition(null, true);
        
        objectsConditions.put(objectId, objectCondition);
        
        
        completeCondition = new CompleteCondition(null, objectsConditions);
        
        // FailingConditionMessages
  
        failingVisibilityConditionMessages = new HashMap<>();
                
        failingVisibilityConditionMessages.put(objectId, failingVisibilityConditionMessage);
        
        failingConditionMessages = new FailingConditionMessages(null,
        null, null,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        specialAction = () -> {
            SafeDialogGUI safeGUI = new SafeDialogGUI(null, true);
            safeGUI.setVisible(true);
            
            if (safeGUI.isPasswordGuessed()){
                throw new PasswordGuessedException();
            }
        };
        
        gameEffect = new GameEffect(null, null,
                null, null, null, specialAction);
        
        passingConditionResult = new PassingConditionResult(gameEffect, null);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);     


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};            
        
        
        // ==================================================================================================
        objectId = ObjectId.SAFE_ROLL_OF_BILLS;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.INNER_SAFE, null, ValuableObject.class, 
                "Ti sei impossessato della mazzetta di banconote e pensi di recarti subito in banca quando uscirai "
                        + "da qui, peché con tutti i furti che vengono fatti negli appartamenti, non ti senti"
                        + " sicuro se li conservi in casa tua", null);   

        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications, 30000);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(valuableObject);
        }catch(DuplicateException exception){};        
        

        // ==================================================================================================
        objectId = ObjectId.TRAPDOOR_KEY;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.INNER_SAFE, null, InteractiveObject.class, 
                "Hai raccolto la chiave e ti chiedi cosa potra' mai aprire di cosi' prezioso...",
                     "Hai gettato la chiave sul pavimento");  
  
        
        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){};            
        

        // ==================================================================================================
        objectId = ObjectId.JEWELS;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.OUTER_SAFE, null, ValuableObject.class, 
                "Hai raccolto tutti i gioelli", null);  


        //adding object to room
        
        advObject = clientManager.getObjectRequest(objectId);
        valuableObject = new ValuableObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), false, gameActionSpecifications, 15000);
        
        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(valuableObject);
        }catch(DuplicateException exception){};           
        
                
        // ==========================================================================================
           
        objectId = ObjectId.RUBBER_GLOVES;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.RUBBER_GLOVES, null,
        ObjectId.SINK, null, InteractiveObject.class, "Hai raccolto dei guanti di gomma...!",
        "Hai lasciato dei guanti di gomma in giro...");
        
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.KITCHEN);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}

        // ==========================================================================================  
        
        objectId = ObjectId.SINK;

        gameActionSpecifications = new HashMap();
        containerObjects = new ObjectId[]{ObjectId.RUBBER_GLOVES};
        gameActionSpecifications.put(new Container(containerObjects), null);
            
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        null, advObject.getAlias(), true, gameActionSpecifications);


        room1 = this.getRoomById(RoomId.KITCHEN);
        try{
            room1.addObject(interactiveObject);
        }catch(DuplicateException exception){}
            
        // ========================================================================================== 
            
            
        objectId = ObjectId.FRUIT_BOWL;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.KITCHEN);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
            
            
        // ========================================================================================== 
            
            
            objectId = ObjectId.DISHES;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.KITCHEN);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
          
            
        // ========================================================================================== 
       
                    
        objectId = ObjectId.CHAIRS;
            
        //adding object to room
        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.KITCHEN);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};
          
            
        // ========================================================================================== 
        
            objectId = ObjectId.KITCHEN_TABLE;

            gameActionSpecifications = new HashMap();
            containerObjects = new ObjectId[]{ObjectId.FRUIT_BOWL, ObjectId.DISHES};
            gameActionSpecifications.put(new Container(containerObjects), null);

            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            interactiveObject = new InteractiveObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications);


            room1 = this.getRoomById(RoomId.KITCHEN);
            try{
                room1.addObject(interactiveObject);
            }catch(DuplicateException exception){}
        
        
        // ==========================================================================================
        
        
                   
            objectId = ObjectId.BOOKCASE;

            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.LIBRARY);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
        
        
        // ==========================================================================================
        
                   
            objectId = ObjectId.LIBRARY_DOOR;

            gameActionSpecifications = new HashMap();
            property = new Openable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.LIBRARY_DOOR, null,
            null, null, InteractiveObject.class, "Hai aperto la porta della biblioteca!",
            "Hai chiuso la porta della biblioteca");
            
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.LIBRARY);
            room2 = this.getRoomById(RoomId.BEDROOM);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.SOUTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        
        
        // ==========================================================================================
        
        
                   
            objectId = ObjectId.DANCE;

            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 

            room1 = this.getRoomById(RoomId.LIBRARY);
            try{
                room1.addObject(advObject);
            }catch(DuplicateException exception){};
        

        
       // ==========================================================================================
        
        
                   
            objectId = ObjectId.LAUNDRY_DOOR;
        
            gameActionSpecifications = new HashMap();
            property = new Openable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.LAUNDRY_DOOR, null,
            null, null, InteractiveObject.class, "Hai aperto la porta della lavanderia!",
            "Hai chiuso la porta della lavanderia...");
            
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.GYM);
            room2 = this.getRoomById(RoomId.LAUNDRY);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.NORTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        
                    
       // ==========================================================================================
        
        
                   
            objectId = ObjectId.KITCHEN_DOOR;
        
            gameActionSpecifications = new HashMap();
            property = new Openable(false);
            gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());

            this.addStandardGameActionSpecifications(gameActionSpecifications, property, ObjectId.KITCHEN_DOOR, null,
            null, null, InteractiveObject.class, "Hai aperto la porta della cucina!",
            "Hai chiuso la porta della cucina...");
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            door = new Door(advObject.getId(), advObject.getName(), advObject.getDescription(),
            null, advObject.getAlias(), true, gameActionSpecifications, false);

            room1 = this.getRoomById(RoomId.LIVING_ROOM);
            room2 = this.getRoomById(RoomId.KITCHEN);
        
        
            try{
                this.addDoor(door, room1, room2);
                this.addLinks(CardinalPoint.NORTH, door, room1, room2);
            }catch(AlreadyLinkedException exception){}
            catch(DuplicateException exception){};
        
                
       // ==========================================================================================
        
        
                   
            objectId = ObjectId.MAIN_DOOR;
            
            //adding object to room
            advObject = clientManager.getObjectRequest(objectId);

            advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
            advObject.getAlias(), true); 
            
            
            room1 = this.getRoomById(RoomId.STAIRCASE);
            room2 = this.getRoomById(RoomId.LIVING_ROOM);
            
        
        
            try{
                room1.addObject(advObject);
                room2.addObject(advObject);
            }
            catch(DuplicateException exception){};
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.CAR;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};  
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.SHOPPING_LIST;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};         
        
            
        // ==========================================================================================                                  
        objectId = ObjectId.LUGGAGE_OBJECTS_LIST;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};  
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.NEWSPAPER_PAGE;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.LIVING_ROOM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};    
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.BENCH;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};          
       

        // ==========================================================================================                                  
        objectId = ObjectId.LAT_MACHINE;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};            
        
       
          // ==========================================================================================                                  
        objectId = ObjectId.SHOULDER_PRESS;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};          
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.OUTER_NUMERIC_KEYPAD;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), false); 

        room1 = this.getRoomById(RoomId.GYM);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};            
        
        
        // ==========================================================================================                                  
        objectId = ObjectId.GARAGE_DOOR;

        //adding object to room

        advObject = clientManager.getObjectRequest(objectId);

        advObject = new AdvObject(advObject.getId(), advObject.getName(), advObject.getDescription(),
        advObject.getAlias(), true); 

        room1 = this.getRoomById(RoomId.GARAGE);
        try{
            room1.addObject(advObject);
        }catch(DuplicateException exception){};            
        
        
        
    };
    
    private void addStandardGameActionSpecifications (Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications,
            Property property, ObjectId targetObjectId, List<ObjectId[]> auxiliaryObjectIds,
            ObjectId containerId, ObjectId[] containedObjectsIds, Class<?> targetObjectClass,
            String positivePassingConditionMessage, String negativePassingConditionMessage)
        throws InconsistentInitializationException {
        
        GameActionSpecification gameActionSpecification = null;
        
        if (((PropertyWithValue)property).getType() == PropertyType.PICKUPABLE){
            CommandType commandType = CommandType.PICK_UP;
            
            gameActionSpecification = this.buildStandardGameActionSpecification(property, commandType,
                targetObjectId, auxiliaryObjectIds, containerId, containedObjectsIds, targetObjectClass, positivePassingConditionMessage);
        
                gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
    
            if (targetObjectClass != ValuableObject.class){
                commandType = CommandType.DROP;
        
                gameActionSpecification = this.buildStandardGameActionSpecification(property, commandType,
                    targetObjectId,auxiliaryObjectIds, null, containedObjectsIds, targetObjectClass,
                    negativePassingConditionMessage);
        
                gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
            }
        }
        else if (((PropertyWithValue)property).getType() == PropertyType.OPENABLE){
            CommandType commandType = CommandType.OPEN;
            
            gameActionSpecification = this.buildStandardGameActionSpecification(property, commandType,
                targetObjectId, auxiliaryObjectIds, containerId, containedObjectsIds, targetObjectClass, positivePassingConditionMessage);
        
            if (gameActionSpecifications.get(property) != null)
                gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
    
            if (targetObjectClass != ValuableObject.class){
                commandType = CommandType.CLOSE;
        
                gameActionSpecification = this.buildStandardGameActionSpecification(property, commandType,
                    targetObjectId, auxiliaryObjectIds, null, containedObjectsIds, targetObjectClass, negativePassingConditionMessage);
        
                if (gameActionSpecifications.get(property) != null)
                    gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
            }            
        }
    }
    
    private GameActionSpecification buildStandardGameActionSpecification(Property property, 
            CommandType commandType, ObjectId targetObjectId, List<ObjectId[]> auxiliaryObjectIds,
            ObjectId containerId, ObjectId[] containedObjectsIds,
            Class<?> targetObjectClass, String passingConditionMessage) 
            throws IllegalArgumentException, InconsistentInitializationException{
        
        CompleteCondition completeCondition = null;
        FailingConditionMessages failingConditionMessages = null;
        PassingConditionResult passingConditionResult = null;
        GameEffect gameEffect = null;
        Map<ObjectId, ObjectEffect> objectsEffects = new HashMap<>();
        Map<ObjectId, String> missingNecessaryObjectsMessages = new HashMap<>();
        
        completeCondition = this.buildStandardCompleteCondition(commandType, targetObjectId, auxiliaryObjectIds);
        failingConditionMessages = this.buildStandardFailingConditionMessages(commandType, targetObjectId, 
                auxiliaryObjectIds, missingNecessaryObjectsMessages);
        
        
        objectsEffects = this.buildStandardObjectsEffects(commandType, targetObjectId, containerId, containedObjectsIds);
        gameEffect = this.buildStandardGameEffect(commandType, targetObjectId, targetObjectClass, objectsEffects);
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        return (new GameActionSpecification(completeCondition, failingConditionMessages, passingConditionResult));    
    }
    
    private void addStandardGameActionSpecification_Breakable(Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications,
            ObjectId targetObjectId, ObjectId[] necessaryObjectsIdsArray, String missingNecessaryObjectMessage)
        throws InconsistentInitializationException {
        
        CompleteCondition completeCondition = null;
        FailingConditionMessages failingConditionMessages = null;
        List<ObjectId[]> necessaryObjectsIds = new ArrayList<>();
        Map<ObjectId, String> missingNecessaryObjectsMessages = new HashMap<>();
        Map<ObjectId, ObjectEffect> objectsEffects = new HashMap<>();
        GameEffect gameEffect = null;
        
        necessaryObjectsIds.add(necessaryObjectsIdsArray);
        
        completeCondition = this.buildStandardCompleteCondition(CommandType.BREAK,
                targetObjectId, necessaryObjectsIds);
        
        if (necessaryObjectsIdsArray.length == 1)
            missingNecessaryObjectsMessages.put(necessaryObjectsIdsArray[0], missingNecessaryObjectMessage);
        
        failingConditionMessages = this.buildStandardFailingConditionMessages(CommandType.BREAK,
                targetObjectId, necessaryObjectsIds, missingNecessaryObjectsMessages);
        
        objectsEffects = this.buildStandardObjectsEffects(CommandType.BREAK, targetObjectId, 
               null, null);
        gameEffect = this.buildStandardGameEffect(CommandType.BREAK, targetObjectId, 
                InteractiveObject.class, objectsEffects);
    }
    
    private void addStandardGameActionSpecification_Movable(Map<Property, Map<CommandType, GameActionSpecification>> gameActionSpecifications,
            ObjectId targetObjectId, ObjectId revealedObjectId, String passingConditionMessage)
        throws InconsistentInitializationException{
        
        GameActionSpecification gameActionSpecification = null;
        CompleteCondition completeCondition = null;
        FailingConditionMessages failingConditionMessages = null;
        PassingConditionResult passingConditionResult = null;
        GameEffect gameEffect = null;
        Map<ObjectId, ObjectEffect> objectsEffects = new HashMap<>();
        Set<PropertyValue> propertyWithValueResults = new HashSet<>();
        ObjectEffect objectEffect = null;
        
        completeCondition = this.buildStandardCompleteCondition(CommandType.MOVE, targetObjectId, null);
        failingConditionMessages = this.buildStandardFailingConditionMessages(CommandType.MOVE,
                targetObjectId, null, null);
        
        propertyWithValueResults.add(new PropertyValue(PropertyType.MOVABLE, true));
        objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
        objectsEffects.put(targetObjectId, objectEffect);
        
        objectEffect = new ObjectEffect(null, null, true);
        objectsEffects.put(revealedObjectId, objectEffect);
        
        gameEffect = this.buildStandardGameEffect(CommandType.MOVE, targetObjectId,
                InteractiveObject.class, objectsEffects);
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(new Movable(false)).put(CommandType.MOVE, gameActionSpecification);        
    }
    
    private InventoryCondition buildInventoryCondition(ObjectId[] necessaryObjects){
        List<ObjectId> necessaryObjectsList = new ArrayList<>();
        
        for (int i=0; i < necessaryObjects.length ; i++)
            necessaryObjectsList.add(necessaryObjects[i]);
        
        return (new InventoryCondition(necessaryObjectsList));
    }
    
    private CompleteCondition buildStandardCompleteCondition(CommandType commandType, ObjectId targetObjectId,
            List<ObjectId[]> auxiliaryObjectIds) throws IllegalArgumentException {
        
        CompleteCondition completeCondition = null;
        Map<ObjectId, ObjectCondition> objectsConditions = new HashMap<>();
        List<InventoryCondition> inventoryConditionOptions = new ArrayList<>();
        InventoryCondition inventoryCondition = null;
        ObjectCondition objectCondition = null;
        Set<PropertyValue> propertyWithValueConstraints = new HashSet<>();
        
        if (commandType == CommandType.PICK_UP){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.PICKUPABLE, false));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
            
            completeCondition = new CompleteCondition(null, objectsConditions);
        }
        else if(commandType == CommandType.DROP){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.PICKUPABLE, true));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
            
            inventoryCondition = this.buildInventoryCondition(new ObjectId[] {targetObjectId});
            inventoryConditionOptions.add(inventoryCondition);
            completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        }
        else if(commandType == CommandType.MOVE){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.MOVABLE, false));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
            
            completeCondition = new CompleteCondition(null, objectsConditions);
        }
        else if (commandType == CommandType.BREAK){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.BREAKABLE, false));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
             
            objectCondition = new ObjectCondition(null, true);
            for (ObjectId[] necessaryObjectsIds : auxiliaryObjectIds){
                
                for (ObjectId objectId : necessaryObjectsIds)
                    objectsConditions.put(objectId, objectCondition);
                
                inventoryCondition = this.buildInventoryCondition(necessaryObjectsIds);
                inventoryConditionOptions.add(inventoryCondition);
            }
            completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        }
        else if (commandType == CommandType.OPEN){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.OPENABLE, false));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
            
            if (auxiliaryObjectIds != null){
                inventoryCondition = this.buildInventoryCondition(auxiliaryObjectIds.get(0));
                inventoryConditionOptions.add(inventoryCondition);
            }
            else{
                inventoryConditionOptions = null;
            }
                
            
            completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);              
        }
        else if (commandType == CommandType.CLOSE){
            propertyWithValueConstraints.add(new PropertyValue(PropertyType.OPENABLE, true));
            objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
            objectsConditions.put(targetObjectId, objectCondition);
            
            completeCondition = new CompleteCondition(null, objectsConditions);        
        }
        else{
            throw new IllegalArgumentException();
        }
            
        return completeCondition;
    }
    
    private FailingConditionMessages buildStandardFailingConditionMessages(CommandType commandType, 
            ObjectId targetObjectId, List<ObjectId[]> auxiliaryObjectsId, 
            Map<ObjectId, String> missingNecessaryObjectsMessages) throws IllegalArgumentException {
        
        String failingVisibilityConditionMessage = "Qui non c'e' un oggetto simile, guardati meglio intorno!";
        String failingInventoryConditionMessage = null;
        FailingConditionMessages failingConditionMessages = null;
        Map<ObjectId, String> failingVisibilityConditionMessages = new HashMap<>();
        Map<ObjectId, Map<PropertyType,String>> failingObjectsConditionsMessages = new HashMap<>();
        Map<PropertyType,String> failingPropertiesMessages = new HashMap<>();
      
        if (commandType == CommandType.PICK_UP){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.PICKUPABLE, "Hai gia' raccolto questo oggetto, "
                    + "hai proprio la memoria di un pesciolino rosso!");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            failingConditionMessages = new FailingConditionMessages(null,
                    null, failingObjectsConditionsMessages, failingVisibilityConditionMessages);
        }
        else if(commandType == CommandType.DROP){
            
            if(missingNecessaryObjectsMessages != null)
                missingNecessaryObjectsMessages.put(targetObjectId, "Non hai questo oggetto nell'inventario");
            
            failingPropertiesMessages.put(PropertyType.PICKUPABLE, "Non hai questo oggetto nell'inventario");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);

            failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
            null, failingObjectsConditionsMessages, null);
        }
        else if (commandType == CommandType.MOVE){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.MOVABLE, "Hai gia' spostato questo oggetto, "
                    + "non e' questo il momento per rimetterti in forma!");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            failingConditionMessages = new FailingConditionMessages(null,
            null, failingObjectsConditionsMessages, 
                    failingVisibilityConditionMessages);
        }
        else if (commandType == CommandType.BREAK){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            
            for (ObjectId[] necessaryObjectsIds : auxiliaryObjectsId){
                for (ObjectId objectId : necessaryObjectsIds)
                    failingVisibilityConditionMessages.put(objectId, failingVisibilityConditionMessage);
            } 
            
            failingPropertiesMessages.put(PropertyType.BREAKABLE, "Questo oggetto e' gia' rotto");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            if (auxiliaryObjectsId.size() > 1)
                failingInventoryConditionMessage = "Intendi rompere questo oggetto a mani nude o preferisci"
                        + " tornare a casa con le ossa delle mani ancora integre? ";
            
            failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
            failingInventoryConditionMessage, failingObjectsConditionsMessages, 
                    failingVisibilityConditionMessages);
        }
        else if (commandType == CommandType.OPEN){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.OPENABLE, "L'oggetto e' gia' aperto!");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            if (auxiliaryObjectsId != null){
                for (ObjectId auxiliaryObjectId : auxiliaryObjectsId.get(0))
                    missingNecessaryObjectsMessages.put(auxiliaryObjectId, "Non puoi aprire questo oggetto, "
                            + "forse hai bisogno di qualcosa... ");
            }
            else{
                missingNecessaryObjectsMessages = null;
            }
            
            failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
            null, failingObjectsConditionsMessages, 
                    failingVisibilityConditionMessages);            
        }
        else if (commandType == CommandType.CLOSE){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.OPENABLE, "L'oggetto e' gia' chiuso, forse ti "
                    + "servirebbe piu' aprirlo non pensi?");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            failingConditionMessages = new FailingConditionMessages(null,
            null, failingObjectsConditionsMessages, 
                    failingVisibilityConditionMessages);            
        }
        else{
            throw new IllegalArgumentException();
        }
            
        return failingConditionMessages;
    }

    private GameEffect buildStandardGameEffect(CommandType commandType, ObjectId targetObjectId, 
            Class<?> targetObjectClass, Map<ObjectId, ObjectEffect> objectsEffects) 
            throws IllegalArgumentException, InconsistentInitializationException{
    
        GameEffect gameEffect = null;
        CurrentPositionEffect currentPositionEffect = null;
        InventoryEffect inventoryEffect = null;
        LootBagEffect lootBagEffect = null;
        RoomEffect roomEffect = null;
 
        
        if (commandType == CommandType.PICK_UP){
            
            if (targetObjectClass != ValuableObject.class){
                inventoryEffect = new InventoryEffect(targetObjectId, null);
            }
            else if (targetObjectClass == ValuableObject.class){
                lootBagEffect = new LootBagEffect(targetObjectId);
            }
            
            roomEffect = new RoomEffect(null, targetObjectId);
            
            gameEffect = new GameEffect(null, inventoryEffect, 
                    lootBagEffect, roomEffect, objectsEffects, null);
        }
        else if (commandType == CommandType.DROP){
            inventoryEffect = new InventoryEffect(null, targetObjectId);
            roomEffect = new RoomEffect(targetObjectId, null);
            gameEffect = new GameEffect(null, inventoryEffect, 
                    null, roomEffect, objectsEffects, null);
        }
        else if (commandType == CommandType.MOVE){
            gameEffect = new GameEffect(null, null, null,
                null, objectsEffects, null);
        }
        else if (commandType == CommandType.BREAK){
            gameEffect = new GameEffect(null, null, null,
                null, objectsEffects, null);
        }
        else if (commandType == CommandType.OPEN){
            gameEffect = new GameEffect(null, null, null,
                null, objectsEffects, null);
        }
        else if (commandType == CommandType.CLOSE){
            gameEffect = new GameEffect(null, null, null,
                null, objectsEffects, null);
        }
        else{
            throw new IllegalArgumentException();
        }
        
        return gameEffect;
    }
    
    private Map<ObjectId, ObjectEffect> buildStandardObjectsEffects(CommandType commandType,
            ObjectId targetObjectId, ObjectId containerId, ObjectId[] containedObjectsIds) 
            throws IllegalArgumentException {
        
        Map<ObjectId, ObjectEffect> objectsEffects = new HashMap<>();
        ObjectEffect objectEffect = null;
        Set<PropertyValue> propertyWithValueResults = new HashSet<>();
        
        
        if (commandType == CommandType.PICK_UP){
            // Target object effect
            propertyWithValueResults.add(new PropertyValue(PropertyType.PICKUPABLE, true));
            objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
            objectsEffects.put(targetObjectId, objectEffect);

            if (containerId != null){
                ContainerEffect containerEffect = new ContainerEffect(targetObjectId);
                objectEffect = new ObjectEffect(null, containerEffect, true);
                objectsEffects.put(containerId, objectEffect);
            }
        }
        else if (commandType == CommandType.DROP){
            propertyWithValueResults.add(new PropertyValue(PropertyType.PICKUPABLE, false));
            objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
            objectsEffects.put(targetObjectId, objectEffect);            
        }
        else if (commandType == CommandType.BREAK){
            propertyWithValueResults.add(new PropertyValue(PropertyType.BREAKABLE, true));
            objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
            objectsEffects.put(targetObjectId, objectEffect);               
        }
        else if (commandType == CommandType.OPEN){
            propertyWithValueResults.add(new PropertyValue(PropertyType.OPENABLE, true));
            objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
            objectsEffects.put(targetObjectId, objectEffect); 
            
            if (containedObjectsIds != null){
                for (ObjectId containedObjectId : containedObjectsIds){
                    objectEffect = new ObjectEffect(null, null, true);
                    objectsEffects.put(containedObjectId, objectEffect); 
                }
            }
        }
        else if (commandType == CommandType.CLOSE){
            propertyWithValueResults.add(new PropertyValue(PropertyType.OPENABLE, false));
            objectEffect = new ObjectEffect(propertyWithValueResults, null, true);
            objectsEffects.put(targetObjectId, objectEffect); 
            
            if (containedObjectsIds != null){
                for (ObjectId containedObjectId : containedObjectsIds){
                    objectEffect = new ObjectEffect(null, null, false);
                    objectsEffects.put(containedObjectId, objectEffect); 
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }

        return objectsEffects;
    }
    
    private void initCommands(){
        
        addCommand(CommandType.NORTH, "nord", new String[]{"n"});
	addCommand(CommandType.SOUTH, "sud", new String[]{"s"});
	addCommand(CommandType.EAST, "est", new String[]{"e"});
	addCommand(CommandType.WEST, "ovest", new String[]{"o"});
	addCommand(CommandType.LOOT_BAG, "bottino", new String[]{"sacco", "zaino", "refurtiva", "borsa"});
	addCommand(CommandType.INVENTORY, "inventario", new String[]{"inv"});
	addCommand(CommandType.END, "esci", new String[]{"end", "fine","finire", "terminare", "termina", "exit", "basta"});
	addCommand(CommandType.SAVE, "salva", new String[]{" salvataggio", "salvare"});
	addCommand(CommandType.MENU, "menu", new String[]{});
	addCommand(CommandType.OPEN, "apri", new String[]{"spalancare", "spalanca", "aprire"});
	addCommand(CommandType.CLOSE, "chiudi", new String[]{"chiudere"});
	addCommand(CommandType.PUSH, "premi", new String[]{"premere", "spingi", "spingere", "pressare", "pressa","schiacciare", "schiaccia"});
	addCommand(CommandType.BREAK, "rompi", new String[]{"rompere", "distruggere", "sfasciare", "disintegrare" , "rompere", 	"distruggi", "sfascia", "disintegra","disintegrare", "sfonda","sfondare", "danneggia", "danneggiare"});
	addCommand(CommandType.PICK_UP, "raccogli", new String[]{"raccogliere", "prendere", "afferrare", "prendi", "afferra"});
        addCommand(CommandType.DROP, "lascia", new String[]{});
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
            
            Room room = getRoomById(RoomId.SIDEWALK);
            room.addRoomLink(CardinalPoint.NORTH, getRoomById(RoomId.MIDDLE_GARDEN));
            
            room = getRoomById(RoomId.MIDDLE_GARDEN);
            room.addRoomLink(CardinalPoint.SOUTH, getRoomById(RoomId.SIDEWALK));
            
            room = getRoomById(RoomId.MIDDLE_GARDEN);
            room.addRoomLink(CardinalPoint.NORTH, getRoomById(RoomId.STAIRCASE));
            
            room = getRoomById(RoomId.STAIRCASE);
            room.addRoomLink(CardinalPoint.SOUTH, getRoomById(RoomId.MIDDLE_GARDEN));
            
            room = getRoomById(RoomId.MIDDLE_GARDEN);
            room.addRoomLink(CardinalPoint.EAST, getRoomById(RoomId.EAST_GARDEN));
            
            room = getRoomById(RoomId.EAST_GARDEN);
            room.addRoomLink(CardinalPoint.WEST, getRoomById(RoomId.MIDDLE_GARDEN));
            
            room = getRoomById(RoomId.MIDDLE_GARDEN);
            room.addRoomLink(CardinalPoint.WEST, getRoomById(RoomId.WEST_GARDEN));
            
            room = getRoomById(RoomId.WEST_GARDEN);
            room.addRoomLink(CardinalPoint.EAST, getRoomById(RoomId.MIDDLE_GARDEN));
            
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
    
    private void addLinks(CardinalPoint cardinalPoint, Door door, Room room1, Room room2) throws AlreadyLinkedException, DuplicateException{
        if (room1.reaches(room2) || room2.reaches(room1)){
		throw new AlreadyLinkedException();
	}

	CardinalPoint oppositeCardinalPoint = cardinalPoint.getOpposite();
	
	room1.addRoomLink(cardinalPoint, room2);
	room1.addDoorLink(cardinalPoint, door);
	room2.addRoomLink(oppositeCardinalPoint, room1);
	room2.addDoorLink(oppositeCardinalPoint, door);
    }
    
    
    private void addDoor(Door door, Room room1, Room room2) throws DuplicateException{
        if (room1.contains(door) || room2.contains(door))
	{
            throw new DuplicateException();
	}
	else{
		room1.addObject(door);
		room2.addObject(door);
	}
    }
    
}
