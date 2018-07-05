/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

import java.util.Arrays;
import java.util.Random;

/**
 * Sorting algorithms and binary search.
 * 
 * @author Edwin Rodr&iacute;guez
 *
 */
public class Sorting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list = new int[10000000];
		Random rand = new Random();
		
		for (int i = 0; i < list.length; ++i) {
			list[i] = rand.nextInt(1000000000);
		}
		
		long initTime = System.currentTimeMillis();
		Arrays.sort(list);
		long finalTime = System.currentTimeMillis();
		
		//System.out.println(Arrays.toString(list));
		
		System.out.println(finalTime - initTime);
	}
	
	public static int binarySearch(int[] list, int x) {
		int low = 0;
		int high = list.length - 1;
		
		while (true) {
			int mid = (low + high) / 2;

			if (low >= high) {
				if (list[low] == x)
					return low;
				else
					break;
			}
			
			if (list[mid] == x) {
				return mid;
			} else if (list[mid] < x) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}			
		}
		
		return -1;
	}
	
	public static boolean naiveSearch(int[] list, int x) {
	
		for (int y : list) {
			if ( x == y)
				return true;
		}
		
		return false;
	}
	
	public static int[] insertionSort(int[] list){
		for (int i = 1; i < list.length; ++i) {
			for (int j = i; j >= 1; --j) {
				if (list[j] < list[j-1]) {
					swap(list, j, j-1);
				} else {
					break;
				}
			}
		}
		
		return list;
	}

	public static String[] stringInsertionSort(String[] list){
		for (int i = 1; i < list.length; ++i) {
			for (int j = i; j >= 1; --j) {
				if (list[j].compareTo(list[j-1]) < 0) {
					stringSwap(list, j, j-1);
				} else {
					break;
				}
			}
		}
		
		return list;
	}

	public static int[] bubbleSort(int[] list) {
		boolean swapped;
		
		do {
			swapped = false;
			
			for (int i = 0; i < list.length-1; ++i) {
				if (list[i] > list[i+1]) {
					swap(list, i, i+1);
					swapped = true;
				}
			}
		} while (swapped);
		
		return list;
	}
	
	public static int[] selectionSort(int[] list) {
		for (int i = 0; i < list.length-1; ++i) {
			int lowestIndex = i;
			
			for (int j = i+1; j < list.length; ++j) {
				if (list[lowestIndex] > list[j]) {
					lowestIndex = j;
				}
			}
			
			swap(list, lowestIndex, i);
		}
		
		return list;
	}

	private static void swap(int[] list, int i, int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	private static void stringSwap(String[] list, int i, int j) {
		String temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

}
