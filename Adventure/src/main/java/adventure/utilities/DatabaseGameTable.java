/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;


import adventure.Entity.types.RobberyAdventure;


/** Tupla di una tabella che associa un oggetto {@link RobberyAdventure} ad un identificativo
 *
 * @author Paolo
 */
public class DatabaseGameTable {
    
    private RobberyAdventure robberyAdventure;
    private String id;

    /**
     *
     * @param robbery Partita del gioco RobberyAdventure
     * @param id Identificativo da associare alla partita
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
