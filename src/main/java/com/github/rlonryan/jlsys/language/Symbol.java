/*
 * 
 */
package com.github.rlonryan.jlsys.language;

/**
 * An interface representing a symbol.
 * 
 * A set of symbols that make up a language is called and alphabet.
 *
 * @author Ryan
 */
public interface Symbol {
    
    Symbol KLEENE_STAR = new SymbolKleeneStar();
    
    boolean matches(byte[] bytes);
    
    public static final class SymbolKleeneStar implements Symbol {

        private SymbolKleeneStar() {
        }

        @Override
        public boolean matches(byte[] bytes) {
            return false;
        }
        
    }
    
}
