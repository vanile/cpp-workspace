package edu.cpp.cs.cs445.program1;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import static org.lwjgl.opengl.GL11.*;

/***************************************************************
* file: Ellipse.java
* author: Alexander Kimea
* class: CS 445-01 - Computer Graphics
*
* assignment: program 1
* date last modified: 4/4/2017
*
* purpose: This program uses the LWJGL library to draw a window of 640x480
* in the center of the screen. The program reads coordinates from a file titled
* coordinates.txt to draw primitives in the same window.
*
****************************************************************/ 
public class Ellipse implements Primitive {

    private int centerX;
    private int centerY;
    private int rX;
    private int rY;
    
    /**
     * Constructor used to set Ellipse attributes
     * @param centerX
     * @param centerY
     * @param rX
     * @param rY 
     */
    public Ellipse(int centerX, int centerY, int rX, int rY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.rX = rX;
        this.rY = rY;
    }
    
    /**
     * Returns integer center X value
     * @return 
     */
    public int getCenterX() {
        return centerX;
    }
    
    /**
     * Returns integer center Y value
     * @return 
     */
    public int getCenterY() {
        return centerY;
    }
    
    /**
     * Returns integer radius X value
     * @return 
     */
    public int getRX() {
        return rX;
    }
    
    /**
     * Returns integer radius Y value
     * @return 
     */
    public int getRY() {
        return rY;
    }

    /**
     * Called to render the Ellipse using set attributes on screen.
     * Uses Ellipse algorithms to draw.
     */
    @Override
    public void render() {
        glColor3f(0.0f, 1.0f, 0.0f);
        glPointSize(1);
        
        glBegin(GL_POINTS);

        for(int i = 0; i <= 360; i++) {
            double x = (rX * Math.cos(i)) + centerX;
            double y = (rY * Math.sin(i)) + centerY;
            glVertex2d(x, y);
        }
        
        glEnd();
    }
}
