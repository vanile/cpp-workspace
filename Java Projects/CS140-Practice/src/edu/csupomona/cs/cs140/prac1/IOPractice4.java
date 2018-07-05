/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IOPractice4 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("file.txt");
		PrintWriter pw = new PrintWriter(file);
		pw.println("This is my first line!");
		pw.println("This my second line!");
		pw.println(42);
		pw.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
