/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.interactive;

import java.util.Arrays;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IntArrayPopulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to array populator v0.1!");
		System.out.println("Please, enter size of array:");
		
		int size = sc.nextInt();
		
		int[] theArray = new int[size];
		
		for (int i = 0; i < theArray.length; ++i) {
			System.out.println("Enter the value for position " + i);
			int value = sc.nextInt();
			theArray[i] = value;
		}
		
		// Here we're going to pretty-print the array
		
		System.out.print("[");
		
		for (int i = 0; i < theArray.length; ++i) {
			System.out.print(theArray[i]);
			
			if (i != (theArray.length - 1)) {
				System.out.print(" , ");
			}
		}
		
		System.out.println("]");
	}

}
