package edu.cpp.cs.cs240.assignment3;

import java.io.*;
import java.util.*;

public class Project3 {

    static HashTable<String, ArrayList<Double>> ht = new HashTable<String, ArrayList<Double>>();
	
	public static void main(String[] args) throws IOException {
		Project3 p3 = new Project3();
		
		System.out.println("Project 3: Score Processor");
		File directory = new File(enterDirectory());
		
		if (!directory.exists()) {
			throw new RuntimeException("directory does not exist");
		}
	    File[] files = directory.listFiles();
	    
	    Scanner input;
	    String name = "";
	    Double score = 0.0;
	    
	    System.out.println("Processing data...");
	    if (files.length == 0) {
	    	throw new RuntimeException("directory is empty");
	    }
	    
	    
	    for (int i = 0; i < files.length; i++) {
	    	input = new Scanner(files[i]);
	    	while (input.hasNext()) {
	    		name = "";
	      		while (!input.hasNextDouble()) {
	      			
	      			name += input.next(); 
	      			name = name.toLowerCase();
	      			name = name.replaceAll("\\p{Z}","");
	      		}
	      	
	      	score = new Double(input.next());
	      	p3.newScore(name, score);
	    	}
	    	
	    }
	    
	    boolean flag = true;
	    while (flag == true) {
	    	int choice = userChoice();
	    	if (choice == 1) {
	    		p3.searchName(enterTeam());
	    	} else if (choice == 2) {
	    		flag = false;
	    		System.out.println("exiting...");
	    	} else {
	    		System.out.println("Invalid option, try again");
	    	}
	    }
	    
	   
	}
	public static int userChoice () {
		Scanner sc = new Scanner(System.in);
		System.out.println("[Menu]");
		System.out.println();
		
		System.out.println("Choose one of the following: ");
		System.out.println("1: to enter a team name");
		System.out.println("2: to quit");
		int choice = sc.nextInt();
		
		return choice;
	}
	public static String enterTeam() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a team name: ");
		String team = sc.nextLine();
		
		String format = team.toLowerCase();
		format = format.replaceAll("\\p{Z}","");
		System.out.println(team + " ");
		
		return format;
	}
	
	public static String enterDirectory() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter directory to be scanned: ");
		String dir = sc.nextLine();
		
		return dir;
	}
	
	public void newScore(String name, Double score) {
		ArrayList<Double> scores = ht.get(name);
		if (scores == null) {
			scores = new ArrayList<Double>();
		}
		scores.add(score);
		
		ht.put(name, scores);
	}
	
	public void searchName(String name) {
		ArrayList<Double> scores = ht.get(name);
		
		if (ht.get(name) == null) {
			throw new RuntimeException("team does not exist");
		}
		
		int scoreSize = scores.size();
		double sum = 0;
		double avg;
		
		for (int i = 0; i < scoreSize; i++) {
			sum += scores.get(i);
		}
		avg = sum / scoreSize;
		System.out.println("");
		System.out.println(name + ": " + "Avg: " + avg + " #Scores: " + scoreSize);
		System.out.println("");
		
	}
	
}
