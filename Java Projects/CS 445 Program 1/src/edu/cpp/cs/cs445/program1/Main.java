package edu.cpp.cs.cs445.program1;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/***************************************************************
* file: Main.java
* author: Alexander Kimea
* class: CS 445-01 - Computer Graphics
*
* assignment: program 1
* date last modified: 4/7/2017
*
* purpose: This program uses the LWJGL library to draw a window of 640x480
* in the center of the screen. The program reads coordinates from a file titled
* coordinates.txt to draw primitives in the same window.
*
****************************************************************/ 
public class Main {
    
    private File coordTxt;
    private ArrayList<Primitive> primitives;
    
    /**
     * Constructor for the Main class. Creates ArrayList object
     * for Primitive types (Circle, Line, Ellipse)
     */
    public Main() {
        primitives = new ArrayList<Primitive>();
    }
    
    /**
     * Where the program begins. Launches an instance of this Main class.
     * Sets a File object of coordinates.txt
     * Starts the whole process of creating a computer graphic
     * on screen.
     */
    public static void main(String args[]) {
        Main main = new Main();
        main.setCoordinateFile();
        main.start();
    }
    /**
     * Knowing that coordinates are stored in coordinates.txt,
     * This method will set a File object with filepath to coordinates.txt.
     * The txt file must be in the immediate/default directory.
     */
    private void setCoordinateFile() {
        String fileName = "coordinates.txt";
        
        try {
            this.coordTxt = new File(fileName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses coordinates.txt and creates Primitive objects based
     * on given information. These objects are placed into ArrayLists.
     * This parser assumes coordinates.txt is already formatted.
     * @param file
     * @throws FileNotFoundException 
     */
    private void parseFile(File file) throws FileNotFoundException {
        Scanner fileSc = new Scanner(file);

        while(fileSc.hasNextLine()) {
            Scanner stringSc;
            String newL;
            Primitive newP;
            String shapeIndicator = fileSc.next();
            
            if (shapeIndicator.equals("l")) {
                newL = fileSc.nextLine().replace(",", " ");
                stringSc = new Scanner(newL);
                
                int startX = stringSc.nextInt();
                int startY = stringSc.nextInt();
                int endX = stringSc.nextInt();
                int endY = stringSc.nextInt();
                
                newP = new Line(startX, startY, endX, endY);
                primitives.add(newP);
            } else if (shapeIndicator.equals("c")) {
                newL = fileSc.nextLine().replace(",", " ");
                stringSc = new Scanner(newL);
                
                int centerX = stringSc.nextInt();
                int centerY = stringSc.nextInt();
                int radius = stringSc.nextInt();
                
                newP = new Circle(centerX, centerY, radius);
                primitives.add(newP);
            } else if (shapeIndicator.equals("e")) {
                newL = fileSc.nextLine().replace(",", " ");
                stringSc = new Scanner(newL);
                
                int centerX = stringSc.nextInt();
                int centerY = stringSc.nextInt();
                int rX = stringSc.nextInt();
                int rY = stringSc.nextInt();
                
                newP = new Ellipse(centerX, centerY, rX, rY);
                primitives.add(newP);
            } else { }
        }
    }
    
    /**
     * This method starts the whole process of producing a graphic on the screen
     * by creating a window, initializing graphics libraries, and then rendering
     * the graphics.
     */
    public void start() {
        try {
            parseFile(coordTxt);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            createWindow();
            initGL();
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method creates a new window on the user's screen with set
     * properties and attributes.
     * @throws Exception 
     */
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("CS 445 Program 1");
        
        Display.create();
    }
    
    /**
     * This method will initiate properties and attributes
     * to the graphics libraries such as setting up the
     * coordinate graph.
     */
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    /**
     * This method will render graphics from the file onto the window.
     * Calls render() on all primitives in the ArrayList.
     */
    private void render() {
        while(!Display.isCloseRequested()) {
            try {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                
                for (int i = 0; i < primitives.size(); i++) {
                    primitives.get(i).render();
                }

                Display.update();
                Display.sync(60); 
                checkIfExit();
            } catch(Exception e) {
                
            }
        }
        Display.destroy();
    }
    
    /**
     * After everything has been rendered. Checks if user presses
     * ESC key on keyboard and if so, program will exit.
     * @throws LWJGLException 
     */
    private void checkIfExit() throws LWJGLException {
        Keyboard.create();
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                    System.exit(0);
        }
    }
}
