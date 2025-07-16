/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando si tenta di aggiungere un elemento già presente in una collezione
 * o in una sequenza di elementi
 *
 * @author Alessandro
 */
public class DuplicateException extends Exception {
    final private String message = "Duplicate element";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; } 
}
