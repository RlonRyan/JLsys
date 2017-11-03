/*
 * 
 */
package com.github.rlonryan.jlsys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Lsys builder class.
 *
 * @author Ryan
 */
public final class LsysBuilder {

    private String axiom;
    private final Set<Character> alphabet;
    private final Map<Character, String> productions;
    private final Map<String, String> properties;

    public LsysBuilder() {
        // Set the values.
        this.axiom = "";
        this.alphabet = new HashSet<>();
        this.productions = new HashMap();
        this.properties = new HashMap<>();
    }
    
    /**
     * Sets the L-System's axiom to the given string.
     * 
     * @param axiom The string to use as the axiom.
     * @return The builder instance.
     */
    public LsysBuilder withAxiom(String axiom) {
        // Ensure axiom is valid.
        LsysUtil.checkAxiom(axiom, this.alphabet);
        // Set the axiom.
        this.axiom = axiom;
        // Return builder instance.
        return this;
    }

    /**
     * Adds the given symbol to the L-System's alphabet.
     *
     * @param symbol The symbol to add to the alphabet, if it is not already present.
     * @return The builder instance.
     */
    public LsysBuilder withSymbol(char symbol) {
        // Ensure symbol is not the null character.
        LsysUtil.checkSymbol(symbol);
        // Add the given symbol to the alphabet.
        this.alphabet.add(symbol);
        // Return the builder.
        return this;
    }
    
    /**
     * Adds the given symbols to the L-System's alphabet.
     *
     * @param symbols The symbols to add to the alphabet, if not already present.
     * @return The builder instance.
     */
    public LsysBuilder withSymbols(char... symbols) {
        // Loop through symbol set.
        for (char symbol : symbols) {
            // Add the symbol.
            this.withSymbol(symbol);
        }
        // Return the builder.
        return this;
    }
    
    /**
     * Adds the given symbols to the L-System's alphabet.
     *
     * @param symbols The symbols to add to the alphabet, if not already present.
     * @return The builder instance.
     */
    public LsysBuilder withSymbols(String symbols) {
        // Loop through the symbol set.
        for (int i = 0; i < symbols.length(); i++) {
            // Add the symbol.
            this.withSymbol(symbols.charAt(i));
        }
        // Return the builder.
        return this;
    }

    /**
     * Adds the given production rule to the L-System.
     *
     * @param symbol The target symbol of the production rule.
     * @param production The expansion string of the production rule.
     * @return The builder instance.
     */
    public LsysBuilder withProduction(char symbol, String production) {
        // Ensure valid production rule.
        LsysUtil.checkProduction(symbol, production, this.alphabet);
        // Add the production rule.
        this.productions.put(symbol, production);
        // Return the builder.
        return this;
    }
    
    public LsysBuilder withProperty(String key, String value) {
        // Ensure key is not null.
        Objects.requireNonNull(key);
        // If the value is null, remove the property from the map, otherwise set the value in the map.
        if (value == null) {
            this.properties.remove(key);
        } else {
            this.properties.put(key, value);
        }
        // Return the builder.
        return this;
    }

    /**
     * Constructs an L-System from the state of this builder instance.
     * 
     * @return A new L-System, constructed from the state of this builder instance.
     */
    public Lsys build() {
        return new Lsys(this.axiom, this.alphabet, this.productions, this.properties);
    }

}
