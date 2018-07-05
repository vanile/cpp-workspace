/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

import java.util.Arrays;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class StringClassExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String theString = "Hello Fools,";
		String theOtherString =	"have a horrible day!";
		
		boolean starts = theString.startsWith("Fool");
		boolean contains = theOtherString.contains("horrible");
		
		String[] tokens = theString.split("\\s");
		int index = theString.indexOf('l');
		String lowerString = theString.toLowerCase();
		String upperString = theString.toUpperCase();
		String concatenated = theString.concat(theOtherString);
		
		int substringIndex = concatenated.indexOf("have");
		
		String theOriginalTheString = concatenated.substring(0, substringIndex);
		String theOriginalTheOtherString = concatenated.substring(substringIndex);
		
		
		
		System.out.println(Arrays.toString(tokens));
		System.out.println(lowerString);
		System.out.println(upperString);
		System.out.println(theString);
		System.out.println(concatenated);
		System.out.println(starts);
		System.out.println(contains);
		
		System.out.println(theOriginalTheString);
		System.out.println(theOriginalTheOtherString);
		
		System.out.println(theOriginalTheString.equals(theString));
	}

}
