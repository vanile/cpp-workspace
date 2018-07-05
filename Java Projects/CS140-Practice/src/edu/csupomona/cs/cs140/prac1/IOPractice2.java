/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IOPractice2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] numbers = new int[100];
		
		System.out.println("Input a sequence of numbers:");
		
		int i = 0;
		
		while(sc.hasNext()) {
			numbers[i] = sc.nextInt();
			++i;
		}
		
		System.out.println("These are the numbers you entered:");
		
		for (int j = 0; j < i; ++j) {
			System.out.print(numbers[j] + " ");
		}
	}

}
