/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.exceptions;

/**
 *
 * @author utente
 */
public class AmbiguousCommandException extends Exception {
    final private String message = "Non ho capito cosa vuoi dire, il comando è ambiguo";
    
    /**
     *
     * @return
     */
    public String getMessage() { return message; }    
}
