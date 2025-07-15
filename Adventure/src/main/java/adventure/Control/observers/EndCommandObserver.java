/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;

import adventure.Boundary.GUI.FrameCreator;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.ParserOutput;
import adventure.exceptions.NotValidSentenceException;
import adventure.exceptions.EndGameException;
import adventure.Boundary.GUI.EndCommandGUI;

import javax.swing.JFrame;



/**
 *
 * @author utente
 */
public class EndCommandObserver implements TechnicalObserver {

    /**
     *
     * @param game
     * @param parserOutput
     * @param message
     * @throws NotValidSentenceException
     * @throws EndGameException
     */
    public void update (GameDescription game, ParserOutput parserOutput, StringBuilder message) throws NotValidSentenceException, EndGameException {
        
        if (!parserOutput.getObjects().isEmpty() || parserOutput.getDoorRoom() != null)
            throw new NotValidSentenceException();
        
        JFrame baseFrame = FrameCreator.createBaseFrame();
        
        EndCommandGUI endCommandGUI = new EndCommandGUI(baseFrame, true);
        endCommandGUI.setVisible(true);
        
        
       if (endCommandGUI.isGameEnded())
           throw new EndGameException();
        
    }   
}
