/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.analyzers;


import adventure.Entity.types.CommandAnalysisResult;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.identifiers.PropertyType;
import adventure.identifiers.PrepositionType;
import adventure.Entity.objects.InteractiveObject;
import adventure.Entity.objects.AdvObject;
import adventure.exceptions.*;
import adventure.exceptions.*;

/**
 *
 * @author Paolo
 */
public abstract class CommandAnalyzer {

    /**
     *
     */
    protected String errorMessage;
    
    /**
     *
     * @param errorMessage
     */
    public CommandAnalyzer(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    /**
     *
     * @param gameDescription
     * @param parserOutput
     * @return
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    public abstract CommandAnalysisResult analyze(GameDescription gameDescription, ParserOutput parserOutput)
            throws NotValidSentenceException, AmbiguousCommandException;
    
    /**
     *
     * @param parserOutput
     * @param propertyType
     * @param errorMessage
     * @return
     * @throws NotValidSentenceException
     */
    protected CommandAnalysisResult analyzeUnaryCommand(ParserOutput parserOutput, PropertyType propertyType, String errorMessage) throws NotValidSentenceException{
        
        InteractiveObject analyzedObject = null;
        String message = null;
        boolean analysisPassed = true;
        
        
        if (!parserOutput.getObjects().isEmpty() && parserOutput.getObjects().size() == 1)
	{
            for (AdvObject object : parserOutput.getObjects().keySet())
            {
                if (object instanceof InteractiveObject)
                {
                        InteractiveObject iteratedObject = (InteractiveObject) object;
                        if (!iteratedObject.hasProperty(propertyType))
                        {
                            message = errorMessage;
                            analysisPassed = false;
                        }
                        else if (parserOutput.hasPreposition())
                        {
                            throw new NotValidSentenceException();
                        }
                        else
                            analyzedObject = iteratedObject;
                }
                else{
                    message = errorMessage;
                    analysisPassed = false;
                }
            }
	}
	else{
            throw new NotValidSentenceException();
	}

        return new CommandAnalysisResult(message, analyzedObject, null, analysisPassed, propertyType);
    }
    
    /**
     *
     * @param parserOutput
     * @param propertyType
     * @param prepositionType
     * @param errorMessage
     * @return
     * @throws NotValidSentenceException
     * @throws AmbiguousCommandException
     */
    protected CommandAnalysisResult analyzeBinaryCommand(ParserOutput parserOutput, PropertyType propertyType, PrepositionType prepositionType, String errorMessage) throws NotValidSentenceException, AmbiguousCommandException{
        String message = null;
	InteractiveObject targetObject = null;
	InteractiveObject auxiliaryObject = null;
	boolean analysisPassed = true;

        if (parserOutput.getObjects().size() == 1)
        {
            return analyzeUnaryCommand(parserOutput, propertyType, errorMessage);
        }
        else
        {
                if (parserOutput.hasPreposition())
                {

                        AdvObject advObject = parserOutput.getUniqueObjectWithoutPreposition();
                        if (advObject instanceof InteractiveObject)
                        {
                                targetObject = (InteractiveObject) advObject;

                                if (!targetObject.hasProperty(propertyType))
                                {
                                    message = errorMessage;
                                    analysisPassed = false;
                                }
                                else if (parserOutput.getObjectWithPreposition() instanceof InteractiveObject)
                                {
                                        auxiliaryObject = (InteractiveObject) parserOutput.getObjectWithPreposition();
                                        if (!prepositionType.getWords().contains(parserOutput.getExpression(auxiliaryObject).getValue().getPreposition()))
                                        {
                                            throw new NotValidSentenceException();
                                        }

                                }
                                else{
                                    throw new NotValidSentenceException();
                                }
                        }
                        else
                        {
                            throw new NotValidSentenceException();
                        }
                }
                else{
                        for (AdvObject object : parserOutput.getObjects().keySet())
                        {
                                if (object instanceof InteractiveObject)
                                {
                                        InteractiveObject iteratedObject = (InteractiveObject) object;
                                        if (iteratedObject.hasProperty(propertyType))
                                        {
                                                if (targetObject == null)
                                                    targetObject = iteratedObject;
                                                else
                                                    throw new AmbiguousCommandException();
                                        }
                                        else{
                                                if (auxiliaryObject == null)
                                                    auxiliaryObject = iteratedObject;
                                                else{
                                                        message = errorMessage;
                                                        analysisPassed = false;
                                                }
                                        }
                                }
                        }

                }			

        }
    
    
        return new CommandAnalysisResult(message, targetObject, auxiliaryObject, analysisPassed, propertyType);
    }
}

