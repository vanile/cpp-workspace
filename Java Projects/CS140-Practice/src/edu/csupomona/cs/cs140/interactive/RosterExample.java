/**
 * 
 */
package edu.csupomona.cs.cs140.interactive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class RosterExample {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		File file = new File("grades.txt");
		PrintWriter pw = new PrintWriter(file);
		
		int option = 1;
		
		while (!(option == 2)) {
			option = topMenu(pw, sc);
		}
		
		pw.close();
	}

	private static void dataGathering(PrintWriter pw, Scanner sc) {
		System.out.println("Please enter the name of the student:");
		String name = sc.nextLine();
		System.out.println("Please enter grade (A-F):");
		String grade = sc.nextLine();

		pw.printf("%-30s %7s\n", name, grade);
	}

	private static int topMenu(PrintWriter pw, Scanner sc) {
		System.out
				.println("Select an option number" +
						"from the menu and press ENTER\n");
		System.out.println("1. Enter student grade");
		System.out.println("2. Quit");
		
		int option = sc.nextInt();
		sc.nextLine();
		
		switch(option) {
		case 1:
			dataGathering(pw, sc);
			break;
		}

		return option;
	}

}
