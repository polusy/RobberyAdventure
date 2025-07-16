/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando il gioco deve terminare, sia per la terminazione ordinaria dell'applicazione,
 * che per casi speciali legati alla partita, come la fine dell'avventura
 *
 * @author Alessandro
 */
public class EndGameException extends Exception {
    final private String message = "End game";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }    
}
