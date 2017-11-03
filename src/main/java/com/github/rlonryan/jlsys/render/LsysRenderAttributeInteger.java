/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.render;

import com.github.rlonryan.jlsys.Lsys;

/**
 *
 * @author Ryan
 */
public final class LsysRenderAttributeInteger {
    
    public static final String LSYS_RENDER_ATTRIBUTE_INTEGER_FORMAT = "<LsysRenderAttributeInteger> { name: \"%s\",  min: %d, max: %d, initial: %d, step: %d, current: %d }";

    private final String name;

    private final int min;
    private final int max;
    private final int initial;
    private final int step;

    private int current;

    public LsysRenderAttributeInteger(String name, Lsys lsys) {
        this.name = name;
        this.min = lsys.getProperty(name + "_min")
                .map(Integer::parseInt)
                .orElse(0);
        this.max = lsys.getProperty(name + "_max")
                .map(Integer::parseInt)
                .orElse(Integer.MAX_VALUE);
        this.initial = lsys.getProperty(name + "_initial")
                .map(Integer::parseInt)
                .orElse(min);
        this.step = lsys.getProperty(name + "_step")
                .map(Integer::parseInt)
                .orElse(1);
        this.current = initial;
    }

    public LsysRenderAttributeInteger(String name, int min, int max, int initial, int step) {
        this(name, min, max, initial, step, initial);
    }

    public LsysRenderAttributeInteger(String name, int min, int max, int initial, int step, int current) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.initial = initial;
        this.step = step;
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getInitial() {
        return initial;
    }

    public int getStep() {
        return step;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    
    public void addCurrent(int delta) {
        this.current += delta;
    }
    
    public void subCurrent(int delta) {
        this.current -= delta;
    }
    
    public void mulCurrent(int factor) {
        this.current *= factor;
    }
    
    public void divCurrent(int divisor) {
        this.current /= divisor;
    }
    
    public void modCurrent(int divisor) {
        this.current %= divisor;
    }
    
    public void reset() {
        this.current = this.initial;
    }

    @Override
    public String toString() {
        return String.format(LSYS_RENDER_ATTRIBUTE_INTEGER_FORMAT, this.name, this.min, this.max, this.initial, this.step, this.current);
    }

}
