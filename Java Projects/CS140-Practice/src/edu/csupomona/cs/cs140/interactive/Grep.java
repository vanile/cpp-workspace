/**
 * 
 */
package edu.csupomona.cs.cs140.interactive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class Grep {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 2) {
			String keyword = args[0];
			Scanner sc = new Scanner(new File(args[1]));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				if (line.contains(keyword)) {
					System.out.println(line);
				}
			}
		} else {
			System.out.println("Wrong number of arguments!");
		}
	}

}
