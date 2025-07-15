/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Control.observers;
import javax.swing.JFrame;

/**
 *
 * @author Paolo
 */
public class FrameCreator {
    
    public static JFrame createBaseFrame(){
        JFrame dummyOwner = new JFrame();
        dummyOwner.setUndecorated(true);  
        dummyOwner.setSize(0, 0);          
        dummyOwner.setLocationRelativeTo(null);
        dummyOwner.setVisible(true);
        
        
        return dummyOwner;
        
    }
    
}
