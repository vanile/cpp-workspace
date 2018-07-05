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
public class IOPractice7 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//PrintWriter pw = new PrintWriter(new File("table.txt"));
		
		double[] row1 = {451.4566, 456782.23, 3.34};
		double[] row2 = {443546456.4354456, 534556673.0, 6.344557};
		
		for (double d : row1) {
			System.out.printf("%20.3f",d);
		}
		
		System.out.println();
		
		for (double d : row2) {
			System.out.printf("%20.3f", d);
		}
		
		//pw.close();
	}

}
