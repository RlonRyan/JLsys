/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.language;

import java.util.List;

/**
 * A word is an ordered set of symbols.
 * 
 * @author Ryan
 */
public interface Word {
    
    List<Symbol> getSymbols();
    
}
