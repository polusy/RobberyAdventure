/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.identifiers;

/** Questo enumerativo contiene gli identificativi delle tipologie di comando disponibili nel gioco
 *
 * @author Paolo
 */
public enum CommandType {
    
    /** Comando inseribile dall'utente per uscire dal gioco
     *
     */
    END,

    /** Comando inseribile dall'utente per salvare la partita
     *
     */
    SAVE,

    /** Comando inseribile dall'utente per accedere al menù
     *
     */
    MENU,

    /** Comando inseribile dall'utente per visualizzare l'inventario
     *
     */
    INVENTORY,

    /** Comando inseribile dall'utente per visualizzare lo zaino contenente gli oggetti rubati
     *
     */
    LOOT_BAG,

    /** Comando inseribile dall'utente per spostarsi verso nord nella mappa
     *
     */
    NORTH,

    /** Comando inseribile dall'utente per spostarsi verso sud nella mappa
     *
     */
    SOUTH,

    /** Comando inseribile dall'utente per spostarsi verso est nella mappa
     *
     */
    EAST,

    /** Comando inseribile dall'utente per spostarsi verso ovest nella mappa
     *
     */
    WEST,

    /** Comando inseribile dall'utente per aprire un oggetto del gioco
     *
     */
    OPEN,

    /** Comando inseribile dall'utente per chiudere un oggetto del gioco
     *
     */
    CLOSE,

    /** Comando inseribile dall'utente per premere un oggetto del gioco
     *
     */
    PUSH,

    /** Comando inseribile dall'utente per rompere un oggetto del gioco
     *
     */
    BREAK,

    /** Comando inseribile dall'utente per raccogliere un oggetto del gioco, inserendolo nell'inventario o nello zaino
     * della refurtiva se l'oggetto è {@link ValuableObject}
     *
     */
    PICK_UP,

    /** Comando inseribile dall'utente per gettare un oggetto del gioco nella stanza corrente, rimuovendolo dall'inventario
     *
     */
    DROP,

    /** Comando inseribile dall'utente per utilizzare un oggetto del gioco
     *
     */
    USE,

    /** Comando inseribile dall'utente per osservare la stanza corrente o  un oggetto del gioco
     *
     */
    LOOK_AT,

    /** Comando inseribile dall'utente per consumare un oggetto del gioco
     *
     */
    CONSUME,

    /** Comando inseribile dall'utente per attivare un oggetto del gioco
     *
     */
    ACTIVATE,

    /** Comando inseribile dall'utente per disattivare un oggetto del gioco
     *
     */
    DEACTIVATE,

    /** Comando inseribile dall'utente per spostare un oggetto del gioco nella stanza
     *
     */
    MOVE,

    /** Comando inseribile dall'utente per riempire un oggetto del gioco
     *
     */
    FILL
}
