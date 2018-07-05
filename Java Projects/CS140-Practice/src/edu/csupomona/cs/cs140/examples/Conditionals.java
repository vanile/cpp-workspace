/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.examples;

/**
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class Conditionals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = -1;

		if (x > 0) {
			System.out.println("x is positive! isn't that nice?");
		} else if (x < 0) {
			System.out.println("x is negative! isn't that a bummer?");
		} else {
			System.out.println("x is zero! whatever...");
		}
	}

}
