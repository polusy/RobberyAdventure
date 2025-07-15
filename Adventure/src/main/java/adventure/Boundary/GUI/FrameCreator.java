/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary.GUI;

import javax.swing.JFrame;

/**
 *
 * @author Paolo
 */
public class FrameCreator {
    
    public static JFrame CreateBaseFrame(){
        JFrame owner = new JFrame();
        owner.setUndecorated(true);  
        owner.setSize(0, 0);          
        owner.setLocationRelativeTo(null);
        owner.setVisible(true);
        
        return owner;
    }
    
}
