/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/**
 *
 * @author utente
 */
public class NotValidSentenceException extends Exception {
    final private String message = "Comando non valido! Dai un'occhiata al manuale";
    
    public String getMessage() { return message; }    
}
