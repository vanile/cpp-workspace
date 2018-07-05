/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.interactive;

import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class SimpleExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Enter line of text:");
		Scanner sc = new Scanner(System.in);

		String line = sc.nextLine();
		
		System.out.println(line);
		
	}

}
