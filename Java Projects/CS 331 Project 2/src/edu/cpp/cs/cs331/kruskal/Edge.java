package edu.cpp.cs.cs331.kruskal;

public class Edge implements Comparable<Edge>{
	
	public int source;
	public int destination;
	public int weight;
	
	public int compareTo(Edge compareEdge) {
		return this.weight-compareEdge.weight;
    }
}
