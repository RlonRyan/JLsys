/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.render;

import com.github.rlonryan.jlsys.Lsys;
import com.github.rlonryan.jlsys.LsysCalculator;
import com.github.rlonryan.jlsys.turtle.Turtle;

/**
 *
 * @author Ryan
 */
public class LsysCalculatorBounds extends LsysCalculator {
    
    public final double MOVEMENT_UNIT = 1.0;

    private final Turtle turtle;
    private final double angleStart;
    private final double angleStep;

    public LsysCalculatorBounds(Lsys lsys, LsysRenderAttributes attributes) {
        this(
                lsys,
                attributes.getDepth().getCurrent(),
                0.0,
                Math.toRadians(attributes.getAngle().getCurrent())
        );
    }

    public LsysCalculatorBounds(Lsys lsys, int depth, double angleStart, double angleStep) {
        super(lsys, depth);
        this.turtle = new Turtle(0, 0, angleStart);
        this.angleStart = angleStart;
        this.angleStep = angleStep;
    }

    public final double getAngleStart() {
        return angleStart;
    }

    public final double getAngleStep() {
        return angleStep;
    }

    public final double getMinX() {
        return turtle.getMinX();
    }

    public final double getMinY() {
        return turtle.getMinY();
    }

    public final double getMaxX() {
        return turtle.getMaxX();
    }

    public final double getMaxY() {
        return turtle.getMaxY();
    }
    
    public final double getWidth() {
        return getMaxX() - getMinX();
    }
    
    public final double getHeight() {
        return getMaxY() - getMinY();
    }

    public final double calculateStep(double width, double height) {
        return Math.min(width / getWidth(), height / getHeight());
    }

    @Override
    protected final void processExtended(int index, Character c, int occurances) {
        // Have the turtle process the command.
        this.turtle.execute(c, MOVEMENT_UNIT, this.angleStep);
        // Call the extended turtle process function.
    }

    protected void processExtendedWithTurtle(int index, Character c, int occurances, Turtle turtle) {
        // Do nothing, as method is meant for extension.
    }

}
