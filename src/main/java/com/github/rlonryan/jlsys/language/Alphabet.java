/*
 * 
 */
package com.github.rlonryan.jlsys.language;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing an alphabet.
 *
 * An alphabet is the set of all symbols that are used in a language.
 *
 * An alphabet has the additional property of defining a total ordering over it's symbols.
 *
 * This specific implementation is only capable of representing finite alphabets of cardinality less
 * than or equal to {@link Integer#MAX_VALUE}. For any realistic alphabet such will be fine.
 *
 * @author Ryan
 */
public final class Alphabet {

    private final Letter[] letters;
    private final Map<Symbol, Letter> symbols;
    
    public Alphabet(Symbol... letters) {
        this(Arrays.asList(letters));
    }

    public Alphabet(Collection<Symbol> letters) {
        // Require the symbol collection to be non-null.
        Objects.requireNonNull(letters);
        // Create the holding set and encoding mapping array.
        this.letters = new Letter[letters.size()];
        this.symbols = new HashMap<>();
        // Letter id counter.
        final Iterator<Symbol> iter = letters.iterator();
        // Loop through the symbol set and add them to the collections.
        for (int id = 0; iter.hasNext(); id++) {
            // Get the next letter.
            final Symbol symbol = iter.next();
            // Check that the symbol is not null.
            Objects.requireNonNull(symbol, "An alphabet may not contain a null symbol!");
            // Check that the symbol is not a duplicate.
            if (this.symbols.containsKey(symbol)) {
                throw new Error("Duplicate symbol: " + symbol + "!");
            }
            // Create the new letter.
            final Letter letter = new Letter(id, symbol, this);
            // Register
            this.letters[id] = letter;
            this.symbols.put(symbol, letter);
        }
    }
    
    public boolean hasLetter(Letter letter) {
        return (letter != null) && (this.equals(letter.alphabet));
    }
    
    public boolean hasLetter(Symbol symbol) {
        return this.symbols.containsKey(symbol);
    }
    
    public boolean hasLetter(int encoding) {
        return encoding >= 0 && encoding < this.letters.length;
    }
    
    public Optional<Letter> getLetter(int encoding) {
        // Return symbol if in bounds.
        if (this.hasLetter(encoding)) {
            return Optional.of(this.letters[encoding]);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<Letter> getLetter(Symbol symbol) {
        return Optional
                .ofNullable(symbol)
                .map(this.symbols::get);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Alphabet) {
            final Alphabet other = (Alphabet) obj;
            return Arrays.deepEquals(this.letters, other.letters);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Arrays.deepHashCode(this.letters);
        return hash;
    }

    @Override
    public String toString() {
        return "Alphabet { letters: " + Arrays.toString(letters) + " }";
    }

}
