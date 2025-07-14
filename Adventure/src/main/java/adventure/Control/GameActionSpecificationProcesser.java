/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control;


import adventure.Entity.objects.AdvObject;
import adventure.Entity.objects.InteractiveObject;

import adventure.identifiers.ObjectId;
import adventure.identifiers.PropertyType;
import adventure.identifiers.CommandType;

import adventure.Entity.types.GameDescription;
import adventure.Entity.types.RobberyAdventure;
import adventure.Entity.types.GameActionSpecification;
import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.ConditionEvaluationResult;
import adventure.Entity.types.GameActionResult;
import adventure.Entity.types.PassingConditionResult;
import adventure.Entity.effects.GameEffect;
import adventure.Entity.effects.ObjectEffect;
import adventure.Entity.effects.SpecialAction;

import adventure.Entity.conditions.CompleteCondition;
import adventure.Entity.conditions.InventoryCondition;
import adventure.Entity.conditions.ObjectCondition;
import adventure.Entity.properties.Property;
import adventure.Entity.properties.Container;
import adventure.Entity.types.PropertyValue;
import adventure.Entity.properties.PropertyWithValue;
import adventure.Entity.types.ObjectPropertyReference;

import adventure.exceptions.*;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author Paolo
 */
public abstract class GameActionSpecificationProcesser {
    
    
    public static GameActionResult process(GameActionSpecification gameActionSpecification,GameDescription gameDescription, CommandAnalysisResult commandAnalysisResult) throws NotValidSentenceException,DuplicateException
    {
	ConditionEvaluationResult conditionEvaluationResult = processCompleteCondition(gameActionSpecification.getCompleteCondition(), gameDescription, commandAnalysisResult);

	if (conditionEvaluationResult.hasConditionPassed()){
            GameActionResult gameActionResult = processConditionPassingResult(gameActionSpecification.getPassingConditionResult(), gameDescription);
            return gameActionResult;
	}
	else{
            String failingMessage = gameActionSpecification.getFailingConditionMessages().getMessage(conditionEvaluationResult);
            return (new GameActionResult(failingMessage));
	}
    }
    
    private static ConditionEvaluationResult processCompleteCondition(CompleteCondition completeCondition ,GameDescription gameDescription, CommandAnalysisResult commandAnalysisResult) throws NotValidSentenceException
    {
	ConditionEvaluationResult conditionEvaluationResult = new ConditionEvaluationResult();

	processInventoryCondition(conditionEvaluationResult, completeCondition.getInventoryConditionOptions(), gameDescription, commandAnalysisResult.getAuxiliaryObject());

	if (conditionEvaluationResult.hasConditionPassed())
	{
            processObjectsConditions(conditionEvaluationResult, completeCondition.getObjectsConditions(), gameDescription);
	}
	
	return conditionEvaluationResult;
    }
    
    
    
    private static void processInventoryCondition(ConditionEvaluationResult conditionEvaluationResult, List<InventoryCondition> inventoryConditionOptions,
            GameDescription gameDescription, InteractiveObject auxiliaryObject) throws NotValidSentenceException{
        
        List<AdvObject> inventoryObjects = gameDescription.getInventory().getObjects();
	ObjectId missingObjectId = null;
	boolean inventoryConditionOptionFound = false;
	boolean inventoryConditionPassed = false;
	List<ObjectId> inventoryObjectsId = inventoryObjects.stream()
                                            .map(object -> object.getId())
                                            .collect(Collectors.toList());
												
        if(inventoryConditionOptions != null)
        {

            for (InventoryCondition inventoryConditionOption : inventoryConditionOptions)
            {

                if (inventoryObjectsId.containsAll(inventoryConditionOption.getNecessaryObjects()))
                {
                    inventoryConditionOptionFound = true;
                    if (auxiliaryObject == null || inventoryConditionOption.contains(auxiliaryObject.getId()))
                    {
                        inventoryConditionPassed = true;
                    }
                }
            }
	
            if (!inventoryConditionPassed)
            {
                if (inventoryConditionOptionFound)
                        throw new NotValidSentenceException();

                conditionEvaluationResult.setInventoryConditionPassed(false);

                if (inventoryConditionOptions.size() == 1)
                {
                    InventoryCondition inventoryConditionOption = inventoryConditionOptions.get(0);
                    for (ObjectId objectId : inventoryConditionOption.getNecessaryObjects())
                    {
                        if (!inventoryObjectsId.contains(objectId) && missingObjectId == null)
                        {
                            missingObjectId = objectId;
                        }
                    }
                }
            }
        }
        else{
                inventoryConditionPassed = true;
        }

        if (!inventoryConditionPassed)
                conditionEvaluationResult.setConditionPassed(false);

        conditionEvaluationResult.setInventoryConditionPassed(inventoryConditionPassed);
        conditionEvaluationResult.setMissingNecessaryObject(missingObjectId);
    }
    
    
    
    private static void processObjectsConditions(ConditionEvaluationResult conditionEvaluationResult, Map<ObjectId, ObjectCondition> objectsConditions, GameDescription gameDescription){
        
        ObjectId failedConditionObjectId = null;
	ObjectId failedVisibilityObjectId = null;
	PropertyType failedPropertyType = null;
        ObjectPropertyReference objectFailedConditionReference = null;
	boolean conditionPassed = true;
		
	if(objectsConditions != null)
	{
            for (Map.Entry<ObjectId, ObjectCondition> objectConditions : objectsConditions.entrySet())
            {
                    ObjectId currentIterationObjectId = objectConditions.getKey();
                    AdvObject object = gameDescription.getObjectById(currentIterationObjectId);
                    if (object.isVisible() == objectConditions.getValue().getVisibility())
                    {
                        if (object instanceof InteractiveObject)
                        {
                            InteractiveObject interactiveObject = (InteractiveObject) object;
                            for (Property property :  interactiveObject.getProperties())
                            {
                                if (failedPropertyType == null)
                                {

                                    if (property instanceof PropertyWithValue)
                                    {   
                                        PropertyWithValue propertyWithValue = (PropertyWithValue) property;
                                        PropertyType propertyType = propertyWithValue.getType();

                                        try{
                                            PropertyValue conditionPropertyValue = objectConditions.getValue().getPropertyValue(propertyType);

                                            if (!(propertyWithValue.getValue() == conditionPropertyValue.getValue()))
                                            {
                                                failedPropertyType = propertyType;
                                                failedConditionObjectId = interactiveObject.getId();
                                                conditionPassed  = false;
                                            }

                                        }catch(NoSuchElementException exception){};
                                    }
                                }
                            }
                        }
                    }
		else{
			failedVisibilityObjectId = object.getId();
			conditionPassed  = false;
		}
            }
		
		if (failedConditionObjectId != null)
                    objectFailedConditionReference = new ObjectPropertyReference(failedConditionObjectId, failedPropertyType);

		conditionEvaluationResult.setObjectFailedConditionReference(objectFailedConditionReference);
		conditionEvaluationResult.setFailedVisibilityObject(failedVisibilityObjectId);
		conditionEvaluationResult.setConditionPassed(conditionPassed);
        }
		
        
    }
    
    
    private static GameActionResult processConditionPassingResult(PassingConditionResult passingConditionResult, GameDescription gameDescription) throws DuplicateException{
        
        String passingConditionMessage = passingConditionResult.getPassingConditionMessage();
	GameEffect gameEffect = passingConditionResult.getGameEffect();
	SpecialAction specialAction = null;
        AdvObject removedObject = null;
       
        PassingConditionResult auxiliaryPassingConditionResult = null;
        Container container = null;

	if (gameEffect != null)
	{
            specialAction = gameEffect.getSpecialAction();
        }   


	if (gameEffect.getCurrentPositionEffect() != null)
	{
            gameDescription.setCurrentRoom(gameDescription.getRoomById(gameEffect.getCurrentPositionEffect().getNewCurrentRoom()));
	}

	if (gameEffect.getInventoryEffect() != null)
	{
		if (gameEffect.getInventoryEffect().getAddingObject() != null)
			gameDescription.addObjectToinventory(gameDescription.getObjectById(gameEffect.getInventoryEffect().getAddingObject()));

		if (gameEffect.getInventoryEffect().getRemovingObject() != null){
                        removedObject = gameDescription.getObjectById(gameEffect.getInventoryEffect().getRemovingObject());
			gameDescription.removeObjectFromInventory(gameDescription.getObjectById(gameEffect.getInventoryEffect().getRemovingObject()));
                        
                }
        }

	
	if (gameEffect.getLootBagEffect() != null)
	{
            if (gameEffect.getLootBagEffect().getAddingObject() != null)
            {
                if (gameDescription instanceof RobberyAdventure)
                {
                    RobberyAdventure robberyAdventure = (RobberyAdventure) gameDescription;
                    removedObject = gameDescription.getObjectById(gameEffect.getLootBagEffect().getAddingObject());
                    robberyAdventure.addObjectToLootBag(robberyAdventure.getObjectById(gameEffect.getLootBagEffect().getAddingObject()));
                }
            }
        }

	if (gameEffect.getRoomEffect() != null)
	{
            if (gameEffect.getRoomEffect().getAddingObject() != null)
                    gameDescription.addObjectToRoom(gameDescription.getCurrentRoom(), removedObject);

            if (gameEffect.getRoomEffect().getRemovingObject() != null)
                    gameDescription.removeObjectFromRoom(gameDescription.getCurrentRoom(), gameDescription.getObjectById(gameEffect.getRoomEffect().getRemovingObject()));
	 }


	if (gameEffect.getObjectsEffects() != null)
	{
		for (Map.Entry<ObjectId, ObjectEffect> objectEffects : gameEffect.getObjectsEffects().entrySet())
		{
			InteractiveObject object = (InteractiveObject) gameDescription.getObjectById(objectEffects.getKey());
			ObjectEffect effect = objectEffects.getValue();

			if (((Boolean)effect.getVisibility()) != null)
				object.setVisibility(effect.getVisibility());

			if (effect.getContainerEffect() != null)
			{
                            if (object.isContainer())
                                    container = object.getContainerProperty();
                                    if (container.hasObject(effect.getContainerEffect().getRemovingObject()))
                                    {
                                        container.removeObject(effect.getContainerEffect().getRemovingObject());
                                        
                                        if (object.hasProperty(PropertyType.OPENABLE)){
                                            auxiliaryPassingConditionResult = object.getGameActionSpecification(PropertyType.OPENABLE, CommandType.OPEN).getPassingConditionResult();
                                            auxiliaryPassingConditionResult.getGameEffect().getObjectsEffects().remove(effect.getContainerEffect().getRemovingObject());

                                            auxiliaryPassingConditionResult = object.getGameActionSpecification(PropertyType.OPENABLE, CommandType.CLOSE).getPassingConditionResult();
                                            auxiliaryPassingConditionResult.getGameEffect().getObjectsEffects().remove(effect.getContainerEffect().getRemovingObject());
                                    }
                                    }
			}

			if (effect.getPropertyWithValueResults() != null)
			{
				for (PropertyValue propertyValue : effect.getPropertyWithValueResults())
				{
					try{
                                            object.setValueToProperty(propertyValue.getPropertyType(), propertyValue.getValue());
					}catch(NoSuchElementException exception){};
				}
			}
		}
	}

	return new GameActionResult(passingConditionMessage, specialAction);
    }
 
    public static void safeOpeningHandler(RobberyAdventure game){
        InteractiveObject innerSafe = (InteractiveObject) game.getObjectById(ObjectId.INNER_SAFE);
        
        if (innerSafe.getPropertyByType(PropertyType.OPENABLE).getValue() == false){
            
            innerSafe.getPropertyByType(PropertyType.OPENABLE).setValue(true);
            game.getObjectById(ObjectId.SAFE_ROLL_OF_BILLS).setVisibility(true);
            game.getObjectById(ObjectId.TRAPDOOR_KEY).setVisibility(true);
        }
    }
    
    
}
    
    
