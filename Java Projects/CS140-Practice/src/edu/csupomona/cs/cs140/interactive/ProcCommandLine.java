/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.interactive;

/**
 * @author apoloimagod
 * 
 */
public class ProcCommandLine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Showing all command-line arguments");

		System.out.println(args.length + " arguments passed!");

		if (args.length > 0) {
			switch(args[0]) {
			case "arg1":
				System.out.println("Argument 1!");
				break;
			case "arg2":
				System.out.println("Argument 2!");
				break;
			case "arg3":
				System.out.println("Argument 3!");
				break;
			default:
				System.out.println("No argument match!");
			}

		} else {
			System.out.println("No arguments passed!");
		}
		
		rePrintArgs(args);
	}
	
	public static void rePrintArgs(String[] args) {
		for (String arg : args) {
			System.out.println(arg);
		}
	}

}
