package edu.cpp.cs.cs445.program1;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import static org.lwjgl.opengl.GL11.*;

/***************************************************************
* file: Circle.java
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
public class Circle implements Primitive {
    
    private int centerX;
    private int centerY;
    private int radius;
    
    /**
     * Constructor used to set circle attributes
     * @param centerX
     * @param centerY
     * @param radius 
     */
    public Circle(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }
    
    /**
     * Returns integer center X attribute
     * @return 
     */
    public int getCenterX() {
        return centerX;
    }
    
    /**
     * Returns integer center Y attribute
     * @return 
     */
    public int getCenterY() {
        return centerY;
    }
    
    /**
     * Returns integer radius attribute
     * @return 
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Called to render the circle using attributes
     * on screen. Uses circle algorithm to draw.
     */
    @Override
    public void render() {
        glColor3f(0.0f, 0.0f, 1.0f);
        glPointSize(1);
        
        glBegin(GL_POINTS);
        
        for(int i = 0; i <= 360; i++) {
            double x = (radius * Math.cos(i)) + centerX;
            double y = (radius * Math.sin(i)) + centerY;
            glVertex2d(x, y);
        }
        
        glEnd();
    }
}
