import java.util.ArrayList;


class Digraph {
	private boolean[][] edges; //2D array to check if a road/edge actually exists
    private ArrayList<String> cityCode; 
    //I found an ArrayList easier to work with because I can check if the array contains something without looping all of the time
	private ArrayList<City> cities; //ArrayList of all cities
	private int[][] roads; //2D array for actual distance between two cities
	
	public Digraph() {
		edges = new boolean[20][20];
		cityCode = new ArrayList<String>();
		cities = new ArrayList<City>();
		roads = new int[20][20];
	}
	
	public ArrayList<String> getCodeArray() {
		//used a lot to get the city codes when the user enters city codes for the various menu options
		return cityCode;
	}
	
	public ArrayList<City> getCitiesArray() {
		return cities;
	}
	
	public int[][] getRoadsArray() {
		return roads;
	}
	
	public void addCityCode(String code) {
		cityCode.add(code);
	}

	public void setRoad(int source, int target, int distance) {
		//add a distance between 2 points
		roads[source][target] = distance;
	}

	public void addEdge(int source, int target) {
		//add an actual edge between 2 points, not distance
		edges[source][target] = true;
	}
	
	public String getCode(int vertex) {
		return cityCode.get(vertex);
	}
	
	public boolean isEdge(int source, int target) {
		return edges[source][target];	
	}
	
	public int[] neighbors(int vertex) {
		//returns vertices of the neighbors of the source vertex
        int i;
        int count = 0;
        int[] answer;

        for (i = 0; i < edges.length; i++) {
            if (edges[vertex][i])
                count++;
        }
        answer = new int[count];
        count = 0;
        for (i = 0; i < edges.length; i++) {
            if (edges[vertex][i])
                answer[count++] = i;
        }
        return answer;
    }
	
	public void removeEdge(int source, int target) {
		edges[source][target] = false;
	}
	
	public void setCode(int vertex, String newLabel) {
		cityCode.set(vertex, newLabel);
	}
	
	public void setCities(int vertex, City city) {
		cities.set(vertex, city);
	}
	
	public void addCity(City city) {
		cities.add(city);
	}
	
	public City getCities(int vertex) {
		//returns City object at vertex i in the cities array
		return cities.get(vertex);
	}
	
	public int size() {
		return cityCode.size();
	}
}
