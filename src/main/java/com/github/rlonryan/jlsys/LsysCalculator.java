/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 *
 * @author Ryan
 */
public class LsysCalculator {
    
    private final Lsys lsys;
    private final Map<Character, Integer> occurances;
    private final AtomicBoolean finished;
    private final AtomicBoolean active;
    private final int depth;
    private int expandedSize;

    public LsysCalculator(Lsys lsys, int depth) {
        this.lsys = lsys;
        this.depth = depth;
        this.occurances = new HashMap<>();
        this.finished = new AtomicBoolean(false);
        this.active = new AtomicBoolean(false);
        this.expandedSize = 0;
        
        // Setup the occurance map.
        for (Character c : this.lsys.getAlphabet()) {
            this.occurances.put(c, 0);
        }
    }
    
    public final boolean isFinished() {
        return finished.get();
    }
    
    public final boolean isActive() {
        return active.get();
    }

    public final Lsys getLsys() {
        return lsys;
    }

    public final int getExpandedSize() {
        return expandedSize;
    }

    public final Map<Character, Integer> getOccurances() {
        return Collections.unmodifiableMap(occurances);
    }
    
    public final void calculate(Function<LsysCalculator, Boolean> sentinal) {
        // If already finished, abort.
        if (this.finished.get()) {
            // Abort.
            return;
        }
        
        // If was already active abort.
        if (this.active.getAndSet(true)) {
            // Abort.
            return;
        }
        
        // Create an iterator over the system.
        final Iterator<Character> iterator = this.lsys.iterator(this.depth);
        
        // Iterate over the system calling the processing function.
        while (iterator.hasNext() && sentinal.apply(this)) {
            // Increment the index.
            this.expandedSize++;
            // Call the processing function with the character.
            this.process(this.expandedSize, iterator.next());
        }
        
        // Increment the index once more to get the actual expanded size.
        this.expandedSize++;
        
        // Set finished.
        this.finished.set(true);
        
        // Set not active.
        this.active.set(false);
    }
    
    private void process(int index, Character c) {
        // Get the occurance count.
        final int count = this.occurances.get(c) + 1;
        // Update the occurance count.
        this.occurances.put(c, count);
        // Call the extended processing function.
        this.processExtended(index, c, count);
    }
    
    protected void processExtended(int index, Character c, int occurances) {
        // Do nothing, as is intended for extensions.
    }
    
}
