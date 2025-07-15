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

    /**
     *
     * @param robbery
     * @param id
     */
    public DatabaseGameTable(RobberyAdventure robbery, String id) {
        this.robberyAdventure = robbery;
        this.id = id;
    }

    /**
     *
     */
    public DatabaseGameTable() {
    }

    /**
     *
     * @return
     */
    public RobberyAdventure getRobberyAdventure() {
        return robberyAdventure;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param robberyAdventure
     */
    public void setRobberyAdventure(RobberyAdventure robberyAdventure) {
        this.robberyAdventure = robberyAdventure;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    


    
    
    
    
    
    
}
