/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/**
 *
 * @author utente
 */
public class AlreadyLinkedException extends Exception {
    final private String message = "Rooms are already linked";
    
    /**
     *
     * @return
     */
    public String getMessage() { return message; }
}
