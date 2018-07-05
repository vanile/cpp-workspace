import java.util.ArrayList;


class DijkstraAlgorithm {
	//calculates the shortest distance and the routes throughh which the shortest distance is found
	private int infinity;
	private int nullI;
	
	public DijkstraAlgorithm() {
		infinity = 222222222;
		nullI = infinity * -1;
	}
	
	private int indexOfMin(int[] dist, boolean[] q) {
		//finds the index of the minimum value in the distance array
		int u = infinity;
		int v = nullI;
		for (int i = 0; i < dist.length; i++) {
			if (!q[i] && dist[i] < u) {
				v = i;
				u = dist[i];
			}
		}
		return v;
	}
	
	public int[] algorithm(Digraph graph, int source) {
		//generates an array of shortest paths from the source vertex to all other available vertices
		int[][] roads = graph.getRoadsArray();
		
		int[] dist = new int[graph.getCitiesArray().size()];
		int[] prev = new int[dist.length];
		boolean[] queue = new boolean[dist.length];
		
		for (int i = 0; i < graph.getCitiesArray().size(); i++) {
			//set everything in distance array to infinity
			dist[i] = infinity;
		}
		dist[source] = 0; //source to 0
		
		for (int i = 0; i < graph.getCitiesArray().size(); i++) {
			//applying dijkstra's algorithm
			int u = indexOfMin(dist, queue);
			queue[u] = true;
			
			int[] neighbors = graph.neighbors(u);
			for (int l = 0; l < neighbors.length; l++) {
				//compares the paths from one vertex to the sources
				int n = neighbors[l];
				int alt = dist[u] + roads[u][n];
				if (dist[n] > alt) {
					//comparison to find the shortest path from neighboring vertices
					dist[n] = alt;
					prev[n] = u;
				}
			}
		}
		return prev;
	 }
	 
	 public void dijkstra(Digraph graph, int [] prev, int start, int end) {
		 //this method will print out route information as well as total distance
		 int[][] roads = graph.getRoadsArray();
		 ArrayList<City> path = new ArrayList<City>(); //used to add cities for prev to a path array
		 int distance = 0;
		 int u = end;
		 
		 while (u != start) { 
			 path.add (0, graph.getCities(u));
		     u = prev[u];
		 } 
		 path.add (0, graph.getCities(start));
		 //this part prints out the route from starting city to end city
		 System.out.println("Minimum distance between " + path.get(0).getName() + " and " + path.get(path.size() - 1).getName() + " is through route: ");
		 for (int i = 0; i < path.size(); i++) {
			 System.out.print(path.get(i).getName());
			 if (i != path.size() - 1) {
				 System.out.print(", ");
			 }
		 }
		 //this will add the distances between the city routes together
		 for (int i = 0; i < path.size() - 1; i++) {
			int cN1 = path.get(i).getNumber() - 1; //city number - 1 for roads[][] coordinate
			int cN2 = path.get(i + 1).getNumber() - 1; //2nd city number -1 for the second coordinate
			
			distance += roads[cN1][cN2];
		 }
		 System.out.print(" with a distance of " + distance); //final statement concatenation with distance
		 System.out.println();
	}
	 
}
