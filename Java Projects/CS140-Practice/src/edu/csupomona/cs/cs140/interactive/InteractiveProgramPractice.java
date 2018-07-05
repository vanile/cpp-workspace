/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.interactive;

import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class InteractiveProgramPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Registry Program!\n");

		startMenus(sc);

	}

	private static void startMenus(Scanner sc) {
		while (true) {
			System.out.println("(Enter option # and press ENTER)\n");

			System.out.println("1. Enter Information");
			System.out.println("2. Quit");

			int option = sc.nextInt();

			sc.nextLine();

			switch (option) {
			case 1:
				infoMenu(sc);
				System.out.println("Entering info-gathering stage...");
				break;
			case 2:
				System.exit(0);
			default:
				// Go back to the top!
				System.out.println("Unrecognized Option!\n");
			}

		}
	}

	private static void infoMenu(Scanner sc) {
		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		System.out.print("Enter age: ");
		int age = sc.nextInt();

		System.out.println();

		while (true) {

			System.out.println("(Enter option # and press ENTER)\n");

			System.out.println("1. Display full record");
			System.out.println("2. Display name only");
			System.out.println("3. Display age only");
			System.out.println("4. Quit");

			int option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("Name: " + name);
				System.out.println("Age: " + age);
				System.out.println();
				break;
			case 2:
				System.out.println("Name: " + name);
				System.out.println();
				break;
			case 3:
				System.out.println("Age: " + age);
				System.out.println();
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Unrecognized Option!\n");
			}

		}

	}

}
