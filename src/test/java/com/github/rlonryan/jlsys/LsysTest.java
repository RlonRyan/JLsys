/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
public class LsysTest {
    
    public LsysTest() {
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
     * Test of hasExpansion method, of class Lsys.
     */
    @Test
    public void testHasProductions() {
        System.out.println("hasProduction");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaaa")
                .withAxiom(axiom)
                .build();
        assertTrue("L-System does not contain production for symbol 'a'!", instance.hasProduction('a'));
        assertFalse("L-System contains production for symbol 'A'!", instance.hasProduction('A'));
    }

    /**
     * Test of hasSymbol method, of class Lsys.
     */
    @Test
    public void testHasSymbol() {
        System.out.println("hasSymbol");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaaa")
                .withAxiom(axiom)
                .build();
        assertTrue("L-System does not contain symbol 'a' in alphabet!", instance.hasSymbol('a'));
        assertFalse("L-System contains symbol 'A' in alphabet!", instance.hasSymbol('A'));
    }

    /**
     * Test of getAxiom method, of class Lsys.
     */
    @Test
    public void testGetAxiom() {
        System.out.println("getAxiom");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withAxiom(axiom)
                .build();
        assertEquals("Axiom magically transmuted into something else!", axiom, instance.getAxiom());
    }

    /**
     * Test of getAlphabet method, of class Lsys.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("getAlphabet");
        final String symbols = "dfsahlfdksajbhgcl";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withAxiom(axiom)
                .build();
        final Set<Character> expResult = new HashSet<>();
        for (int i = 0; i < symbols.length(); i++) {
            expResult.add(symbols.charAt(i));
        }
        final Set<Character> result = instance.getAlphabet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpansion method, of class Lsys.
     */
    @Test
    public void testGetExpansion() {
        System.out.println("getExpansion");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        assertEquals("aaa", instance.getProduction('a'));
        assertEquals("bb", instance.getProduction('b'));
        assertEquals("c", instance.getProduction('c'));
        assertEquals(null, instance.getProduction('f'));
    }

    /**
     * Test of getExpansions method, of class Lsys.
     */
    @Test
    public void testGetExpansions() {
        System.out.println("getExpansions");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        final Map<Character, String> expResult = new HashMap<>();
        expResult.put('a', "aaa");
        expResult.put('b', "bb");
        expResult.put('c', "c");
        final Map<Character, String> result = instance.getProductions();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class Lsys.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        final int depth = 3;
        final String symbols = "abc";
        final String axiom = "abcba";
        final StringBuilder sb = new StringBuilder();
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        instance.iterator(depth).forEachRemaining(sb::append);
        final String expected = "aaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbcbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        final String actual = sb.toString();
        assertEquals(expected, actual);
    }

    /**
     * Test of equals method, of class Lsys.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instanceA = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        final Lsys instanceB = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        assertTrue("L-system equals identical L-System.", instanceA.equals(instanceB));
        assertFalse("L-System equals test object.", instanceA.equals(this));
        assertFalse("L-System equals random null.", instanceA.equals(null));
    }

    /**
     * Test of hashCode method, of class Lsys.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instanceA = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        final Lsys instanceB = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        assertEquals("Hashcode is inconsistant.", instanceA.hashCode(), instanceB.hashCode());
    }

    /**
     * Test of toString method, of class Lsys.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        final String symbols = "abc";
        final String axiom = "abcba";
        final Lsys instance = new LsysBuilder()
                .withSymbols(symbols)
                .withProduction('a', "aaa")
                .withProduction('b', "bb")
                .withProduction('c', "c")
                .withAxiom(axiom)
                .build();
        String expResult = "Lsys {\n\tAxiom: 'abcba'\n\tAlphabet: [a, b, c]\n\tProductions:\n\t\t'a' -> 'aaa'\n\t\t'b' -> 'bb'\n\t\t'c' -> 'c'\n}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
