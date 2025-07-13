/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;


import adventure.Entity.types.RobberyAdventure;


/**
 *
 * @author Paolo
 */
public class DatabaseGameTable {
    
    private RobberyAdventure robberyAdventure;
    private String id;

    public DatabaseGameTable(RobberyAdventure robbery, String id) {
        this.robberyAdventure = robbery;
        this.id = id;
    }

    public DatabaseGameTable() {
    }

    public RobberyAdventure getRobberyAdventure() {
        return robberyAdventure;
    }

    public String getId() {
        return id;
    }

    public void setRobberyAdventure(RobberyAdventure robberyAdventure) {
        this.robberyAdventure = robberyAdventure;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    


    
    
    
    
    
    
}
