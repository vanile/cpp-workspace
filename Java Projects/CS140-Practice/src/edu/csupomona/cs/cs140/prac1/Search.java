/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

/**
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * Performs linear search to find element <code>x</code> in the given array.
	 * 
	 * @param list
	 * @param x
	 * @return The index that contains <code>x</code>, or or <code>-1</code>
	 *         otherwise.
	 */
	public static int linearSearch(int[] list, int x) {
		int index = -1;

		for (int i = 0; i < list.length; ++i) {
			if (x == list[i]) {
				index = i;
				break;
			}
		}

		return index;
	}

	public static int binarySearch(int[] list, int x) {
		int low = 0;
		int high = list.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			int midVal = list[mid];

			if (midVal < x)
				low = mid + 1;
			else if (midVal > x)
				high = mid - 1;
			else
				return mid;
		}
		return -(low + 1);
	}
}
