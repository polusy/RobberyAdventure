/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando un comando risulta ambiguo
 *
 * @author Alessandro
 */
public class AmbiguousCommandException extends Exception {
    final private String message = "Non ho capito cosa vuoi dire, il comando è ambiguo";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }    
}
