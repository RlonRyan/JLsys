/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.gui;

import com.github.rlonryan.jlsys.Lsys;
import com.github.rlonryan.jlsys.render.LsysCalculatorBounds;
import com.github.rlonryan.jlsys.render.LsysRenderAttributes;
import com.github.rlonryan.jlsys.turtle.Turtle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class ViewRenderLsys extends JPanel implements KeyListener {
    
    private Lsys lsys;
    private LsysRenderAttributes attributes;
    private LsysCalculatorBounds bounds;
    
    BufferStrategy buffer;

    /**
     * Creates new form LsysRenderer
     * 
     * @param lsys the L-System to start out with.
     */
    public ViewRenderLsys(Lsys lsys) {
        this.setLsys(lsys);
    }

    public final void setLsys(Lsys lsys) {
        this.lsys = lsys;
        this.attributes = new LsysRenderAttributes(lsys);
        this.onUpdate();
    }
    
    public final void onUpdate() {
        this.bounds = new LsysCalculatorBounds(this.lsys, this.attributes);
        SwingUtilities.invokeLater(() -> {
            this.bounds.calculate((b) -> true);
            this.repaint();
        });
    }

    public static void drawTargetBox(Graphics2D g, int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
        g.drawLine(x, y, x + w, y + h);
        g.drawLine(x, y + h, x + w, y);
    }
    
    public static void drawInfo(Graphics2D g, LsysRenderAttributes attributes, LsysCalculatorBounds bounds, int x, int y) {
        final Color oldColor = g.getColor();
        int i = 1;
        g.setColor(Color.YELLOW);
        g.drawString(String.format("Depth: %d", attributes.getDepth().getCurrent()), 0, 10 * i++);
        g.drawString(String.format("Size:  %d", bounds.getExpandedSize()), 0, 10 * i++);
        g.drawString(String.format("Step:  %.2f", attributes.getMovement().getCurrent()), 0, 10 * i++);
        g.drawString(String.format("Angle: %.2f", attributes.getAngle().getCurrent()), 0, 10 * i++);
        g.setColor(oldColor);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        // Do a super call!
        super.paintComponent(graphics);
        
        // Cast to superior graphics2d.
        final Graphics2D g2d = (Graphics2D)graphics;
        
        // Clear the screen.
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // If bounds are not ready, then don't do anything.
        if (!this.bounds.isFinished()) {
            return;
        }

        // Calculate the step.
        final double step = this.bounds.calculateStep(this.getWidth(), this.getHeight());

        // Pad the sides so that rendering is in the center of the window.
        final double padAutoX = Math.abs(this.getWidth() - (bounds.getWidth() * step)) / 2;
        final double padAutoY = Math.abs(this.getHeight() - (bounds.getHeight() * step)) / 2;

        // Set the anchor for the drawing.
        final double anchorX = padAutoX - bounds.getMinX() * step;
        final double anchorY = padAutoY - bounds.getMinY() * step;
        
        //System.out.printf("Window: { w: %d, h: %d }\n", this.getWidth(), this.getHeight());
        //System.out.printf("Bounds: { w: %d, h: %d }\n", (int)(bounds.getWidth() * step), (int)(bounds.getHeight() * step));
        //System.out.printf("Anchor: { x: %d, y: %d }\n", (int)anchorX, (int)anchorY);

        // Draw information.
        drawInfo(g2d, attributes, bounds, 0, 0);

        // Draw bounds.
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) padAutoX, (int) padAutoY, (int)(bounds.getWidth() * step), (int)(bounds.getHeight() * step));

        // Translate to move system into bounds.
        g2d.translate(anchorX, anchorY);

        // Create a new iterator and turtle.
        final Iterator<Character> iterator = this.lsys.iterator(this.attributes.getDepth().getCurrent());
        final Turtle turtle = new Turtle();
        
        // Create a line object.
        final Line2D.Double line = new Line2D.Double();
        
        // Set the draw color.
        g2d.setColor(Color.WHITE);
        
        // Fetch the angle.
        final double angrad = Math.toRadians(this.attributes.getAngle().getCurrent());
        
        // Loop through the iterator, tracking the turtle's movements.
        while(iterator.hasNext()) {
            // Have the turtle execute the command.
            boolean result = turtle.execute(iterator.next(), step, angrad);
            // Get the new position.
            line.x2 = turtle.getX();
            line.y2 = turtle.getY();
            // If this is a drawing command, draw.
            if (result) {
                g2d.draw(line);
            }
            // Update old position to be new position.
            line.x1 = line.x2;
            line.y1 = line.y2;
        }
    }
        

    @Override
    public void keyTyped(KeyEvent e) {
        // Nop
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println();
        // Do stuff.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_PLUS:
                if (this.attributes.getDepth().getCurrent() < this.attributes.getDepth().getMax()) {
                    this.attributes.getDepth().addCurrent(1);
                    System.out.println("Increasing rendering depth by one to " + this.attributes.getDepth().getCurrent() + "!");
                    this.onUpdate();
                } else {
                    System.out.println("Cannot increase rendering depth! Rendering depth is already at maximum of " + this.attributes.getDepth().getMax() + "!");
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_MINUS:
                if (this.attributes.getDepth().getCurrent() > this.attributes.getDepth().getMin()) {
                    this.attributes.getDepth().subCurrent(1);
                    System.out.println("Decreasing rendering depth by one to " + this.attributes.getDepth().getCurrent() + "!");
                    this.onUpdate();
                } else {
                    System.out.println("Cannot decrease rendering depth! Rendering depth is already at minimum of zero!");
                }
                break;
            case KeyEvent.VK_LEFT:
                this.attributes.getAngle().subCurrent(1);
                this.attributes.getAngle().modCurrent(360);
                System.out.printf("Decreased turn angle to: %f!\n", Math.toDegrees(this.attributes.getAngle().getCurrent()));
                this.onUpdate();
                break;
            case KeyEvent.VK_RIGHT:
                this.attributes.getAngle().addCurrent(1);
                this.attributes.getAngle().modCurrent(360);
                System.out.printf("Increased turn angle to: %f!\n", Math.toDegrees(this.attributes.getAngle().getCurrent()));
                this.onUpdate();
                break;
            case KeyEvent.VK_R:
                System.out.println("Reset rendering parameters to default!");
                this.attributes.reset();
                this.onUpdate();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nop
    }

}
