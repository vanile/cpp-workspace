/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.interactive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class ReadFile {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("file.txt");
		Scanner sc = new Scanner(file);
		String line = "";
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println(line);
		}
		
		sc.close();
	}

}
