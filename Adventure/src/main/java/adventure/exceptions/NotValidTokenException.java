/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando un token proveniente da una stringa di input
 * non rientra nel lessico supportato dal gioco
 *
 * @author Alessandro
 */
public class NotValidTokenException extends Exception {
    final private String message = "Token is not valid";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }    
}
