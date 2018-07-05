import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class UI {
	private Scanner scan;
	private File city;
	private File road;
	private Digraph graph;
	
	public UI() {
		scan = new Scanner(System.in);
		city = new File("city.dat");
		road = new File("road.dat");
		graph = new Digraph();
	}
	
	private void cityParser() throws FileNotFoundException {
		//this function will parse the city.dat file and categorize the information and store the cities in separate City objects
		//and will add the object into the Digraph
		Scanner cityS = new Scanner(city);
		
		String cityN; //city name
		String cityC; //city code
		int population;
		int elevation;
		int number; //city number
		City city;
		
		while (cityS.hasNext()) {
			//parser, creates each City object and adds the object to the cities array in the Digraph
			number = cityS.nextInt();
			cityC = cityS.next();
			cityN = cityS.next();
			if (cityS.hasNext() == true && cityS.hasNextInt() == false) { 
				//checks if it's a two word city name such as "BREA CANYON"
				//by checking if the next thing is an integer, if so then it is the population
				//and is not a 2 word city
				cityN += " " + cityS.next();
			}
			population = cityS.nextInt();
			elevation = cityS.nextInt();
			
			city = new City(number, cityC, cityN, population, elevation);
			graph.addCityCode(cityC);
			graph.addCity(city);	
		}
	}
	
	private void roadParser() throws FileNotFoundException { 
		//This function parses the road.dat file and adds the edges and distance values into the Digraph
		Scanner roadS = new Scanner(road);
		int start;
		int target;
		int distance;
		
		while (roadS.hasNextInt()) {
			//parser
			start = roadS.nextInt() - 1; // -1 because array is city number minus 1
			target = roadS.nextInt() - 1;
			distance = roadS.nextInt();
			
			graph.addEdge(start, target);
			graph.setRoad(start, target, distance);
		}
	}
	
	private void cityQuery(int vertex) {
		//propably won't be used, brings up city information when prompted with city's vertex int on the graph.
		City city = graph.getCities(vertex);
		System.out.println(city.getNumber() + " " + city.getCode() + " "  + city.getName() + " "  + city.getPopulation() + " "  + city.getElevation());
	}
	
	public void menu() {
		//the default menu upon starting of the program.
		//Implemented using a while loop to control of the program will be exited through the switch case
		try {
			//typical try-catch to catch FileNotFound
			cityParser();
			roadParser();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		boolean exit = false;
		String option;
		
		while (exit == false) {
			System.out.println("Q   Query the city information by entering the city code.");
			System.out.println("D   Find the minimum distance between two cities.");
			System.out.println("I   Insert a road by entering two city codes and distance.");
			System.out.println("R   Remove an existing road by entering two city codes.");
			System.out.println("H   Display this message.");
			System.out.println("E   Exit.");
			
			System.out.print("Command? ");
			option = scan.nextLine().toUpperCase();
			
			System.out.println();
			switch (option) { 
			//switch statement that controls what happens when a user enters the command from the main menu
				case "Q":
					String code; //city code
					int i;
					System.out.print("Enter city code: ");
					code = scan.nextLine().toUpperCase();
					//String[] codeArray = graph.getCodeArray();
					if (graph.getCodeArray().contains(code)) {
						for (i = 0; i < 20; i++) {
							if (graph.getCodeArray().get(i).equals(code)) { //checks city code array for if the user's city code matches, then will get the vertex of city
								graph.getCities(i).printCityInfo();
							}
						}
					} else {
						System.out.println("Wrong city code, try again");
					}
					break;
				case "D":
					DijkstraAlgorithm djk = new DijkstraAlgorithm();
					
					System.out.println("Enter starting city code: ");
					String strt = scan.nextLine().toUpperCase();
					int startD = -1;
					
					System.out.println("Enter ending city code: ");
					String en = scan.nextLine().toUpperCase();
					int endD = -1;
					
					if (graph.getCodeArray().contains(strt) && graph.getCodeArray().contains(en)) { //makes sure both cities exist
						for (i = 0; i < 20; i++) {//will return the vertex of the city once its value is found
							if (graph.getCodeArray().get(i).equals(strt)) {
								startD = i;
							}					
						}		
						for (i = 0; i < 20; i++) {//same as above but for the next city		
							if (graph.getCodeArray().get(i).equals(en)) {						
								endD = i;						
							}				
						}
					}
					djk.dijkstra(graph, djk.algorithm(graph, startD), startD, endD);
					break;
				case "I":
					//asks for user's city and distance values
					System.out.println("Enter starting city code: ");
					String start = scan.nextLine().toUpperCase();
					int startN = -1; //index of starting city
					
					System.out.println("Enter ending city code: ");
					String target = scan.nextLine().toUpperCase();
					int targetN = -1;
					
					System.out.println("Enter the distance of road: ");
					int distance = scan.nextInt();
					scan.nextLine();
					
					if (graph.getCodeArray().contains(start) && graph.getCodeArray().contains(target)) { //makes sure both cities exist
						for (i = 0; i < 20; i++) {//will return the vertex of the city once its value is found
							if (graph.getCodeArray().get(i).equals(start)) {
								startN = i;
							}					
						}		
						for (i = 0; i < 20; i++) {//same as above but for the next city		
							if (graph.getCodeArray().get(i).equals(target)) {						
								targetN = i;						
							}				
						}					
						graph.setRoad(startN, targetN, distance);
						System.out.println("You have inserted a road from " + graph.getCities(startN).getName() + " to " + graph.getCities(targetN).getName() + " with a distance of " + distance);
					} else {
						
					}
					break;
				case "R":
					//prompts user for city codes to begin, very similar to case "I" overall.
					System.out.println("Enter starting city code: ");
					String source = scan.nextLine().toUpperCase();
					int sourceN = -1; //will be the vertex of starting city
					
					System.out.println("Enter ending city code: ");
					String end = scan.nextLine().toUpperCase();
					int endN = -1;
					
					if (graph.getCodeArray().contains(source) && graph.getCodeArray().contains(end)) { //makes sure both cities exist
						for (i = 0; i < 20; i++) {
							if (graph.getCodeArray().get(i).equals(source)) {
								sourceN = i;
							}					
						}		
						for (i = 0; i < 20; i++) {						
							if (graph.getCodeArray().get(i).equals(end)) {						
								endN = i;						
							}				
						}					
						graph.removeEdge(sourceN, endN);
						graph.setRoad(sourceN, endN, 0);
					} else {
						System.out.println("The road from " + graph.getCities(sourceN).getName() + " and " + graph.getCities(endN).getName() + " doesn't exist.");
					}
					break;
				case "H":
					//does nothing, will break out of switch statement
					break;
				case "E":
					System.out.println("Exiting...");
					System.exit(0);
				default:
					System.out.println("Invalid menu choice.");
					break;
			}
		}
	}
}
