/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.examples;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class Arrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(avg2(new int[] {3, 4, 5, 6, 13}));
	}
	
	/**
	 * Computes the sum of the elements of the
	 * argument array.
	 * 
	 * @param numbers
	 * @return
	 */
	public static int arraySum(int[] numbers) {
		int sum = 0;
		
		for(int x : numbers) {
			sum += x;
		}
		
		return sum;
	}
	
	public static int avg2(int[] numbers) {
		return arraySum(numbers) / numbers.length;
	}
	
	public static void indexOutOfBounds() {
		int[] xs = {0, 1, 2, 3};
		
		System.out.println(xs[4]);
	}
	
	/**
	 * Prints the contents of the array of integers,
	 * by displaying one number per line.
	 * 
	 * @param numbers the argument array to be displayed.
	 */
	public static void printElements(int[] numbers) {
		for(int x : numbers) {
			System.out.println(x);
		}
	}
	
	/**
	 * Computes the average of the elements contained in
	 * the integer array passed as argument.
	 * 
	 * @param numbers the arguments array
	 * @return the average of the elements in the array
	 */
	public static int avg(int[] numbers) {
		int sum = 0;
		
		for(int x : numbers)
			sum += x;
		
		return sum/numbers.length;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static int max(int[] numbers) {
		int max = numbers[0];
		
		for(int i = 1; i < numbers.length; ++i) {
			if (max < numbers[i])
				max = numbers[i];
		}
		
		return max;
	}
	
	/**
	 * This method returns the index in the array that contains
	 * the element <code>x</code>.
	 * 
	 * @param numbers the argument array
	 * @param x the element to be found
	 * @return the index that contains the argument <code>x</code>, or
	 * <code>-1</code> if the element doesn't happen in the array.
	 */
	public static int findIndex(int[] numbers, int x) {
		int index = -1; // -1 is the default value.
		
		/*
		 * The purpose of this for loop is to find  the index
		 * that contains the element x.
		 */
		for (int i = 0; i < numbers.length; ++i) {
			if (numbers[i] == x) { // The element is found when this succeeds
				index = i;
				break;
			}
		}
		
		return index;
	}

}
