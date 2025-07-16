/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando il giocatore inserisce la password corretta per la cassaforte interna
 * ({@code ObjectId} : {@code INNER_SAFE})
 *
 * @author Alessandro
 */
public class PasswordGuessedException extends Exception {
    final private String message = "Password guessed";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }       
}
