import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class UI {
	//This class controls all functions pertaining to output and input of text through the standard output and input.
	//The class also controls the menu and what functions of the program will be executed depending on the input
	// of the user.
	private Scanner scan;
	
	public UI () {
		scan = new Scanner(System.in);
	}
	
	public void menu() {
		//the default menu upon starting of the program.
		//Implemented using a while loop to control of the program will be exited through the switch case
		boolean exit = false;
		int option;
		
		while (exit == false) {
			System.out.println("\n" + "=======================================================================" + "\n");
			System.out.println("Please select how to test the program:");
			System.out.println("(1) 20 sets of 100 randomly generated integers");
			System.out.println("(2) Fixed integer values 1-100");
			System.out.print("Enter choice: ");
			option = scan.nextInt();
			
			System.out.println();
			switch (option) { 
			//switch statement that controls what happens when a user enters the command from the main menu
				case 1:
					System.out.println("Average swaps for series of insertions: " + averageSwaps());
					
					System.out.println("Average swaps for optimal method: " + averageOptimal());
					break;
				case 2:
					BHeap bHeap = new BHeap();
					//ArrayList<Integer> arr = bHeap.getHeapList();
					for (int i = 1; i <= 100; i++) {
						bHeap.add(i);
					}
					
					System.out.print("Heap built using series of insertions: ");
					printFirstTen(bHeap);
					
					System.out.println("Number of swaps: " + bHeap.getSwaps());
					
					System.out.print("Heap after 10 removals: ");
					for (int i = 0; i < 10; i++) {
						bHeap.remove();
					}
					printFirstTen(bHeap);
					System.out.println();
					
					//optimal method section of option 2
					BHeap bHeap2 = new BHeap();
					ArrayList<Integer> arr2 = bHeap2.getHeapList();
					for (int j = 1; j <= 100; j++) {
						bHeap2.addR(j);
					}
					
					System.out.print("Heap built using optimal method: ");
					bHeap2.optimize();
					printFirstTen(bHeap2);
					System.out.println("Number of swaps: " + bHeap2.getSwapsOptimal());
					
					System.out.print("Heap after 10 removals: ");
					for (int rmv2 = 0; rmv2 < 10; rmv2++) {
						bHeap2.remove();
					}
					printFirstTen(bHeap2);
					break;
				default:
					System.out.println("Invalid menu choice.");
					break;
			}
		}
	}
	
	private void printFirstTen(BHeap heap) {
		//Takes a heap object and will get the heap's ArrayList and printout its first 10 elements
		ArrayList<Integer> bhp = heap.getHeapList();
		for (int i = 0; i < 10; i++) {
			System.out.print(bhp.get(i) + ",");
		}
		System.out.println("...");
	}
	
	private int[] generateRandomIntegers() {
		//This function will generate 100 random unique integers through a pool of 1 to 150
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 150; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        int[] output = new int[100];
        for (int i=0; i < 100; i++) {
            //System.out.println(list.get(i));
        	output[i] = list.get(i);
        }
        
        return output;
	}
	
	private int averageSwaps() {
		//This function pertains to option (1) and will sequentially add to the heap from the list of random integers
		//Will add how many swaps were needed for 20 sets of 100 integers and divide by 20 for the average swaps
		int swaps = 0;
		for (int i = 0; i < 20; i++) {
			BHeap bh = new BHeap();
			int[] arr = generateRandomIntegers();
			for (int j = 0; j < 100; j++) {
				bh.add(arr[j]);
			}
			swaps += bh.getSwaps();
		}
		return swaps/20;
	}
	
	private int averageOptimal() {
		//Similar to averageSwaps() but will first add to the array regularly with no heapification.
		//Upon completion of addition of 100 elements, the heap will be optimized through the optimal method
		//and similarly calculate the average number of swaps
		int swaps = 0;
		for (int i = 0; i < 20; i++) {
			BHeap bh = new BHeap();
			int[] arr = generateRandomIntegers();
			
			for (int j = 0; j < 100; j++) {
				bh.addR(arr[j]);
			}
			bh.optimize();
			swaps += bh.getSwapsOptimal();
		}
		return swaps/20;
	}
}
