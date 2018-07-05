/**
 * 
 */
package edu.csupomona.cs.cs140;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author thefurryman
 *
 */
public class InputToFile {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("hehe.txt");
		//Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(file);
		
		System.out.println("Enter an input");
		//String input = sc.nextLine();
		pw.print("https://www.youtube.com/watch?v=K-SmfybCbsY&");
		
		
		
		
		//sc.close();
		pw.close();

	}

}
