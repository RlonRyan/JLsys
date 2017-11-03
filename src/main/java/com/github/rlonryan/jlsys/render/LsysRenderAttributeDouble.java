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
public final class LsysRenderAttributeDouble {
    
    public static final String LSYS_RENDER_ATTRIBUTE_DOUBLE_FORMAT = "<LsysRenderAttributeDouble> { name: \"%s\",  min: %f, max: %f, initial: %f, step: %f, current: %f }";
    
    private final String name;
    
    private final double min;
    private final double max;
    private final double initial;
    private final double step;
    
    private double current;
    
    public LsysRenderAttributeDouble(String name, Lsys lsys) {
        this.name = name;
        this.min = lsys.getProperty(name + "_min")
                .map(Double::parseDouble)
                .orElse(0.0);
        this.max = lsys.getProperty(name + "_max")
                .map(Double::parseDouble)
                .orElse(Double.MAX_VALUE);
        this.initial = lsys.getProperty(name + "_initial")
                .map(Double::parseDouble)
                .orElse(min);
        this.step = lsys.getProperty(name + "_step")
                .map(Double::parseDouble)
                .orElse(1.0);
        this.current = initial;
    }

    public LsysRenderAttributeDouble(String name, double min, double max, double initial, double step) {
        this(name, min, max, initial, step, initial);
    }

    public LsysRenderAttributeDouble(String name, double min, double max, double initial, double step, double current) {
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

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getInitial() {
        return initial;
    }

    public double getStep() {
        return step;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
    
    public void addCurrent(double delta) {
        this.current += delta;
    }
    
    public void subCurrent(double delta) {
        this.current -= delta;
    }
    
    public void mulCurrent(double factor) {
        this.current *= factor;
    }
    
    public void divCurrent(double divisor) {
        this.current /= divisor;
    }
    
    public void modCurrent(double divisor) {
        this.current %= divisor;
    }
    
    public void reset() {
        this.current = this.initial;
    }
    
    @Override
    public String toString() {
        return String.format(LSYS_RENDER_ATTRIBUTE_DOUBLE_FORMAT, this.name, this.min, this.max, this.initial, this.step, this.current);
    }
    
}
