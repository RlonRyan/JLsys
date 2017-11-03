/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ryan
 */
public final class LsysExamples {
    
    public static final Lsys PYTHAGORAS_TREE = new LsysBuilder()
            .withSymbols("AB+-[]")
            .withAxiom("A")
            .withProduction('A', "B[+A]-A")
            .withProduction('B', "BB")
            .withProperty("name", "Pythagoras Tree")
            //.withProperty("angle_start", "-90")
            .withProperty("angle_initial", "45")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys KOCH_CURVE = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A-A-A-A")
            .withProduction('A', "AA-A-A-A-A-A+A")
            .withProperty("name", "Koch Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys KOCH_DIAMOND = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A-A-A-A")
            .withProduction('A', "A+A-A-A+A")
            .withProperty("name", "Koch Diamond")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys KOCH_PENTAGON = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A-A-A-A-A")
            .withProduction('A', "A+A-A-A+A")
            .withProperty("name", "Koch Pentagon")
            .withProperty("angle_initial", "72")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys KOCH_SQUARE = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A-A-A-A")
            .withProduction('A', "AA-A-A-A-AA")
            .withProperty("name", "Koch Square")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys KOCH_QUADRATIC = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A-A-A-A")
            .withProduction('A', "A-A+A+AA-A-A+A")
            .withProperty("name", "Koch Quadratic")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "3")
            .build();
    
    public static final Lsys KOCH_ISLANDS = new LsysBuilder()
            .withSymbols("Ff+-")
            .withAxiom("F+F+F+F")
            .withProduction('F', "F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF")
            .withProduction('f', "ffffff")
            .withProperty("name", "Koch Islands")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "2")
            .build();
    
    public static final Lsys SIERPINSKI_TRIANGLE = new LsysBuilder()
            .withSymbols("AB+-")
            .withAxiom("A+B+B")
            .withProduction('A', "A+B-A-B+A")
            .withProduction('B', "BB")
            .withProperty("name", "Sierpinski Triangle")
            .withProperty("angle_initial", "120")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys SIERPINSKI_ARROWHEADS = new LsysBuilder()
            .withSymbols("AB+-")
            .withAxiom("A")
            .withProduction('A', "+B-A-B+")
            .withProduction('B', "-A+B+A-")
            .withProperty("name", "Sierpinski Arrowheads")
            .withProperty("angle_initial", "60")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys GOSPER_HEXAGONAL = new LsysBuilder()
            .withSymbols("AB+-")
            .withAxiom("A")
            .withProduction('A', "A+B++B-A--AA-B+")
            .withProduction('B', "-A+BB++B+A--A-B")
            .withProperty("name", "Gosper Hexagonal")
            .withProperty("angle_initial", "60")
            .withProperty("depth_initial", "4")
            .build();
    
    public static final Lsys DRAGON_CURVE = new LsysBuilder()
            .withSymbols("AB+-")
            .withAxiom("A")
            .withProduction('A', "A+B+")
            .withProduction('B', "-A-B")
            .withProperty("name", "Dragon Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "10")
            .build();
    
    public static final Lsys RECTANGLES = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A+A+A+A")
            .withProduction('A', "AA+A-A+A+AA")
            .withProperty("name", "Rectangles")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "3")
            .build();
    
    public static final Lsys POUND_CURVE = new LsysBuilder()
            .withSymbols("AaB+-")
            .withAxiom("A")
            .withProduction('A', "a+ABA+a+a+ABA+a+a+ABA+a+a+ABA+a")
            .withProduction('B', "BBB")
            .withProperty("name", "Pound Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "3")
            .build();
    
    public static final Lsys L_CURVE = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A")
            .withProduction('A', "AA+A")
            .withProperty("name", "L Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "4")
            .build();
    
    public static final Lsys P_CURVE = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A")
            .withProduction('A', "A+A-A-A-A")
            .withProperty("name", "P Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "4")
            .build();
    
    public static final Lsys S_CURVE = new LsysBuilder()
            .withSymbols("A+-")
            .withAxiom("A")
            .withProduction('A', "AA+A+AA-A-AA")
            .withProperty("name", "S Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys SQUARE_CURVE = new LsysBuilder()
            .withSymbols("A+-[]")
            .withAxiom("A")
            .withProduction('A', "AA[+AA+AA+AA]")
            .withProperty("name", "Square Curve")
            .withProperty("angle_initial", "90")
            .withProperty("depth_initial", "5")
            .build();
    
    public static final Lsys PLANT = new LsysBuilder()
            .withSymbols("AB+-[]")
            .withAxiom("A")
            .withProduction('A', "B-[[A]+A]+B[+BA]-A")
            .withProduction('B', "BB")
            .withProperty("name", "Plant")
            //.withProperty("angle_start", "-90")
            .withProperty("angle_initial", "22")
            .withProperty("depth_initial", "4")
            .build();
    
    public static final Lsys CIRCUITRY = new LsysBuilder()
            .withSymbols("ABCDEF+-[]")
            .withAxiom("FAFB")
            .withProduction('A', "F+DB[FA]-F")
            .withProduction('B', "AC-BA+A")
            .withProduction('C', "+BAE")
            .withProduction('D', "[AA]")
            .withProduction('E', "AD")
            .withProperty("name", "Circuitry")
            .withProperty("angle_initial", "60")
            .withProperty("depth_initial", "4")
            .build();
    
    public static final List<Lsys> EXAMPLES;
    
    static {
        List<Lsys> found = new ArrayList<>();
        for (Field f : LsysExamples.class.getFields()) {
            if (Lsys.class.isAssignableFrom(f.getType())) {
                try {
                    found.add((Lsys)f.get(null));
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //found.forEach(System.out::println);
        EXAMPLES = Collections.unmodifiableList(found);
    }

    private LsysExamples() {
    }
    
}
