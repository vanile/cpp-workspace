/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2014 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.notepad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A simple class for a notepad program (a simple
 * application for creating notes and save them to the
 * file system). The saving system is rather unsophisticated,
 * but it gets the job done. Notes are saved on files
 * with the extension *.note, and the list of notes
 * are kept in a file named notes.data. Notes cannot
 * be deleted once they are created.
 * 
 * @author Edwin Rodr&iacute;guez
 *
 */
public class NotePadApp {

	private static final String NOTES_FILE = "notes.data";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static void createNote(String noteContent,
				String fileName) throws FileNotFoundException {
		addNoteToList(fileName);
		File newNote = new File(fileName + ".note");
		PrintWriter pw = new PrintWriter(newNote);
		pw.print(noteContent);
		pw.close();
	}
	
	private static String readNote(String fileName)
						throws FileNotFoundException {
		String result = "";
		File exNote = new File(fileName + ".note");
		Scanner sc = new Scanner(exNote);
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + "\n";
		}
		
		sc.close();
		return result;
	}

	private static void addNoteToList(String fileName)
			throws FileNotFoundException {
		String oldList = readOldList();
		String newList = oldList + fileName + "\n";
		saveNewList(newList);
	}

	private static void saveNewList(String newList)
			throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(NOTES_FILE));
		pw.print(newList);
		pw.close();
	}

	private static String readOldList()
			throws FileNotFoundException {
		String result = "";
		Scanner sc = new Scanner(new File(NOTES_FILE));
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + "\n";
		}
		
		sc.close();
		return result;
	}

}
