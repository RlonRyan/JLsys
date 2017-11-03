/*
 *
 */
package com.github.rlonryan.jlsys;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * A class representing an immutable L-System.
 *
 * @author Ryan
 */
final public class Lsys {

    /**
     * The axiom of this L-System, otherwise known as the starting string.
     */
    private final String axiom;

    /**
     * The alphabet of the L-System, which is the set of symbols that are considered valid in the
     * context of this L-System.
     *
     * Note, the alphabet may not contain the null character ({@literal '\0'}), as it is reserved
     * for use by the LsysIterator class as the step character.
     */
    private final Set<Character> alphabet;

    /**
     * The production rules of the L-System, in this case a map of symbols to their respective
     * expansions.
     */
    private final Map<Character, String> productions;
    
    /**
     * Meta-data describing the L-System.
     */
    private final Map<String, String> properties;

    /**
     * Creates an L-System with the given axiom and expansion set.
     *
     * @param axiom The axiom for the L-System.
     * @param alphabet The L-System's alphabet.
     * @param productions The L-System's production rules (expansions).
     */
    public Lsys(String axiom, Set<Character> alphabet, Map<Character, String> productions, Map<String, String> properties) {
        // Validate.
        LsysUtil.checkAlphabet(alphabet);
        LsysUtil.checkAxiom(axiom, alphabet);
        LsysUtil.checkProductions(productions, alphabet);
        // Set values.
        this.axiom = axiom;
        this.alphabet = alphabet;
        this.productions = Objects.requireNonNull(productions);
        this.properties = Objects.requireNonNull(properties);
    }

    /**
     * Determines if the L-System has an expansion for the given symbol.
     *
     * @param c The symbol to find an expansion for.
     * @return {@literal true} if and only if the L-System has an expansion for the given symbol
     * {@code c}, {@literal false} otherwise.
     */
    public boolean hasProduction(char c) {
        return this.productions.containsKey(c);
    }

    /**
     * Determines if the L-System has the given symbol in its alphabet.
     *
     * @param c The symbol to check for in the alphabet.
     * @return {@literal true} if and only if the symbol {@code c} is contained in the L-System's
     * alphabet, {@literal false} otherwise.
     */
    public boolean hasSymbol(char c) {
        return this.alphabet.contains(c);
    }

    /**
     * Retrieves the L-System's axiom.
     *
     * @return The L-System's axiom.
     */
    public String getAxiom() {
        return this.axiom;
    }

    /**
     * Retrieves the L-System's alphabet.
     *
     * @return The alphabet of the L-System.
     */
    public Set<Character> getAlphabet() {
        return Collections.unmodifiableSet(this.alphabet);
    }

    /**
     * Fetches the expansion for the given symbol.
     *
     * @param c The symbol to find an expansion for.
     * @return The symbol's expansion, or {@literal null} if the L-System does not have an expansion
     * for the given symbol.
     */
    public String getProduction(char c) {
        return this.productions.get(c);
    }

    /**
     * Gets the expansions that make up the L-System.
     *
     * @return An unmodifiable view of the Map storing the L-System's expansions.
     */
    public Map<Character, String> getProductions() {
        return Collections.unmodifiableMap(productions);
    }
    
    /**
     * Gets a specific property of the L-System.
     * 
     * @param key The property to search for.
     * @return An optional containing the property value, or the empty optional.
     */
    public Optional<String> getProperty(String key) {
        return Optional.ofNullable(this.properties.get(key));
    }
    
    /**
     * Gets the properties of the L-System.
     * 
     * @return An unmodifiable view of the Map storing the L-System's properties.
     */
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    /**
     * Creates an iterator over the L-System, that expands the system to the given depth.
     *
     * @param depth The depth to expand the L-System to.
     * @return An iterator over the L-System with the given depth.
     */
    public Iterator<Character> iterator(int depth) {
        return new LsysIterator(this, depth);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lsys) {
            final Lsys other = (Lsys) obj;
            return this.axiom.equals(other.axiom)
                    && this.alphabet.equals(other.alphabet)
                    && this.productions.equals(other.productions)
                    && this.properties.equals(other.properties);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.axiom);
        hash = 31 * hash + Objects.hashCode(this.alphabet);
        hash = 31 * hash + Objects.hashCode(this.productions);
        hash = 31 * hash + Objects.hashCode(this.properties);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<Lsys> {\n");
        sb.append("\tAxiom: '").append(this.axiom).append("'\n");
        sb.append("\tAlphabet: ").append(this.alphabet).append("\n");
        sb.append("\tProductions:\n");
        this.productions.forEach((symbol, expansion) -> {
            sb.append("\t\t'").append(symbol).append("' -> '").append(expansion).append("'\n");
        });
        sb.append("\tProperties:\n");
        this.properties.forEach((key, value) -> {
            sb.append("\t\t'").append(key).append("': '").append(value).append("'\n");
        });
        sb.append("}");
        return sb.toString();
    }

}
