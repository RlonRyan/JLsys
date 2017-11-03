/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.language;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
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
public class AlphabetTest {
    
    public AlphabetTest() {
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
     * Test of toString method, of class Alphabet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        // Create a list of letters.
        final List<Symbol> letters = new ArrayList<>();
        // Add to the letter list.
        letters.add(new SymbolCharacter('a'));
        letters.add(new SymbolCharacter('b'));
        letters.add(new SymbolCharacter('c'));
        // Create an alphabet with the given characters.
        final Alphabet instance = new Alphabet(letters);
        // Print out the alphabet.
        System.out.println(instance.toString());
    }
    
}
