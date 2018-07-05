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
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A raw Java approximation of the Unix 'cp' program
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class Copy {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 2) {
			File file1 = new File(args[0]);
			Scanner sc = new Scanner(file1);

			File file2 = new File(args[1]);
			PrintWriter pw = new PrintWriter(file2);

			String line = "";

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				pw.println(line);
			}

			sc.close();
			pw.close();
		} else {
			System.out.println("2 Arguments expected!");
		}
	}

}
