package edu.cpp.cs.cs331.kruskal;

import java.util.Arrays;

public class KruskalsAlgorithm {

	private int n;
	private int e;
	public Edge A[];
	private Edge T[];
	private long timeRun;
	
	public KruskalsAlgorithm(int n, int e) {
		this.n = n;
		this.e = e;
		A = new Edge[e];
		for (int i = 0; i < e; i++) {
			A[i] = new Edge();
		}
	}
	
	public void doAlgorithm() {
		Edge T[] = new Edge[n];
		long timeRun;
		int i = 0;
		int j = 0;
		
		for (i = 0; i < n; i++) {
			T[i] = new Edge();
		}
		
		Arrays.sort(A);
		long timeStart = System.nanoTime();

		Set sets[] = new Set[n];
		for (i = 0; i < n; i++) {
			sets[i] = new Set();
		}
		
		for (int k = 0; k < n; k++) {
			sets[k].parent = k;
			sets[k].rank = 0;
		}
		
		i = 0;
		while (j < n - 1) {
			Edge smallestEdge = new Edge();
			if (i == A.length - 1 || A.length == 0) {
				break;
			}
			smallestEdge = A[i++];
			//System.out.println("j: " + j + " n: " + n + " i:" + i + " A.length: " + A.length);
			int a = find(sets, smallestEdge.source);
			int b = find(sets, smallestEdge.destination);
			
			if (a != b) {
				T[j++] = smallestEdge;
				merge(sets, a, b);
			} 
		}
		long timeEnd = System.nanoTime();
		timeRun = (timeEnd - timeStart);
		this.timeRun = timeRun;
		
		this.T = T;
	}
	
	public void displayTree() {
		System.out.println("Kruskal Tree");
		int[][] display = new int[n][n];
		for (int i = 0; i < T.length; i++) {
			int source = T[i].source;
			int destination = T[i].destination;
			int weight = T[i].weight;
			display[source][destination] = weight;
			display[destination][source] = weight;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(display[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public long getTimeRun() {
		return timeRun;
	}
	
	public int find(Set sets[], int i) {
		if (sets[i].parent != i) {
            sets[i].parent = find(sets, sets[i].parent);
		}
        return sets[i].parent;
	}
	
	public void merge(Set sets[], int a, int b) {
		int aroot = find(sets, a);
        int broot = find(sets, b);
 
        if (sets[aroot].rank < sets[broot].rank) {
            sets[aroot].parent = broot;
        } else if (sets[aroot].rank > sets[broot].rank) {
            sets[broot].parent = aroot;
        } else {
            sets[broot].parent = aroot;
            sets[aroot].rank++;
        }
	}
}
