/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.gui;

import com.github.rlonryan.jlsys.Lsys;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.github.rlonryan.jlsys.LsysExamples.*;

/**
 *
 * @author Ryan
 */
public class LsysViewer extends JFrame implements KeyListener {
    
    public static final String VIEWER_TITLE = "L-System Viewer";

    private int active;
    private JFrame window;
    private ViewRenderLsys viewer;
    private Dimension viewport;

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LsysViewer()::createWindow);
    }

    public LsysViewer() {
        this.active = -1;
    }

    public void createWindow() {
        this.window = new JFrame(VIEWER_TITLE);
        this.viewer = new ViewRenderLsys(DRAGON_CURVE);
        this.viewport = new Dimension(512, 512);

        this.viewer.setMinimumSize(this.viewport);
        this.viewer.setPreferredSize(this.viewport);
        this.viewer.setSize(this.viewport);

        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setLocationRelativeTo(null);
        this.window.addKeyListener(this);
        this.window.addKeyListener(this.viewer);
        this.window.add(this.viewer);
        this.window.pack();
        this.window.setMinimumSize(this.viewport);
        this.window.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nop
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Nop
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.active = (this.active + 1) % EXAMPLES.size();
            final Lsys lsys = EXAMPLES.get(this.active);
            this.window.setTitle(VIEWER_TITLE + ": " + lsys.getProperty("name").orElse("Unamed L-System"));
            this.viewer.setLsys(lsys);
        }
    }

}
