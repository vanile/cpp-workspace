/**
 * 
 */
package edu.csupomona.cs.cs140.assgmnt3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 CS 140: Introduction to Computer Science
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #3
 *
 * <Extra Credit Assignment>
 *
 * Alexander Kimea
 *   
 */
public class Assgmnt3 {

	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("data.txt");
		Scanner sc = new Scanner(file);
		Scanner s = new Scanner(file);
		
		int count = 0;
		while (sc.hasNextInt()) {
			++count;
			sc.nextLine();
			
		}
		
		//System.out.println("count = " + count);
		
		int[] numbers = new int[count];
		System.out.println("There are " + numbers.length + " numbers in the file.");
		System.out.println("");
		
		int i = 0;
		while(s.hasNextLine()) {
			
			numbers[i] = s.nextInt();
			++i;
		
			}
		sc.close();
		s.close();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Numbers are read from system file: data.txt");
		System.out.println("");
		
		System.out.println("Select an option by typing in the corresponding number.");
		System.out.println("1. Compute and display the average of the list");
		System.out.println("2. Compute and display the number of occurences of a given element in the list");
		System.out.println("3. Compute and display the prime numbers in the list, if any");
		System.out.println("4. Display information in table form");
		System.out.println("5. Save information into a file (results.txt)");
		System.out.println("6. Exit program");
		int option = input.nextInt();
	
		if (option == 1) {
			computeAverage(numbers);
		} else if (option == 2) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter a number to be counted for number of occurences");
			int ocrnum = sc2.nextInt();
			
			displayOccurrences(numbers, ocrnum);
			sc2.close();
		} else if (option == 3) {
			displayPrimes(numbers);
		} else if (option == 4) {
			tableForm(numbers);
			
		} else if (option == 5) {
			tableFormS(numbers);
			
		} else if (option == 6) {
			System.out.println("Now quitting...");
			System.exit(0);
		} else {
			System.out.println("Invalid option");
			
		}
		input.close();
		
	}
		
	/**
	 * computes the average of the array
	 */
	public static int computeAverage(int[] list) {
		int sum = 0;
		for (int i = 0 ; i < list.length ; ++i) {
			sum += list[i];
				
		}
		int average = sum/list.length;
		System.out.println("The average of the list is " + average);
		return 1;
	}
	
	/**
	 * will print out occurences of a number
	 */
	public static int displayOccurrences(int[] list, int x) {
		int counter = 0;
		for (int i : list) {
			if (x == i) {
				++counter;
			}
		}
		System.out.println(x + " appears " + counter + " times");
		return 1;
		}
	
	/**
	 * will return number of occurences of a number
	 */
	public static int displayOccurrences2(int[] list, int x) {
		int counter = 0;
		for (int i : list) {
			if (x == i) {
				++counter;
			}
		}
		//System.out.println(counter);
		return counter;
		}
	
	
	/**
	 * displays the prime numbers of the array
	 */
	public static int[] displayPrimes(int[] list) {
		System.out.println("These are the prime numbers");
		for (int i: list) {
			String result = isPrime(i);
			if (result == "yes") {
				System.out.println(i);
			}
		}
		return null;
		
	} 
	
	/**
	 * prints to console the numbers with their attributes in a table format
	 */
	public static void tableForm(int[] list) throws FileNotFoundException {
		//System.out.printf("number = \t%" + width + ".2f\n", d);
		System.out.printf("Number" + "\t" + "Occurences" + "\t" + "Prime" + "\n");
		for (int i : list) {
			System.out.printf(i + "\t" + displayOccurrences2(list, i) + "\t" + "\t" + isPrime(i) + "\n");
			
		}
		
	}
	
	/**
	 * saves the table format in a .txt file
	 * P.S - for some reason, the table displays correctly in Notepad++, but not windows notepad
	 */
	public static void tableFormS(int[] list) throws FileNotFoundException {
		//System.out.printf("number = \t%" + width + ".2f\n", d);
		System.out.println("Saving to file 'results.txt ...'");
		File file = new File("results.txt");
		PrintWriter pw = new PrintWriter(file);
		pw.printf("Number" + "\t" + "Occurrences" + "\t" + "Prime" + "\n");
		for (int i : list) {
			pw.printf(i + "\t" + displayOccurrences2(list, i) + "\t" + "\t" + isPrime(i) + "\n");
			//pw.printf(i + "\t" + "\t" + displayOccurrences2(list, i) + "\t" + "\t" + "\t" + isPrime(i) + "\n");
		}
		pw.close();
	}
	
	/**
	 * returns string yes or no if number is prime. yeah could have been boolean, too 
	 * lazy to change it
	 */
	public static String isPrime(int x) {
	    
	    if (x % 2 == 0) 
	    	return "no";
	    
	    for(int i = 3 ; i*i <= x ; i += 2) {
	        if(x % i == 0)
	            return "no";
	    }
	    return "yes";
	}
}
