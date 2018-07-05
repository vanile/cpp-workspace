import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class UI {
	//this is the User Interface class it controls all text that will be displayed to the user
	// as well as takes input from the user
	private Scanner menu;
	private BST bt;
	
	public UI () {
		menu = new Scanner(System.in);
		bt = new BST();
	}

	public void menu() { 
		//the main menu that is based on the while loop and will repeat the commands until the user chooses to exit
		Scanner inputValue = new Scanner(System.in);
		int value;
		boolean exit = false;
		while (exit == false) {
			String option;
		
			System.out.println("Command?");
			System.out.println("I    Insert a value");
			System.out.println("D    Delete a value");
			System.out.println("P    Find predecessor");
			System.out.println("S    Find successor");
			System.out.println("E    Exit program");
			System.out.println("H    H Display this message");
			
			option = menu.nextLine().toUpperCase();
			switch (option) { 
			//switch statement that controls what happens when a user enters the command from the main menu
				case "I":
					System.out.println("Enter value: ");
					value = inputValue.nextInt();
					
					if (bt.findElement(value, bt.getRoot()) != null) {
						System.out.println(value + " already exists, value ignored");
					} else {
						bt.add(value);
					}
					
					System.out.print("In-order: ");
					bt.printInOrder(bt.getRoot());
					break;
				case "D":
					System.out.println("Enter value: ");
					value = inputValue.nextInt();
					if (bt.findElement(value, bt.getRoot()) == null) {
						System.out.println(value + " does not exist");
					} else {
						bt.remove(value, bt.getRoot());
					}
					
					
					System.out.print("In-order: ");
					bt.printInOrder(bt.getRoot());
					break;
				case "P":
					System.out.println("Enter value: ");
					value = inputValue.nextInt();
					
					System.out.println(bt.findPredecessor(value));
					break;
				case "S":
					System.out.println("Enter value: ");
					value = inputValue.nextInt();
					
					System.out.println(bt.findSuccessor(value));
					break;
				case "E":
					System.out.println("Thank you for using my program!");
					exit = true;
				case "H":
					break;
				default: 
					System.out.println("--Invalid command, try again--");
					break;
			}
			System.out.println();
		}
	}
	
	public ArrayList<Integer> getInitialSequence() { 
		//this method will ask for the user's sequence of integers and convert it to an int array
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the initial sequence of int values");
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		String seq = sc.nextLine();
		String[] list = seq.split("\\s+");
		
		for (int i = 0; i < list.length; i++) {
			intArray.add(Integer.parseInt(list[i]));
		}
		System.out.println(intArray.toString()); //this checks for duplicates and removes the last duplicate from the array
		for (int i = 0; i < intArray.size(); i++) {
			for (int j = i + 1; j < intArray.size(); j++) {
				if (intArray.get(i) == intArray.get(j)) {
					intArray.remove(j);
					
					
				}
			}
		}
		return intArray;
	}
	
	public void addSequenceToTree(ArrayList<Integer> array) { 
		//this method will add the generated array from user's initial sequence of integers
		for (int i = 0; i < array.size(); i++) {
			bt.add(array.get(i));
		}
		bt.printTraversals(bt.getRoot());
	}
	
}