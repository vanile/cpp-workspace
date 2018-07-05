package edu.cpp.cs.cs445.program1;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import static org.lwjgl.opengl.GL11.*;

/***************************************************************
* file: Line.java
* author: Alexander Kimea
* class: CS 445-01 - Computer Graphics
*
* assignment: program 1
* date last modified: 4/9/2017
*
* purpose: This program uses the LWJGL library to draw a window of 640x480
* in the center of the screen. The program reads coordinates from a file titled
* coordinates.txt to draw primitives in the same window.
*
****************************************************************/ 
public class Line implements Primitive {
    
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    /**
     * Constructor used to set Line object attributes
     * @param startX
     * @param startY
     * @param endX
     * @param endY 
     */
    public Line(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    /**
     * Returns integer start point X value
     * @return 
     */
    public int getStartX() {
        return startX;
    }
    
    /**
     * Returns integer start point Y value
     * @return 
     */
    public int getStartY() {
        return startY;
    }
    
    /**
     * Returns integer end point X value
     * @return 
     */
    public int getEndX() {
        return endX;
    }
    
    /**
     * Returns integer end point Y value
     * @return 
     */
    public int getEndY() {
        return endY;
    }
    /**
     * Calculates the line's slope based on
     * coordiantes from this object.
     * @return 
     */
    private float calculateSlope() {
        int rise = endY - startY;
        int run = endX - startX;
        
        if (run == 0) {
            return -9398.4562f;
        }
        return (float) rise / run;
    }
    
    /**
     * Called to render the line on screen using Line attributes.
     * Uses midpoint line algorithms
     */
    @Override
    public void render() {
        int incrementRight, incrementUpRight;
        int decrementRight, decrementDownRight;
        
        float slope = calculateSlope();
        int x = startX;
        int y = startY;
        int dx = endX - x;
        int dy = endY - y;
        int d = (2 * dy) - dx;

        glColor3f(1.0f, 0.0f, 0.0f);
        glPointSize(1);
        
        glBegin(GL_POINTS);
        
        if (slope < 0) {
            if (endY > startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }
            
            //d = dy - (2 * dx);/////
            glVertex2d(x, y); 
            while (y != endY) {
                
                dx = endX - x;
                dy = endY - y;
                decrementRight = 2 * dy;
                decrementDownRight = 2 * (dy - dx);
                
                if (d > 0) {
                    x += 1;
                    d += decrementRight;
                    glVertex2d(x,y);
                } else {
                    x -= 1;
                    y -= 1;
                    d -= decrementDownRight;
                } 
                //glVertex2d(x,y);
            }
        } else if (slope > 1) {
             if (endY < startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }
             
             glVertex2d(x,y);
             while (y != endY) {
                dx = endX - x;
                dy = endY - y;
                
                incrementRight = 2 * dy;
                incrementUpRight = 2 * (dy - dx);
                
                if (d > 0) {
                    x += 1;
                    y += 1;
                    d -= incrementUpRight;
                } else {
                    x -= 1;
                    d += incrementRight;
                }
                glVertex2d(x, y);                 
             }
        } else if (slope == 0) {
             if (endX < startX) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }
             
             glVertex2d(x, y); 
             while (x != endX) {
                 x++;
                 glVertex2d(x, y); 
             }
        } else if (slope == -9398.4562f) {
            if (endY < startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }
            glVertex2d(x, y);
            while (y != endY) {
                y++;
                glVertex2d(x, y);        
            }
        } else {
            if (endY < startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }

            glVertex2d(x, y);
            while(x != endX) {
                dx = endX - x;
                dy = endY - y;
                
                incrementRight = 2 * dy;
                incrementUpRight = 2 * (dy - dx);
                
//                System.out.println(x +"," +y);
//                System.out.println(dx + "," + dy + " " + d);  
                if (d > 0) {
                    x += 1;
                    y += 1;
                    d += incrementUpRight;
                } else if (d <= 0) {
                    x += 1;
                    d += incrementRight;
                } else { }

                glVertex2d(x, y);
            }
        }
        
        glEnd();
    }
    
    /**
     * Switches this object's coordinate (start and end) 
     * end points depending on which came first.
     * Used to start drawing points from left to right side.
     */
    private void swap() {
        int temp = startX;
        startX = endX;
        endX = temp;
        
        temp = startY;
        startY = endY;
        endY = temp;
    }
    
    /**
     * Not used. Uses GL_LINE_STRIP to draw lines.
     * Debug and see if line drawn with
     * main implementation is correct.
     */
    public void render2() {
        glColor3f(1.0f, 0.0f, 0.0f);
        
        glBegin(GL_LINES);
        
        glVertex2d(startX, startY);
        glVertex2d(endX, endY);
        
        glEnd();
    }
    
    /**
     * Not used. Old implementation
     */
    public void render3() {
        int incrementRight, incrementUpRight;
        int decrementRight, decrementDownRight;
        
        float slope = calculateSlope();
        int x = startX;
        int y = startY;
        int dx = endX - x;
        int dy = endY - y;
        int d = (2 * dy) - dx;

        glColor3f(1.0f, 0.0f, 0.0f);
        glPointSize(1);
        
        glBegin(GL_POINTS);
        
        if (slope < 0) {
            if (endY > startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }

            glVertex2d(x, y); 
            
            while (x != endX) {
                dx = endX - x;
                dy = endY - y;
                
                decrementRight = 2 * dy;
                decrementDownRight = 2 * (dy - dx);
                
                System.out.println(x +"," +y);
                System.out.println(dx + "," + dy + " " + d);

                if (d > 0) {
                    //x += 1;
                    y -= 1;
                    d += decrementRight;
                } else {
                    x += 1;
                    y -= 1;
                    d += decrementDownRight;
                } 
                //System.out.println("d" + d);
                //System.out.println(x +"," +y);
                glVertex2d(x, y);
            }
        } else {
            if (endY < startY) {
                swap();
                x = startX;
                y = startY;
                
                dx = endX - x;
                dy = endY - y;
                d = (2 * dy) - dx;
            }
            glVertex2d(x, y);
            
            //for(i = startX; i <= endX; i++) {
            //for(; i <= end; i++) {
            while(x != endX) {
                //System.out.println(x + "," + y + " - " + endX + "," + endY);
                dx = endX - x;
                dy = endY - y;
                
                incrementRight = 2 * dy;
                incrementUpRight = 2 * (dy - dx);

                if (d > 0) {
                    x += 1;
                    y += 1;
                    d += incrementUpRight;
                } else if (d <= 0) {
                    x += 1;
                    d += incrementRight;
                } else { }

                glVertex2d(x, y);
            }
        }
        
        glEnd();
    }
}
