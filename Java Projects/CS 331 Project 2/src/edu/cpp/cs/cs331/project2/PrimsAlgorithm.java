package edu.cpp.cs.cs331.project2;

import java.util.*;
import java.lang.*;
import java.io.*;
 
class PrimsAlgorithm {
	
    private int n;
    private int[]T;
    private int[][] cost;
 
    public void doAlgorithm(int cost[][]) {
    	this.n = cost.length;
        int T[] = new int[n];
        int key[] = new int [n];
        Boolean Near[] = new Boolean[n];
 
        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            Near[i] = false;
        }
        
        key[0] = 0;
        T[0] = -1;
 
        for (int i = 0; i < n - 1; i++) {
            int u = minCost(key, Near);
            Near[u] = true;
 
            for (int k = 0; k < n; k++)
                if (cost[u][k]!=0 && Near[k] == false && cost[u][k] <  key[k]) {
                    T[k]  = u;
                    key[k] = cost[u][k];
                }
        }
        this.T = T;
        this.cost = cost;
    }
    
    private int minCost(int key[], Boolean near[]) {
        int min = Integer.MAX_VALUE;
        int index = 0; //////
 
        for (int v = 0; v < n; v++)
            if (near[v] == false && key[v] < min) {
                min = key[v];
                index = v;
            }
 
        return index;
    }
    
    public void displayTree() {
        int[][] nT = new int[n][n];
        
        System.out.println("Prim Tree");
        
        for (int i = 1; i < n; i++) {
            nT[T[i]][i] = cost[i][T[i]];
            nT[i][T[i]] = cost[i][T[i]];
        }
        
        for (int i = 0; i < nT.length; i++) {
        	for (int j = 0; j < nT.length; j++) {
        		System.out.print(nT[i][j] + "\t");
        	}
        	System.out.println();
        }
    }
}