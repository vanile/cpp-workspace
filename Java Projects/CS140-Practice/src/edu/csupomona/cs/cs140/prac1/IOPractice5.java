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
import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class IOPractice5 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file= new File("file.txt");
		Scanner sc = new Scanner(file);
		
		int[] numbers = new int[50];
		int j = 0;
		
		for (int i = 0; sc.hasNext(); ++i) {
			numbers[i] = sc.nextInt();
			++j;
		}
		
		sc.close();
		
		PrintWriter pw = new PrintWriter(new File("newFile.txt"));
		
		for (int i = 0; i < j; ++i) {
			pw.print(numbers[i] + " ");
		}
		
		pw.close();
	}

}
