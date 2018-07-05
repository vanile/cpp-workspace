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
public class Switches {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean bit = intSwitch(0);
		
		System.out.println("The bit is: " + bit);
	}
	
	public static boolean intSwitch(int bit) {
		boolean result = false;
		
		switch(bit) {
		case 0:
			result = false;
			break;
		case 1:
			result = true;
			break;
		default:
			System.out.println("This is not a bit!");
		}
		
		return result;
	}
	
	public static void stringSwitch(String color) {
		switch (color) {
		case "red" :
			System.out.println("Stop!");
			break;
		case "yellow" :
			System.out.println("Be cautious, red is coming!");
			break;
		case "green" :
			System.out.println("Wake up, you're blocking" +
					"the traffic, stop texting and go!");
			break;
		default:
			System.out.println("This light is broken," +
					"who put the brown light in!");
		}
	}

}
