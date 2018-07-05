import java.util.ArrayList;


class BHeap {
	//This class has all related functions for the BHeap pertaining to the project such as add and remove functions
	private ArrayList<Integer> heapList;
	private int swaps;
	private int swapsOptimal;

	
	public BHeap() {
		heapList = new ArrayList<Integer>();
		swaps = 0;
		swapsOptimal = 0;
	}
	
	public void add(int element) {
		//This function will take an inputed integer element and sequentially add the element to the array and swap to 
		//maintain the max heap as needed.
		heapList.add(element);
		int i = heapList.indexOf(element);
		
		while (heapList.get(i) > heapList.get((i - 1)/2)) {
			swap(i, (i - 1)/2);
			swaps++;
			i = heapList.indexOf(element);
		}
		
	}
	
	public void addR(int element) { 
		//regular array add function with no swaps
		//used for the optimal method to just simply add the elements to the array without swaps
		heapList.add(element);
	}
	
	private void swap(int child, int parent) {
		//function called when two elements are needed to be swapped. Typically the child and parent indexes
		int tmp = heapList.get(child); //new child element;
		heapList.set(child, heapList.get(parent)); //set new child to parent
		heapList.set(parent, tmp); //set child node to new node
	}
	
	public void remove() {
		//this function will first swap the index 0 with the last element in the array
		//it will then proceed to heapification and swap elements to make the heap a max heap again
		int endElement = heapList.get(heapList.size() - 1);
		heapList.set(0, endElement);
		heapList.remove(heapList.size() - 1);
		
		int i = 0;
		try {
			//try catch to avoid IndexOutOfBounds exceptions since at 2 multiplied by certain indexes don't
			//exist in the array
			while (heapList.get(i) < heapList.get(2*i + 1) || heapList.get(i) < heapList.get(2*i + 2)) {
				//heapification downward
				if (heapList.get(2*i + 1) > heapList.get(2*i + 2)) {
					swap(i, 2*i + 1);
					i = 2*i + 1;
				} else {
					swap(i, 2*i + 2);
					i = 2*i + 2;
				}
				swaps++;
			}
		} catch (Exception e) {
			
		}
		
	}
	
	public void optimize() {
		//the optimal method of heapifying the heap. Since the heap is predetermined to have a max of 100 elements
		//I start at index 31 or element 32 which is 2 to the 5th power which is the second to lowest level if there are 100 elements
		//after each round of the for loop, the starting Index will be divided by 2 to ascend to the next level
		int sIndex = 31;
		boolean flag = false;
		while (flag == false) {
			for (int i = sIndex; i <= 62; i++) { //we choose 62 since it's the element right before the next and final level of the tree
				try {
					if (heapList.get(i) < heapList.get(2*i + 1) || heapList.get(i) < heapList.get(2*i + 2)) {
						if (i == 49 && heapList.get(i) < heapList.get(2*i + 1)) {
							//this is a special case since comparing index 99 to index 100 (which is out of bounds)
							//would just not compare skip over the next 
							//original if statement and would just not swap the element "100" at all
							swap(i, 2*i + 1);
						} else if (heapList.get(2*i + 1) > heapList.get(2*i + 2)) {
							swap(i, 2*i + 1);
						} else {
							swap(i, 2*i + 2);
						}
						swapsOptimal++;
					}
				} catch (Exception e) {
				}
			}
			if (sIndex == 0) {
				//optimization is done
				flag = true;
			}
			sIndex = sIndex / 2; //since exponential, we divide by 2 to rise to the next level of the tree
		}
	}

	public ArrayList<Integer> getHeapList() {
		return heapList;
	}

	public void setHeapList(ArrayList<Integer> heapList) {
		this.heapList = heapList;
	}

	public int getSwaps() {
		return swaps;
	}

	public void setSwaps(int swaps) {
		this.swaps = swaps;
	}

	public int getSwapsOptimal() {
		return swapsOptimal;
	}

	public void setSwapsOptimal(int swapsOptimal) {
		this.swapsOptimal = swapsOptimal;
	}
	
	
}
