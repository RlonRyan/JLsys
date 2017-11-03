/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ryeni
 */
public class LsysBuilderTest {
    
    public LsysBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of withSymbol method, of class LsysBuilder.
     */
    @Test
    public void testKoch() {
        // Log test start.
        System.out.println("testKoch");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('+');
        lb.withSymbol('-');
        lb.withSymbol('A');
        // Add only production.
        lb.withProduction('A', "AA-A-A-A-A-A+A");
        // Set the axiom.
        lb.withAxiom("A-A-A-A");
        // Create L-System.
        final Lsys lsys = lb.build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAxiom() {
        // Log test start.
        System.out.println("testInvalidAxiom");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aa\0a");
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid axiom containing null character!");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductionRuleSymbol() {
        // Log test start.
        System.out.println("testInvalidProductionRuleSymbol");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aaa");
        // Attempt to add a bad production rule (a production rule with a bad target symbol).
        lb.withProduction('\0', "aaaa");
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid production rule with target symbol equal to null character!");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUnknownProductionRuleSymbol() {
        // Log test start.
        System.out.println("testUnknownProductionRuleSymbol");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aaa");
        // Attempt to add a bad production rule (a production rule with an unknown target symbol).
        lb.withProduction('b', "aaaa");
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid production rule with target symbol not in defined alphabet!");
    }
    
    @Test(expected = NullPointerException.class)
    public void testNullProductionRuleExpansion() {
        // Log test start.
        System.out.println("testNullProductionRuleExpansion");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aaa");
        // Attempt to add a bad production rule (a production rule with an unknown target symbol).
        lb.withProduction('a', null);
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid production rule with a null expansion string!");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductionRuleExpansion() {
        // Log test start.
        System.out.println("testInvalidProductionRuleExpansion");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aaa");
        // Attempt to add a bad production rule (a production rule with an unknown target symbol).
        lb.withProduction('a', "aa\0aaa");
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid production rule with an expansion string containing the null character!");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUnknownProductionRuleExpansion() {
        // Log test start.
        System.out.println("testUnknownProductionRuleExpansion");
        // Create the L-System builder instance.
        final LsysBuilder lb = new LsysBuilder();
        // Add symbols to alphabet.
        lb.withSymbol('a');
        // Set axiom.
        lb.withAxiom("aaa");
        // Attempt to add a bad production rule (a production rule with an unknown target symbol).
        lb.withProduction('a', "abababa");
        // If we make it to here, we fail!
        fail("Managed to create an L-System builder with an invalid production rule with an expansion string containing a symbol not in the alphabet!");
    }
    
}
