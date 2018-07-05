package edu.cpp.cs.cs331.project2;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import edu.cpp.cs.cs331.kruskal.KruskalsAlgorithm;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		//test();
		drive();
	}
	
	private void test() {
		int[][] cost = new int[6][6];
		for (int i = 0; i < cost.length; i++) {
			cost[i][i] = 0;
		}
		cost[0][1] = 16;
		cost[0][4] = 19;
		cost[0][5] = 21;
		cost[1][0] = 16;
		cost[1][2] = 5;
		cost[1][5] = 11;
		cost[1][3] = 6;
		cost[2][1] = 5;
		cost[2][3] = 10;
		cost[3][2] = 10;
		cost[3][1] = 6;
		cost[3][5] = 14;
		cost[3][4] = 18;
		cost[4][5] = 33;
		cost[4][3] = 18;
		cost[4][0] = 19;
		cost[5][0] = 21;
		cost[5][1] = 11;
		cost[5][3] = 14;
		cost[5][4] = 33;
		
		cost = new int[][] 
			{{0, 2, 0, 6, 0},
             {2, 0, 3, 8, 5},
             {0, 3, 0, 0, 7},
             {6, 8, 0, 0, 9},
             {0, 5, 7, 9, 0},
        };
        
        printMatrix(cost);
		PrimsAlgorithm t = new PrimsAlgorithm();
		t.doAlgorithm(cost);
		t.displayTree();
		System.out.println();
		
		///////
		int numEdges = countEdges(cost);
		KruskalsAlgorithm kalg = new KruskalsAlgorithm(cost.length, numEdges);
		kruskalSetEdges(kalg, cost);
		kalg.doAlgorithm();
		kalg.displayTree();
	}
	
	private void drive() {
		int[] vertices = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
		
		for (int i = 0; i < vertices.length; i++) {
			//generate number of edges for each vertex
			int E = (vertices[i] * (vertices[i] - 1)) / 2;
			int[] e = new int[5];
			double inc = 0.2;
			for (int k = 0; k < 5; k++) {
				double val = inc * E;
				e[k] = (int) val;
				inc += 0.2;
			}
			
			double[] kTimes = new double[5];
			double[] pTimes = new double[5];
			
			inc = 0.2; //increment E
			
			//this will do the algorithms on all edge sizes
			for (int k = 0; k < e.length; k++) {
				int[][] A = fillMatrix(vertices[i], e[k]); //generate new Cost from vertices and edge information
				if (i == 0) {
					System.out.println("---------------");
					System.out.println("Matrix " + inc + "E");
					printMatrix(A);
					System.out.println("---------------");
					inc += 0.2;
				}
				
				kTimes[k] = kruskalsAverageTimeSeconds(A, i);
				pTimes[k] = primsAverageTimeSeconds(A, i);
			}
				
			System.out.println("Vertices: " + vertices[i]);
			System.out.println("Matrix Times (seconds) [0.2E, 0.4E, 0.6E, 0.8E, E]");
			System.out.println("Prims: " + Arrays.toString(pTimes));
			System.out.println("Kruskals: " + Arrays.toString(kTimes));
			System.out.println();
		}		
	}
	
	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private double kruskalsAverageTimeSeconds(int[][] cost, int j) {
		int numEdges = countEdges(cost);
		KruskalsAlgorithm kalg = new KruskalsAlgorithm(cost.length, numEdges);
		kruskalSetEdges(kalg, cost);
		
		int iterations = 5;
		double timeTotal = 0;
		long timeRun;
		
		for (int i = 1; i <= iterations; i++) {		
			kalg.doAlgorithm();
	
			timeRun = kalg.getTimeRun();
			timeTotal = timeTotal + timeRun;
		}
		
		if (j == 0) {
			kalg.displayTree();
		}
		return (timeTotal / iterations) / 1000000000; //(average time) / 1000000000 to convert nanosec to seconds
	}
	
	private void kruskalSetEdges(KruskalsAlgorithm kalg, int[][] cost) {
		int edge = 0;
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].length; j++) {
				if (cost[i][j] != 0) {
					kalg.A[edge].source = i;
					kalg.A[edge].destination = j;
					kalg.A[edge].weight = cost[i][j];
					edge++;
				}
			}
		}
	}
	
	private int countEdges(int[][] cost) {
		int numEdges = 0;
		for (int i= 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				if (cost[i][j] != 0) {
					numEdges++;
				}
			}
		}
		return numEdges;
	}
	
	private double primsAverageTimeSeconds(int[][] cost, int j) {
		PrimsAlgorithm palg = new PrimsAlgorithm();
		int iterations = 5;
		double timeTotal = 0;
		long timeRun;
		
		for (int i = 1; i <= iterations; i++) {
			long timeStart = System.nanoTime();
			
			palg.doAlgorithm(cost);
			
			long timeEnd = System.nanoTime();
			
			timeRun = (timeEnd - timeStart);
			timeTotal = timeTotal + timeRun;
		}
		if (j == 0) {
			palg.displayTree();
		}
		return (timeTotal / iterations) / 1000000000; //(average time) / 1000000000 to convert nanosec to seconds
	}
	
	private int generateRandomInt() {
		return ThreadLocalRandom.current().nextInt(1, 1000);
	}
	
	/**
	 * 
	 * Generate a random Matrix/Cost graph given n vertices and e edges
	 * 
	 */
	private int[][] fillMatrix(int n, int e) {
		int[][] A = new int[n][n];
		int numEdges = new Integer(e);
		
		while (numEdges != 0) {
			int source = ThreadLocalRandom.current().nextInt(0, n);
			int destination = ThreadLocalRandom.current().nextInt(0, n);
			
			while (destination == source) {
				// || destination == source + 1
				destination = ThreadLocalRandom.current().nextInt(0, n);
			}
			
			if (A[source][destination] == 0 && A[destination][source] == 0) {
				int rand = generateRandomInt();
				A[source][destination] = rand;
				A[destination][source] = rand;
				numEdges--;
			} 
		}
		
		for (int i = 0; i < n; i++) {
			A[i][i] = 0;
		}
		return A;
	}
}
