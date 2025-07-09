/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/**
 *
 * @author utente
 */
public class NotValidTokenException extends Exception {
    private String message = "Token is not valid";
    
    public String getMessage() { return message; }    
}
