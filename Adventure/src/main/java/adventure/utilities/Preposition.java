/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;

/** Preposizione riconoscibile da un oggetto {@link Parser}
 *
 * @author Paolo
 */
public class Preposition {
    
    private String preposition;
    
    /**
     *
     * @param preposition Parola che rappresenta l'effettiva preposizione inserita dall'utente nella stringa di input
     */
    public Preposition(String preposition){
        this.preposition = preposition;
    }

    /**
     *
     * @return
     */
    public String getPreposition() {
        return preposition;
    }

    /**
     *
     * @param preposition
     */
    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }
    
    
}
