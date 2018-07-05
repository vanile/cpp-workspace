package edu.cpp.cs.cs331.project2;

import java.util.Scanner;

public class Fixer {

	public static void main(String[] args) {
		int[][] newGraph = new int[10][10];
		String dog = "";
		Scanner sc = new Scanner(dog);		
		int x = 0;
		int y = 0;
		while(sc.hasNextInt()) {
			int hello = sc.nextInt();
			if (hello != 0) {
				newGraph[x][y] = hello;
				newGraph[y][x] = hello;
			}
			x++;
			if (x == 10) {
				x = 0;
				y++;
			}
		}
		
		for (int i = 0; i < newGraph.length; i++) {
			for (int j = 0; j < newGraph[i].length; j++) {
				System.out.print(newGraph[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
