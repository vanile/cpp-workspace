/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IOPractice6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] n = {1, 3, 5, 6};
		
		double d = 1000.0/3.0;
		double d2 = 1000000.0/3.0;
		int width = 15;
		
		String s = (new Double(d)).toString();
		
		int size = s.length();
		
		System.out.printf("result = \t%" + width + ".2f\n", n);
		System.out.printf("result = \t%" + width + ".2f\n", d2);
		System.out.println(size);
	}

	
	
	
	
	
	
	
}
