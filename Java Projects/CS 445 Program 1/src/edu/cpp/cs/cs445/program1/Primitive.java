package edu.cpp.cs.cs445.program1;

/***************************************************************
* file: Primitive.java
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
public interface Primitive {
    
    /**
     * Inheritable by Circle, Line, Ellipse classes. Used so we can
     * make an ArrayList of all primitive types and loop through
     * them to call render() and render each one on screen.
     */
    public void render();
}
