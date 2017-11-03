/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.turtle;

/**
 *
 * @author Ryan
 */
public class TurtleState {
    
    public static final String FORMAT_TURTLE_STATE = "<TurtleState> { x: %d, y: %d, rot: %d }";
    
    private double x;
    private double y;
    private double rot;
    
    public TurtleState(TurtleState other) {
        this.x = other.x;
        this.y = other.y;
        this.rot = other.rot;
    }

    public TurtleState(double x, double y, double rot) {
        this.x = x;
        this.y = y;
        this.rot = rot;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRot() {
        return rot;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_TURTLE_STATE, this.x, this.y, this.rot);
    }
    
}
