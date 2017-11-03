/*
 * 
 */
package com.github.rlonryan.jlsys;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * An iterator for an L-System.
 *
 * @author Ryan
 */
final class LsysIterator implements Iterator<Character> {
    
    /**
     * The symbol representing the stack pop operation.
     */
    public static final Character STEP_SYMBOL = '\0';
    
    /**
     * The L-System that this iterator iterates over.
     */
    private final Lsys lsys;
    
    /**
     * The maximum depth that this iterator expands the given L-System to.
     */
    private final int max_depth;
    
    /**
     * The stack used by this L-System iterator as to expand the L-System.
     */
    private final Deque<Character> stack;
    
    /**
     * The current depth of the L-System iterator.
     */
    private int depth;

    public LsysIterator(Lsys system, int depth) {
        // Validate.
        Preconditions.checkNotNull(system, "The L-System that an L-System Iterator iterates over may not be null!");
        Preconditions.checkArgument(depth >= 0, "An L-System iterator may not have a negative max depth!");
        // Set values.
        this.lsys = system;
        this.max_depth = depth;
        this.stack = new ArrayDeque<>(256);
        this.depth = 0;
        // Put axiom on the stack.
        final String axiom = this.lsys.getAxiom();
        for (int i = axiom.length() - 1; i >= 0; i--) {
            this.stack.push(axiom.charAt(i));
        }
    }

    @Override
    public boolean hasNext() {
        return !this.stack.isEmpty() ;
    }

    @Override
    public Character next() {
        // Ensure that we have a next symbol.
        if (this.stack.isEmpty()) {
            throw new IndexOutOfBoundsException("Attempted to get next symbol from Lsys, but was already at the end!");
        }
        // Take the first symbol from the stack.
        char symbol = this.stack.pop();
        // While are not at the max depth then we need to expand the symbol, if it is expandable.
        while (this.depth < this.max_depth && this.lsys.hasProduction(symbol)) {
            // Fetch the symbol's expansion.
            String expansion = this.lsys.getProduction(symbol);
            // Push the step symbol to the stack and increment depth counter.
            this.stack.push(STEP_SYMBOL);
            this.depth = this.depth + 1;
            // Push the expansion symbols to the stack.
            for (int i = expansion.length() - 1; i >= 0; i--) {
                // Take the next symbol from the expansion and push it to the stack.
                this.stack.push(expansion.charAt(i));
            }
            // Take the first symbol off the stack, since just expanded.
            symbol = this.stack.pop();
        }
        // We have the next symbol to return, but we need to trim any trailing step characters. *
        Character peek = this.stack.peek();
        // In case step symbol, do step and get new symbol from stack.
        while (STEP_SYMBOL.equals(peek)) {
            // Pop the symbol off the stack;
            this.stack.pop();
            // Step up the depth counter.
            this.depth = this.depth - 1;
            // Move on to the next symbol in the stack.
            peek = this.stack.peek();
        }
        // Return the next symbol in the expanded L-System.
        return symbol;
    }
    
}
