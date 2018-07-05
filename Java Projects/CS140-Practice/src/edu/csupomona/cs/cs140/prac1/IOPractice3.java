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
import java.util.Scanner;


/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IOPractice3 {
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("file.txt");
		Scanner sc = new Scanner(file);
		
		int[] numbers = new int[100];
		
		int i = 0;
		
		while(sc.hasNext()) {
			numbers[i] = sc.nextInt();
			++i;
		}
		
		sc.close();
		
		System.out.println("These are the numbers in the file:");
		
		for (int j = 0; j < i; ++j) {
			System.out.print(numbers[j] + " ");
		}		
	}


}
