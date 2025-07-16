/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando si tenta di assegnare ad un oggetto un identificativo già assegnato ad un altro
 * oggetto
 *
 * @author Alessandro
 */
public class IdAlreadyTakenException extends Exception {
    final private String message = "Id is already taken";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }    
}
