package edu.cpp.cs.cs331.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.drive();
	}
	
	private void drive() {
		int length = 2;
		System.out.println("[insertionSort, mergeSort, quickSort1, quickSort2, quickSort3] in seconds"); //standard output template
		ArrayList<double[]> list = new ArrayList<double[]>();
		
		for (int i = 0; i <= 15; i++) {
			int[] array = fillArray(new int[length]);
			System.out.println("\n" + "Array size: " + length);
			
			if (length <= 32) { //for n up to n = 32, will print out the array
				System.out.println("Unsorted: " + Arrays.toString(array));
			}
			list.add(doArrayAlgorithms(array));
			if (length <= 32) {
				System.out.println("Sorted: " + Arrays.toString(new SortingAlgorithms().quickSort1(array, 0, array.length-1)));
			}
			System.out.println();
			length = length * 2;
		}
	}
	
	private int generateRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private int[] fillArray(int[] array) {
		for (int i = 0; i <= array.length - 1; i++) {
			array[i] = generateRandomInt(0, 65537); //fill array with random integers from 0 to 65536
			//array[i] = i; //fill array sequentially 
		}
		return array;
	}
	
	private double[] doArrayAlgorithms(int[] array) { //calls functions to do all sorting algorithms and store average times in an array
		double[] list = new double[5];
		int iterations = 5; 

		list[0] = insertionAvgTimeSeconds(array.clone(), iterations);
		list[1] = mergeAvgTimeSeconds(array.clone(), iterations);
		list[2] = qsOneAvgTimeSeconds(array.clone(), iterations);
		list[3] = qsTwoAvgTimeSeconds(array.clone(), iterations);
		list[4] = qsThreeAvgTimeSeconds(array.clone(), iterations);
		
		System.out.println(Arrays.toString(list));
		return list;
	}
	
	//bottom 5 are the same design but are functions for each of the five algorithms
	private double insertionAvgTimeSeconds(int[] array, int iterations) {
		SortingAlgorithms sAl = new SortingAlgorithms();
		double timeTotal = 0;
		long timeRun;
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			array = sAl.insertionSort(array); //does algorithm
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		return (timeTotal / iterations) / 1000000000; //(average time) / 1000000000 to convert nanosec to seconds
	}
	
	private double mergeAvgTimeSeconds(int[] array, int iterations) {
		SortingAlgorithms sAl = new SortingAlgorithms();
		double timeTotal = 0;
		long timeRun;
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			array = sAl.mergeSort(array, 0, array.length - 1);
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		return (timeTotal / iterations) / 1000000000;
	}
	
	private double qsOneAvgTimeSeconds(int[] array, int iterations) {
		SortingAlgorithms sAl = new SortingAlgorithms();
		double timeTotal = 0;
		long timeRun;
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			sAl.quickSort1(array, 0, array.length - 1);
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		return (timeTotal / iterations) / 1000000000;
	}
	
	private double qsTwoAvgTimeSeconds(int[] array, int iterations) {
		SortingAlgorithms sAl = new SortingAlgorithms();
		double timeTotal = 0;
		long timeRun;
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			array = sAl.quickSort2(array, 0, array.length - 1);
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		return (timeTotal / iterations) / 1000000000;
	}
	
	private double qsThreeAvgTimeSeconds(int[] array, int iterations) {
		SortingAlgorithms sAl = new SortingAlgorithms();
		double timeTotal = 0;
		long timeRun;
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			array = sAl.quickSort3(array, 0, array.length - 1);
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		return (timeTotal / iterations) / 1000000000;
	}
}
