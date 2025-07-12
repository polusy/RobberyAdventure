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


import adventure.utilities.Utils;
import adventure.exceptions.*;
import adventure.Entity.types.PropertyValue;


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
    public void init() throws InconsistentInitializationException{
        
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
        final String failingVisibilityConditionMessage = "Qui non c'è un oggetto simile, guardati meglio intorno!" ;
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
              

        
        initCommands();
        initRooms();

        // ==================================================================================================
        objectId = ObjectId.SLING;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, null, null, InteractiveObject.class, "Hai raccolto la fionda. "
                + "Assomiglia a quella che avevi da piccolo, che il vicino ti ha sequestrato dopo che gli hai "
                + "mandato in frantumi la finestra.", "Hai gettato la fionda");
        
        
        // ==================================================================================================
        objectId = ObjectId.VASE;
        
        gameActionSpecifications = new HashMap();
        property = new Movable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        commandType = CommandType.MOVE;
        
        
        // CompleteCondition
        objectsConditions = new HashMap<>();
        
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
                "Hai già spostato questo oggetto, non è questo il momento per rimetterti in forma!");
        
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

        
       
        // ==================================================================================================    
        objectId = ObjectId.GARAGE_KEY;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, null, null, InteractiveObject.class, "Hai raccolto la chiave. "
                , "Hai lasciato la chiave");    
        
        
        // =================================================================================================
        objectId = ObjectId.SECURITY_CAMERA;
        
        gameActionSpecifications = new HashMap();
        property = new Breakable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.BREAK;
          
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {ObjectId.SLING});
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
                
        missingNecessaryObjectsMessages.put(ObjectId.SLING, "Non sapevo che potessi sparare"
                + " raggi laser dagli occhi, wow! Forse hai bisogno di qualcosa per romperla...");
        
        failingObjectsConditionsMessages.put(objectId, new HashMap<>());
        failingObjectsConditionsMessages.get(objectId).put(PropertyType.BREAKABLE, "Hai già rotto"
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
 
        
        // ==================================================================================================    
        objectId = ObjectId.GARAGE_DOOR;
        
        gameActionSpecifications = new HashMap();
        property = new Openable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        List<ObjectId[]> necessaryObjectsId = new ArrayList<>();
        necessaryObjectsId.add(new ObjectId[] {ObjectId.GARAGE_KEY});
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                necessaryObjectsId, null, null, InteractiveObject.class, 
                "Hai aperto la serranda del garage! "
                        + "Speriamo che il proprietario non ci tenga dentro le solite cianfrusaglie. ", 
                "Hai chiuso la serranda del garage."); 
        
        
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
                "Hai chiuso l'armadio del garage. C'erano più acari lì dentro che nel "
                        + "vecchio tappeto di tua nonna.");  
        
        // ==================================================================================================
        objectId = ObjectId.HAMMER;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai preso il martello. Sei cosi' maldestro che ci e' mancato poco che ti cadesse su un piede",
                "Hai buttato il martello, di' la verità che non ce la facevi piu' a "
                        + "portarlo con te in giro");

        
        // ==================================================================================================
        objectId = ObjectId.ELECTRIC_SAW;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai preso la sega elettrica, fai attenzione a non amputarti un dito!",
                "Hai gettato la sega elettrica");

        
        // ==================================================================================================
        objectId = ObjectId.WRENCH;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai raccolto la chiave inglese, probabilmente è l'unico strumento che "
                        + "riconosceresti in una ferramenta.!",
                "Hai buttato la chiave inglese");        
        

        // ==================================================================================================
        objectId = ObjectId.POWER_UNIT;
        
        gameActionSpecifications = new HashMap();
        property = new Pickupable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        this.addStandardGameActionSpecifications(gameActionSpecifications, property, objectId, 
                null, ObjectId.TOOL_CABINET, null, InteractiveObject.class, 
                "Hai raccolto la ciave inglese, probabilmente è l'unico strumento che "
                        + "riconosceresti in una ferramenta.!",
                "Hai buttato la chiave inglese"); 

        
        // ========================================================================================== 
        //                              Template
        
        objectId = ObjectId.VASE;
        
        gameActionSpecifications = new HashMap();
        property = new Movable(false);
        gameActionSpecifications.put(property, new HashMap<CommandType, GameActionSpecification>());
        
        
        commandType = CommandType.MOVE;
           
        
        // CompleteCondition
        inventoryConditionOptions = new ArrayList<>();
        objectsConditions = new HashMap<>();
        
        
        inventoryCondition = this.buildInventoryCondition(new ObjectId[] {});
        inventoryConditionOptions.add(inventoryCondition);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueConstraints.add(propertyValue);
        
        objectCondition = new ObjectCondition(propertyWithValueConstraints, true);
        
        objectsConditions.put(ObjectId.BANDAGES, objectCondition);
        
        
        completeCondition = new CompleteCondition(inventoryConditionOptions, objectsConditions);
        
        // FailingConditionMessages
        
        missingNecessaryObjectsMessages = new HashMap<>();
        failingObjectsConditionsMessages = new HashMap<>();
        failingVisibilityConditionMessages = new HashMap<>();
                
        missingNecessaryObjectsMessages.put(ObjectId.BANDAGES, "");
        failingInventoryConditionMessage = "";
        
        failingObjectsConditionsMessages.put(ObjectId.BANDAGES, new HashMap<>());
        failingObjectsConditionsMessages.get(ObjectId.BANDAGES).put(PropertyType.ACTIVATABLE, "");
        
        failingVisibilityConditionMessages.put(ObjectId.BANDAGES, "");
        
        failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
        failingInventoryConditionMessage, failingObjectsConditionsMessages,
        failingVisibilityConditionMessages);
        
        
        // PassingConditionResult
        
        propertyWithValueResults = new HashSet<>();
        objectsEffects = new HashMap<>();
        
        currentPositionEffect = new CurrentPositionEffect(RoomId.ANTE_VAULT);
        inventoryEffect = new InventoryEffect(ObjectId.BANDAGES, ObjectId.BATTERY_CHARGER);
        lootBagEffect = new LootBagEffect(ObjectId.BANDAGES);
        roomEffect = new RoomEffect(ObjectId.BANDAGES, ObjectId.BATTERY_CHARGER);
        
        propertyValue = new PropertyValue(PropertyType.ACTIVATABLE, true);
        propertyWithValueResults.add(propertyValue);
                
        containerEffect = new ContainerEffect(ObjectId.BANDAGES);
                
        objectEffect = new ObjectEffect(propertyWithValueResults, containerEffect, true);
        objectsEffects.put(ObjectId.BANDAGES, objectEffect);
        
        specialAction = null;
        
        gameEffect = new GameEffect(currentPositionEffect, inventoryEffect,
                lootBagEffect, roomEffect, objectsEffects, specialAction);
        passingConditionMessage = "" ;
        
        passingConditionResult = new PassingConditionResult(gameEffect, passingConditionMessage);
        
        
        // GameActionSpecification
        
        gameActionSpecification = new GameActionSpecification(completeCondition, 
                failingConditionMessages, passingConditionResult);
        
        gameActionSpecifications.get(property).put(commandType, gameActionSpecification);        
        
        
        
        
        
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
        
            gameActionSpecifications.get(property).put(commandType, gameActionSpecification);
    
            if (targetObjectClass != ValuableObject.class){
                commandType = CommandType.CLOSE;
        
                gameActionSpecification = this.buildStandardGameActionSpecification(property, commandType,
                    targetObjectId, auxiliaryObjectIds, null, containedObjectsIds, targetObjectClass, negativePassingConditionMessage);
        
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
        Map<ObjectId, ObjectEffect> objectsEffects = null;
        
        completeCondition = this.buildStandardCompleteCondition(commandType, targetObjectId, auxiliaryObjectIds);
        failingConditionMessages = this.buildStandardFailingConditionMessages(commandType, targetObjectId, 
                auxiliaryObjectIds, null);
        
        
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
        Map<ObjectId, ObjectEffect> objectsEffects = null;
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

            inventoryCondition = this.buildInventoryCondition(auxiliaryObjectIds.get(0));
            inventoryConditionOptions.add(inventoryCondition);
            
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
        
        String failingVisibilityConditionMessage = "Qui non c'è un oggetto simile, guardati meglio intorno!";
        String failingInventoryConditionMessage = null;
        FailingConditionMessages failingConditionMessages = null;
        Map<ObjectId, String> failingVisibilityConditionMessages = new HashMap<>();
        Map<ObjectId, Map<PropertyType,String>> failingObjectsConditionsMessages = new HashMap<>();
        Map<PropertyType,String> failingPropertiesMessages = new HashMap<>();
      
        if (commandType == CommandType.PICK_UP){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.PICKUPABLE, "Hai già raccolto questo oggetto, "
                    + "hai proprio la memoria di un pesciolino rosso!");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            failingConditionMessages = new FailingConditionMessages(null,
                    null, failingObjectsConditionsMessages, failingVisibilityConditionMessages);
        }
        else if(commandType == CommandType.DROP){
            missingNecessaryObjectsMessages.put(targetObjectId, "Non hai questo oggetto nell'inventario");
            failingPropertiesMessages.put(PropertyType.PICKUPABLE, "Non hai questo oggetto nell'inventario");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
            null, failingObjectsConditionsMessages, null);
        }
        else if (commandType == CommandType.MOVE){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.MOVABLE, "Hai già spostato questo oggetto, "
                    + "non è questo il momento per rimetterti in forma!");
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
            
            failingPropertiesMessages.put(PropertyType.BREAKABLE, "Questo oggetto è già rotto");
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
            failingPropertiesMessages.put(PropertyType.OPENABLE, "L'oggetto è già aperto!");
            failingObjectsConditionsMessages.put(targetObjectId, failingPropertiesMessages);
            
            for (ObjectId auxiliaryObjectId : auxiliaryObjectsId.get(0))
                missingNecessaryObjectsMessages.put(auxiliaryObjectId, "Non puoi aprire questo oggetto, "
                        + "forse hai bisogno di qualcosa... ");
            
            failingConditionMessages = new FailingConditionMessages(missingNecessaryObjectsMessages,
            null, failingObjectsConditionsMessages, 
                    failingVisibilityConditionMessages);            
        }
        else if (commandType == CommandType.CLOSE){
            failingVisibilityConditionMessages.put(targetObjectId, failingVisibilityConditionMessage);
            failingPropertiesMessages.put(PropertyType.OPENABLE, "L'oggetto e' già chiuso, forse ti "
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
