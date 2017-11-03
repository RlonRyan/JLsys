/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.render;

import com.github.rlonryan.jlsys.Lsys;
import com.github.rlonryan.jlsys.LsysExamples;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryan
 */
public class LsysImageCreator {

    public static void main(String[] args) {
        // Choose an LSystem.
        final Lsys lsys = chooseLsystem();

        // Get the L-System's render attributes.
        final LsysRenderAttributes attributes = new LsysRenderAttributes(lsys);
        
        // Choose the rendering depth.
        final int depth = chooseDepth();
        
        // Set the depth in the attributes.
        attributes.getDepth().setCurrent(depth);
        
        // The image dimensions.
        final int width = 4096;
        final int height = 4096;

        // Create a buffered image to draw to.
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a graphics object to use.
        final Graphics2D graphics = image.createGraphics();

        // Create a renderer.
        final LsysRenderer renderer = new LsysRenderer(lsys, width, height, attributes);

        // Create a render thread.
        final Thread thread = new Thread(() -> {
            renderer.render(graphics);
        });

        // Start the thread.
        thread.start();
        
        // Get the start time.
        final long startTime = System.currentTimeMillis();
        
        // Log render start.
        System.out.println("Render started!");

        // Wait for the thread to finish, waiting in increments of 0.5 seconds.
        while (thread.isAlive()) {
            // Get the render progress.
            final int progress = renderer.getProgress();
            final int expanded = renderer.getExpandedSize();
            final double percent = 100.0 * progress / (double) expanded;
            // Print out the progress.
            System.out.printf("Render progress: %d / %d (%.2f%%)%n", progress, expanded, percent);
            // Sleep for some time.
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.err.println("Who dares disturb my slumber?");
            }
        }
        
        // Dispose of the graphics.
        graphics.dispose();
        
        // Get the finish time.
        final long finishTime = System.currentTimeMillis();
        
        // Calculate the run time.
        final long runTime = finishTime - startTime;
        
        // Log render finish.
        System.out.printf("Render complete! (%d ms)", runTime);
        
        // Get the save file.
        final Path savepath = Paths.get(lsys.getProperty("name").orElse("unnamed") + "_" + System.currentTimeMillis() + ".png");
        final File savefile = savepath.toFile();
        
        try {
            // Save the buffered image to the current run directory named as the system time.
            ImageIO.write(image, "png", savefile);
        } catch (IOException ex) {
            System.err.printf("Unable to save rendering to file: \"%s\"!%n", savepath);
            ex.printStackTrace();
        }
    }
    
    public static Lsys chooseLsystem() {
        // Print out the L-System Choices
        for (int i = 0; i < LsysExamples.EXAMPLES.size(); i++) {
            // Print out the option.
            System.out.printf("%2d: %s%n", i, LsysExamples.EXAMPLES.get(i).getProperty("name").orElse("unnamed"));
        }
        
        // The index of the choosen example L-System.
        int choice = -1;
        
        // Create an input scanner.
        final Scanner scanner = new Scanner(System.in);
        
        // Prompt to choose an L-System.
        System.out.println("Please select an L-System to be rendered.");
        
        // Loop until get valid input.
        while (true) {
            // Prompt.
            System.out.print("Enter Index of Selected L-System:\n");
            // Read in a number.
            choice = scanner.nextInt();
            // Test if valid.
            if (choice >= 0 && choice < LsysExamples.EXAMPLES.size()) {
                // Break out of the loop, as we have choosen an L-System to render.
                break;
            }
            // Otherwise, we need to inform the user that they are being less than intelligent.
            else {
                System.out.printf("Invalid selection: %d! Selection must be between 0 and %d!%n", choice, LsysExamples.EXAMPLES.size());
            }
        }
        
        // Get the choosen L-System
        final Lsys lsys = LsysExamples.EXAMPLES.get(choice);
        
        // Log the choosen L-System.
        System.out.printf("Selected %d: %s!%n", choice, lsys.getProperty("name").orElse("unamed"));
        
        // Return the user's choice.
        return lsys;
    }
    
    public static int chooseDepth() {
        // The choosen depth.
        int depth = -1;
        
        // Create an input scanner.
        final Scanner scanner = new Scanner(System.in);
        
        // Loop forever asking for a valid depth.
        while (true) {
            // Prompt for depth.
            System.out.print("Enter rendering depth:\n");
            // Scan in depth.
            depth = scanner.nextInt();
            // Test if valid.
            if (depth >= 0) {
                // Break out of the loop as we have selected a rendering depth to use.
                break;
            }
            // Otherwise, we need to inform the user that they are being less than intelligent.
            else {
                System.out.printf("Invalid rendering depth: %d! Rendering depth must be non-negative!", depth);
            }
        }
        
        // Log the selected rendering depth.
        System.out.printf("Selected rendering depth: %d!%n", depth);
        
        // Return the selected depth.
        return depth;
    }

}
