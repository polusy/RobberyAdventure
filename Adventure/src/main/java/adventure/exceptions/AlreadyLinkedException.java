/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando si tenta di associare un oggetto ad un altro, il quale però
 * partecipa già ad un' associazione con un altro oggetto
 *
 * @author Alessandro
 */
public class AlreadyLinkedException extends Exception {
    final private String message = "Rooms are already linked";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }
}
