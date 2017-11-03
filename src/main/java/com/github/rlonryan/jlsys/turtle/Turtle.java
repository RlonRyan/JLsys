/*
 * 
 */
package com.github.rlonryan.jlsys.turtle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A simple turtle class.
 *
 * @author Ryan
 */
public class Turtle {

    private final Deque<TurtleState> states;
    
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    
    public Turtle() {
        this(0, 0, 0);
    }

    public Turtle(double x, double y, double rotation) {
        this.states = new ArrayDeque<>();
        this.states.add(new TurtleState(x, y, rotation));
        this.minX = x;
        this.minY = y;
        this.maxX = x;
        this.maxY = y;
    }

    public double getX() {
        return this.states.peek().getX();
    }

    public double getY() {
        return this.states.peek().getY();
    }

    public double getRot() {
        return this.states.peek().getRot();
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setX(double x) {
        // Update the x-coordinate.
        this.states.peek().setX(x);
        // Update min/max
        if (x < this.minX) {
            this.minX = x;
        } else if (x > this.maxX) {
            this.maxX = x;
        }
    }
    
    public void setY(double y) {
        // Update the y-coordinate.
        this.states.peek().setY(y);
        // Update min/max
        if (y < this.minY) {
            this.minY = y;
        } else if (y > this.maxY) {
            this.maxY = y;
        }
    }

    public void setPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void setRot(double rot) {
        this.states.peek().setRot(rot);
    }

    public void addX(double dx) {
        this.setX(this.getX() + dx);
    }

    public void addY(double dy) {
        this.setY(this.getY() + dy);
    }

    public void rotate(double angle) {
        this.setRot(this.getRot() + angle);
    }
    
    public void left() {
        this.setRot(this.getRot() + Math.PI / 2);
    }
    
    public void right() {
        this.setRot(this.getRot() + Math.PI / 2);
    }

    public void forward(double movementStep) {
        this.addX(movementStep * Math.cos(this.getRot()));
        this.addY(movementStep * Math.sin(this.getRot()));
    }
    
    public void backward(double movementStep) {
        this.addX(-movementStep * Math.cos(this.getRot()));
        this.addY(-movementStep * Math.sin(this.getRot()));
    }

    public void push() {
        this.states.push(new TurtleState(this.states.peek()));
    }

    public void pop() {
        if (this.states.size() > 1) {
            this.states.pop();
        } else {
            System.out.println("Unable to pop turtle state: no remaining previous states left!");
        }
    }
    
    public void reset() {
        this.reset(0, 0, 0);
    }

    public void reset(double x, double y, double rotation) {
        this.states.clear();
        this.states.push(new TurtleState(x, y, rotation));
        this.minX = x;
        this.minY = y;
        this.maxX = x;
        this.maxY = y;
    }

    public boolean execute(char command, double movementStep, double angleStep) {
        switch (command) {
            case '+':
                this.rotate(-angleStep);
                return false;
            case '-':
                this.rotate(angleStep);
                return false;
            case '[':
                this.push();
                return false;
            case ']':
                this.pop();
                return false;
            default:
                this.forward(movementStep);
                return Character.isUpperCase(command);
        }
    }

}
