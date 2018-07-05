package edu.cpp.cs.cs141.class_project;

public class Grid {

	public Grid() {
		String[][] grid = new String[9][9];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				
				grid[i][j] = "X";
				System.out.print("[" + grid[i][j] + "]");
				
			}
			
			System.out.println();
		}
	}
}
