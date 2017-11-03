/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rlonryan.jlsys.render;

import com.github.rlonryan.jlsys.Lsys;

/**
 *
 * @author Ryan
 */
public class LsysRenderAttributes {
    
    private final LsysRenderAttributeInteger depth;
    private final LsysRenderAttributeDouble movement;
    private final LsysRenderAttributeDouble angle;
    
    public LsysRenderAttributes(Lsys lsys) {
        this.depth = new LsysRenderAttributeInteger("depth", lsys);
        this.movement = new LsysRenderAttributeDouble("movement", lsys);
        this.angle = new LsysRenderAttributeDouble("angle", lsys);
    }

    public LsysRenderAttributeInteger getDepth() {
        return depth;
    }

    public LsysRenderAttributeDouble getMovement() {
        return movement;
    }

    public LsysRenderAttributeDouble getAngle() {
        return angle;
    }
    
    public void reset() {
        this.depth.reset();
        this.movement.reset();
        this.angle.reset();
    }
    
}
