/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;


import adventure.Entity.types.GameDescription;


/**
 *
 * @author Paolo
 */
public class DatabaseGameTable {
    
    private GameDescription gameDescription;
    private String id;

    public DatabaseGameTable(GameDescription gameDescription, String id) {
        this.gameDescription = gameDescription;
        this.id = id;
    }

    public DatabaseGameTable() {
    }
    
    

    public GameDescription getGameDescription() {
        return gameDescription;
    }

    public String getId() {
        return id;
    }

    public void setGameDescription(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
    
    
}
