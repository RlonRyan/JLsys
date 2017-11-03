/*
 * 
 */
package com.github.rlonryan.jlsys.language;

import java.util.Objects;

/**
 * A letter is a symbol that is part of an alphabet.
 *
 * A letter is considered distinct from a {@link Symbol} in that a letter is a element of a
 * {@link Alphabet}, whereas a symbol is not. That is to say a letter is a symbol that has been
 * associated to an alphabet.
 *
 * A letter is considered comparable, unlike a symbol, but only within the context of its alphabet.
 *
 * @author Ryan
 */
public final class Letter implements Comparable<Letter> {

    final int id;
    final Symbol symbol;
    final Alphabet alphabet;

    Letter(int id, Symbol symbol, Alphabet alphabet) {
        // Ensure id is valid.
        if (id < 0) {
            throw new IllegalArgumentException("The letter id: " + id + " is less than zero, which is invalid!");
        }
        // Set values.
        this.id = id;
        this.symbol = Objects.requireNonNull(symbol, "A letter's symbol may not be null!");
        this.alphabet = Objects.requireNonNull(alphabet, "A letter's alphabet may not be null!");
    }

    public int getId() {
        return id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    @Override
    public boolean equals(Object obj) {
        // Check that instance of letter.
        if (obj instanceof Letter) {
            // Cast.
            final Letter other = (Letter) obj;
            // Test equality.
            return (this.alphabet.equals(other.alphabet))
                    && (this.id == other.id)
                    && (this.symbol.equals(other.symbol));
        }
        // Otherwise, nope.
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.symbol);
        hash = 37 * hash + Objects.hashCode(this.alphabet);
        return hash;
    }

    @Override
    public String toString() {
        return "<Letter> { id: " + this.id + ", symbol: " + this.symbol + " }";
    }

    @Override
    public int compareTo(Letter other) {
        // If other is null, this is greater.
        if (other == null) {
            return 1;
        }
        // If not in same alphabet, compare alphabet hashcodes.
        if (!this.alphabet.equals(other.alphabet)) {
            return Integer.compare(this.alphabet.hashCode(), other.alphabet.hashCode());
        }
        // Otherwise compare index.
        return Integer.compare(this.id, other.id);
    }

}
