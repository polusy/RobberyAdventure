/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary.GUI;
import javax.swing.JFrame;

/**
 *
 * 
 * 
 * La classe si occupa di creare un frame di base
 * per garantire la visibilità di dialog durante 
 * l'esecuzione del gioco.
 * 
 * @author Paolo
 */
public class FrameCreator {
    
    /**
     *
     * Il metodo crea un jframe di base che farà da owner
     * delle dialog create durante il gameplay.
     * 
     * @return il Jframe di base
     * 
     * 
     */
    public static JFrame createBaseFrame(){
        JFrame dummyOwner = new JFrame();
        dummyOwner.setUndecorated(true);  
        dummyOwner.setSize(0, 0);          
        dummyOwner.setLocationRelativeTo(null);
        dummyOwner.setVisible(true);
        
        
        return dummyOwner;
        
    }
    
}
