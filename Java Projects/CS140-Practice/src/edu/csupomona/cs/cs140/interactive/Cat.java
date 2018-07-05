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
 * A raw Java implementation of the Unix 'cat' program.
 * 
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class Cat {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			for (String arg : args) {
				displayContentOnScreen(arg);
			}
		} else {

			Scanner sc = new Scanner(System.in);

			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}

		}
	}

	private static void displayContentOnScreen(String arg)
			throws FileNotFoundException {
		File file = new File(arg);

		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}

		sc.close();
	}

}
