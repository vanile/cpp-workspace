package edu.cpp.cs.cs240.assignment3;

import java.io.*;
import java.util.*;

public class Project3 {

	public static void main(String[] args) throws IOException {
	    if (args.length < 1) {
	        System.out.println("Error: Directory name is missing");
	        System.out.println("Usage: java scoreProcess directory_name");
	        return;
	    }
	    File directory = new File(args[0]); // args[0] contains the directory name
	    File[] files = directory.listFiles(); // get the list of files from that directory

	    File file;
	    Scanner input;

	    // process the arguments stores in args
	    for (int i = 0; i < files.length; i++) {
	    	input = new Scanner(files[i]);

	    	System.out.println("\nCurrent file name: " + files[i].getName());
	      
	    	// no error checking done here, add your own
	    	String name;
	    	Double score;
	    	while (input.hasNext()) {
	    		name = "";
	      	
	      		while (!input.hasNextDouble()) {
	      			name += input.next() + " ";
	      		}
	      	
	      	score = new Double(input.next());
	      	System.out.println("Name: " + name + " Score: " + score);
	    	}
	    }
	}

}
