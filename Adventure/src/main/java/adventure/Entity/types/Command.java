/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Entity.types;

import adventure.identifiers.CommandType;
import java.util.Objects;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 *
 * La classe modella un qualsiasi comando valido dell'avventura.
 * 
 * @author Paolo
 */
public class Command {
    
    /**
     * il tipo di comando {@link CommandType}
     */
    private final CommandType type;
    
    /**
     * Il nome associato ad esso {@code name}.
     */
    private final String name;
    
    /**
     *gli alias del nome originale {@code alias}.
     */
    private final Set<String> alias;

    /**
     *
     * @param type
     * @param name
     * @param alias
     */
    public Command(CommandType type, String name, String[] alias) {
        this.type = type;
        this.name = name;
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     *
     * @return
     */
    public CommandType getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     *
     * @return Hash calcolato sul tipo di comando.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.type);
        return hash;
    }

    /**
     *
     * @param obj
     * @return {@code true} se i due comandi sono uguali secondo il {@link CommandType}, {@code false} altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        return this.type == other.type;
    }
    
    
    
    
}
