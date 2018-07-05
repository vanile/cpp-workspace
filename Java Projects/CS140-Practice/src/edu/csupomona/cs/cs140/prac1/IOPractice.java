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
public class IOPractice {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please write your name: ");
		String name = sc.nextLine();
		
		System.out.print("Please write your age: ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Please write your address: ");
		String address = sc.nextLine();
		
		System.out.println("Your name is: " + name);
		System.out.println("Your age is: " + age);
		System.out.println("Your address is: " + address);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
