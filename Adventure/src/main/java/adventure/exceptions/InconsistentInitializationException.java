/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando si tenta di costruire un oggetto portandolo in uno stato inconsistente rispetto
 * alla semantica della classe
 *
 * @author Alessandro
 */
public class InconsistentInitializationException extends Exception {
    final private String message = "Inconsistent initialization attempt";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }
}
