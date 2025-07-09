/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/**
 *
 * @author utente
 */
public class InconsistentInitializationException extends Exception {
    private String message = "Inconsistent initialization attempt";
    
    public String getMessage() { return message; }
}
