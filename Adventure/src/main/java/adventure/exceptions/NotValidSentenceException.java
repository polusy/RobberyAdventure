/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/** Questa eccezione viene lanciata quando la stringa inserita dall'utente non risulta corretta sul piano lessicale o 
 * sul piano sintattico o sul piano semantico di una specifica tipologia di comando
 *
 * @author Alessandro
 */
public class NotValidSentenceException extends Exception {
    final private String message = "Comando non valido! Non puoi fare questa cosa";
    
    /** Restituisce il messaggio di errore legato all'eccezione
     *
     * @return Messaggio di errore
     */
    public String getMessage() { return message; }    
}
