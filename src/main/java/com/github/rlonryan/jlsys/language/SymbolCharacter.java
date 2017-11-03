/*
 * 
 */
package com.github.rlonryan.jlsys.language;

/**
 * Class representing a basic symbol that is a character.
 *
 * @author Ryan
 */
public class SymbolCharacter implements Symbol {
    
    public static final String FORMAT_CHARACTER_SYMBOL = "<CharacterSymbol> { character: '%c' }";
    
    final char character;

    public SymbolCharacter(char character) {
        this.character = character;
    }

    @Override
    public boolean matches(byte[] bytes) {
        return (bytes.length == 1) && (bytes[0] == this.character);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SymbolCharacter) && (this.character == ((SymbolCharacter) obj).character);
    }

    @Override
    public int hashCode() {
        return this.character;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_CHARACTER_SYMBOL, this.character);
    }
    
}
