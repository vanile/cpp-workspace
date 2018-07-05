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
public class Loops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		forExample();
	}

	public static void whileExample() {
		int x = 5;

		while (x > 0) {
			System.out.println("x is positive!");
			x = x - 1;
		}

		System.out.println("x is no longer positive!");
	}

	public static void forExample() {
		int x = 5;

		for (int i = 0; i < x; i = i + 1) {
			System.out.println("The current value of i is: " + i
					+ ", which is still less than x!");
		}

		System.out.println("Now i has reached x!");
	}

}
