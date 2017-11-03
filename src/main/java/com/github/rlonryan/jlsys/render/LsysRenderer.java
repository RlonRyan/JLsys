/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.render;

import com.github.rlonryan.jlsys.Lsys;
import com.github.rlonryan.jlsys.turtle.Turtle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Renders an L-System.
 *
 * @author Ryan
 */
public class LsysRenderer {
    
    public static final double MOVEMENT_STEP_MIN = 2.0;

    private final Lsys lsys;
    private final int width;
    private final int height;

    private final LsysRenderAttributes attributes;
    private final AtomicBoolean active;
    private final AtomicBoolean finished;

    private LsysCalculatorBounds bounds;

    private int progress;

    public LsysRenderer(Lsys lsys, int width, int height, LsysRenderAttributes attributes) {
        this.lsys = lsys;
        this.width = width;
        this.height = height;
        this.attributes = attributes;
        this.bounds = new LsysCalculatorBounds(lsys, attributes);
        this.active = new AtomicBoolean(false);
        this.finished = new AtomicBoolean(false);
        this.progress = 0;
    }

    public boolean isFinished() {
        return this.finished.get();
    }

    public Lsys getLsys() {
        return lsys;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getExpandedSize() {
        return this.bounds.getExpandedSize();
    }

    public void render(Graphics2D g) {
        // If already finished, don't do anything.
        if (this.finished.get()) {
            // Abort
            return;
        }

        // If already running, don't do anything.
        if (this.active.getAndSet(true)) {
            // Abort
            return;
        }

        // Calculate the bounds.
        this.bounds.calculate((b) -> (((LsysCalculatorBounds) b).calculateStep(this.width, this.height) > 1));

        // Calculate the step.
        double step = this.bounds.calculateStep(this.width, this.height);

        // Resize until the step is reasonable.
        while (step < MOVEMENT_STEP_MIN) {
            this.attributes.getDepth().subCurrent(1);
            System.out.printf("Computed movement step below minimum limit of %f!%n", MOVEMENT_STEP_MIN);
            System.out.println("Decreasing rendering depth by 1, automatically, to " + this.attributes.getDepth().getCurrent());
            this.bounds = new LsysCalculatorBounds(lsys, attributes);
            this.bounds.calculate((b) -> (((LsysCalculatorBounds) b).calculateStep(this.width, this.height) > 1));
            step = this.bounds.calculateStep(this.width, this.height);
        }

        // Pad the sides so that rendering is in the center of the window.
        final double padAutoX = Math.abs(this.width - bounds.getWidth() * step) / 2;
        final double padAutoY = Math.abs(this.height - bounds.getHeight() * step) / 2;

        // Set the anchor for the drawing.
        final double anchorX = padAutoX - bounds.getMinX() * step;
        final double anchorY = padAutoY - bounds.getMinY() * step;

        // Translate to move system into bounds.
        g.translate(anchorX, anchorY);

        // Create a new iterator and turtle.
        final Iterator<Character> iterator = this.lsys.iterator(this.attributes.getDepth().getCurrent());
        final Turtle turtle = new Turtle();

        // Set the draw color.
        g.setColor(Color.WHITE);

        // Fetch the angle.
        final double angrad = Math.toRadians(this.attributes.getAngle().getCurrent());

        // Create a line object.
        final Line2D.Double line = new Line2D.Double();

        // Loop through the iterator, tracking the turtle's movements.
        while (iterator.hasNext()) {
            // Have the turtle execute the command.
            boolean result = turtle.execute(iterator.next(), step, angrad);
            // Get the new position.
            line.x2 = turtle.getX();
            line.y2 = turtle.getY();
            // If this is a drawing command, draw.
            if (result) {
                g.draw(line);
            }
            // Update old position to be new position.
            line.x1 = line.x2;
            line.y1 = line.y2;
            // Update the progress counter.
            this.progress++;
        }
    }

}
