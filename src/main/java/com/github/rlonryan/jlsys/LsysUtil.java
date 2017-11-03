/*
 * 
 */
package com.github.rlonryan.jlsys;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A set of static utility functions for working with L-Systems.
 *
 * @author Ryan
 */
public final class LsysUtil {

    /**
     * A private constructor to prevent instantiation of the static utility class.
     */
    private LsysUtil() {
        // Nothing to do here.
    }
    
    public static Set<Character> checkAlphabet(Set<Character> alphabet) {
        // Ensure alphabet is not null.
        Preconditions.checkNotNull(alphabet, "Invalid alphabet: null! Alphabet may not be null!");
        // Ensure alphabet does not contain the null character.
        Preconditions.checkArgument(!alphabet.contains('\0'), "Invalid alphabet: %d! Alphabet contains forbidden symbol: '\\0'!", alphabet);
        // Return the validated alphabet.
        return alphabet;
    }

    public static String checkAxiom(String axiom) {
        // Ensure axiom is not null.
        Preconditions.checkNotNull(axiom, "Invalid axiom: null! Axiom may not be null!");
        // Ensure axiom does not contain the null character.
        final int loc = axiom.indexOf(LsysIterator.STEP_SYMBOL);
        Preconditions.checkArgument(loc == -1, "Invalid axiom: '%s'! Axiom contains forbidden symbol: '\\0' at position: %d!", loc);
        // Return the validated axiom.
        return axiom;
    }
    
    public static String checkAxiom(String axiom, Set<Character> alphabet) {
        // Run alphabet-free axiom checks.
        checkAxiom(axiom);
        // Ensure all symbols in axiom are valid.
        for (int i = 0; i < axiom.length(); i++) {
            final char c = axiom.charAt(i);
            Preconditions.checkArgument(alphabet.contains(c), "Invalid axiom: '%s'! Axiom contains symbol: '%s' which is not in alphabet: %s!", axiom, c, alphabet);
        }
        // Return the validated axiom.
        return axiom;
    }

    public static char checkSymbol(char symbol) {
        // Ensure symbol is not null character.
        Preconditions.checkArgument(symbol != LsysIterator.STEP_SYMBOL, "Invalid symbol: '\\0'! Symbol is forbidden character: '\\0'!");
        // Return validated symbol.
        return symbol;
    }
    
    public static char checkSymbol(char symbol, Set<Character> alphabet) {
        // Run alphabet-free checks.
        checkSymbol(symbol);
        // Ensure symbol is in alphabet.
        Preconditions.checkArgument(alphabet.contains(symbol), "Invalid symbol: '%s'! Symbol is not in alphabet: %s!", symbol, alphabet);
        // Return validated symbol.
        return symbol;
    }
    
    public static void checkProduction(char symbol, String production, Set<Character> alphabet) {
        // Ensure not a null production rule.
        Preconditions.checkNotNull(production, "Invalid production rule: '%s' -> null! The expansion string may not be null!", symbol);
        // Ensure symbol is in alphabet.
        Preconditions.checkArgument(alphabet.contains(symbol), "Invalid production rule: '%s' -> '%s'! Target symbol: '%s' is not in alphabet: %s!", symbol, production, symbol, alphabet);
        // Ensure all symbols in the expansion are valid.
        for (int i = 0; i < production.length(); i++) {
            final char c = production.charAt(i);
            Preconditions.checkArgument(alphabet.contains(c), "Invalid production rule: '%s' -> '%s'! Expansion contains symbol: '%s' which is not in alphabet: %s!", symbol, production, c, alphabet);
        }
    }
    
    public static void checkProductions(Map<Character, String> productions, Set<Character> alphabet) {
        // Ensure not null production set.
        Preconditions.checkNotNull(productions, "Invalid production rule set: null! The production rule set may not be null!");
        // Check that all productions are valid.
        productions.forEach((symbol, expansion) -> {
            checkProduction(symbol, expansion, alphabet);
        });
    }

}
