/**
 * 
 */
package edu.csupomona.cs.cs140.notepad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author ahkimea
 *
 */
public class NotepadProg {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Notepad Program v1");
		System.out.println("Press 1 to create new note");
		System.out.println("Press 2 to view existing notes");
		System.out.println("Press 3 to quit");
		Scanner progOptions = new Scanner(System.in);
		int progInput = progOptions.nextInt();
		
		if (progInput == 1) {
			createNote();
		} else if (progInput == 2) {
			viewExistingNotes();
		} else if (progInput == 3) {
			System.out.println("Now quitting...");
			System.exit(0);
		} else {
			System.out.println("Invalid input");
		}
			
		

	}
	public static void createNote() throws FileNotFoundException {
		
		System.out.println("You are now creating a new note");
		System.out.println("Save file as: " + ".txt");
		Scanner sc = new Scanner(System.in);
		String savedNote = sc.nextLine();
		PrintWriter pw = new PrintWriter(savedNote + ".txt");
		File fileName = new File(savedNote);
		pw.close();
		
		System.out.println("Enter text to be saved onto the note");
		System.out.println("Then press ctrl + z to finish and save");
		PrintWriter pw2 = new PrintWriter(fileName + ".txt");
		String text = "";
		while (sc.hasNextLine()) {
			text += sc.nextLine() + "\n";
		}
		pw2.print(text);
		pw2.close();
		
	}
	public static void viewExistingNotes() throws FileNotFoundException {
		File fileList = new File("fileList.txt");
		Scanner sc = new Scanner(fileList);
		Scanner sc2 = new Scanner(System.in);
		String result = "";
		
		while (sc.hasNextLine()) {
			result += sc.nextLine() + "\n";
		}
		System.out.println("These are the available files in the directory");
		System.out.println(result);
		
		
		
		System.out.println("Type filename in with extension to open");
		
		String option = sc2.nextLine();
		
		if (option.equals("file1.txt")) {
			File file1 = new File("file1.txt");
			Scanner f1 = new Scanner (file1);
			String s1 = "";
			while (f1.hasNextLine()) {
				s1 += f1.nextLine() + "\n";
			}
			System.out.println("------------------------------------------");
			System.out.println(s1);
			f1.close();
		} else if (option.equals("file2.txt")) {
			File file2 = new File("file2.txt");
			Scanner f2 = new Scanner (file2);
			String s2 = "";
			while (f2.hasNextLine()) {
				s2 += f2.nextLine() + "\n";
			}
			System.out.println("------------------------------------------");
			System.out.println(s2);
			f2.close();
		} else if (option.equals("file3.txt")) {
			File file3 = new File("file3.txt");
			Scanner f3 = new Scanner (file3);
			String s3 = "";
			while (f3.hasNextLine()) {
				s3 += f3.nextLine() + "\n";
			}
			System.out.println("------------------------------------------");
			System.out.println(s3);
			f3.close();

		} else {
			System.out.println("File does not exist in directory");
		}
			
		
	}
}
