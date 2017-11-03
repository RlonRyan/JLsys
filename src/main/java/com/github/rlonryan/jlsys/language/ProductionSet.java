/*
 * 
 */
package com.github.rlonryan.jlsys.language;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class representing a set of productions over a given alphabet.
 *
 * @author Ryan
 */
public class ProductionSet {
    
    final Alphabet alphabet;
    final HashMap<Letter, List<Letter>> productions;

    public ProductionSet(Alphabet alphabet, HashMap<Letter, List<Letter>> productions) {
        this.alphabet = alphabet;
        this.productions = productions;
        
        // Validate productions.
        productions.forEach((k, v) -> {
            // Ensure not null.
            Objects.requireNonNull(k, "Production has null target!");
            Objects.requireNonNull(v, "Production has null expansion!");
            // Test the target.
            if (!this.alphabet.hasLetter(k)) {
                throw new IllegalArgumentException("Production contains letter that is not in alphabet!");
            }
            // Test the expansion.
            v.forEach((e) -> {
                if (!this.alphabet.hasLetter(e)) {
                    throw new IllegalArgumentException("Production expansion contains letter that is not in alphabet!");
                }
            });
        });
    }
    
    public Stream<Letter> expand(Letter letter) {
        return productions.getOrDefault(letter, Collections.EMPTY_LIST).stream();
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public HashMap<Letter, List<Letter>> getProductions() {
        return productions;
    }

}
