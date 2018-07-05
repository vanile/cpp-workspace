package edu.cpp.cs.cs240.assignment1;

public class IntSet {

	private Node head;
	private Node tail;
	private Node current;
	private int size;
	
	public IntSet() {
		size = 0;
		current = head;
	}
	
	public boolean contain(int value) {
		current = head;
		for (int i = 0; i < size; i++) {
			if (value == current.getValue()) {
				return true;
			} else {
				current = current.getNext();
			}
		}
		return false;
	}
	
	public void remove(int value) {
		if (value < size()) {
			
			if (value == 0) {
				head = head.getNext();
				if (head == null) {
					tail = null;
				}
			} else {
				Node prev = head;
				for(int i = 0; i < value - 1; i++) {
					prev = prev.getNext();
				}
				prev.setNext(prev.getNext().getNext());

				if (value == size() - 1) {
					tail = prev;
				}
			}
			size--;
		} else {
			throw new RuntimeException("Index out of bound.");
		}
	
	}
	
	public int size() {
		return size;
	}
	
	public void addElement(int value) {
		if (this.contain(value)) {
			// do nothing
		} else {
			Node newNode = new Node(value, null);

			if (head != null) {
				tail.setNext(newNode);
			} else {
				head = newNode;
			}
			
			tail = newNode;
			size++;
		}
	}
	
	public boolean subsetOf(IntSet givenSet) {
		int counter = 0;
		current = head;
		
		for (int i = 0 ; i < size; i++) {
			int value = current.getValue();
			
			if (this.contain(value) == givenSet.contain(value)) { //test if set b's terms are in set a and adds counter if there is
				counter++;
			}
			
			current = current.getNext();
		}
		
		if (counter == givenSet.size()) { //if counter equals set b's size then it is a subset
			return true;
		}
		
		return false;
	}
		
	
	public boolean isEqual(IntSet givenSet) {
		int counter = 0;
		current = head;
		
		if (this.size() == givenSet.size()) {
			for (int i = 0; i < size; i++) {
				int value = current.getValue();
				
				if (this.contain(value) == givenSet.contain(value)) { //tests if all values in set a are in set b
					counter++;
				}
				current = current.getNext();
				
				if (counter == size) { // if counter equals set a then both sets are equal
					return true;
				}
			}
		} 
		
		return false;
	}
	
	public IntSet union(IntSet givenSet) {
		IntSet list = this; // starts with set A as first list because union adds both sets, no need to start with empty 
		
		if (this.size() > 0 && givenSet.size() > 0) { //makes sure both sets are greater than 0
			current = head;
			
			// I made these to traverse set b since we've already added set a's terms
			Node givenHead = givenSet.getHead(); //the head node of givenSet (set b)
			Node givenCurrent = givenHead; // set b's current tracker
			
			int givenValue = givenCurrent.getValue(); 
			
			for (int i = 0; i < givenSet.size(); i++) {
				
				if (givenSet.contain(givenValue) == this.contain(givenValue)) {
					list.addElement(givenCurrent.getValue());
				}
				
				givenCurrent = givenCurrent.getNext();
			}
		} 
		return list;
	}
	
	public IntSet intersection(IntSet givenSet) {
		IntSet list = new IntSet();
		
		current = head;
	
		while (current != null) {
			if (givenSet.contain(current.getValue())) {
				list.addElement(current.getValue());
			}
			
			current = current.getNext();
		}
		
		return list;
	}
	
	public IntSet complement(IntSet givenSet) {
		IntSet list = new IntSet();
		
		for (int i = 0; i < size ; i++) {
			if (this.contain(this.get(i)) != givenSet.contain(this.get(i))) {
				list.addElement(this.get(i));
			}
		}
		return list;
	}
	
	public String toString() {
		String list = "[";
		
		current = head;
		
		while(current != null) {
			list += current.getValue();
			current = current.getNext();
			if (current != null) {
				list += ",";
			}
		}
		
		list += "]";
		
		return list;
	}
	
	
	
	public Node getCurrent() {
		return current;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int get(int index) {
		if (index < size()) {
			current = head;
			for(int i = 0 ; i < index; i++) {
				current = current.getNext();
			}
			return current.getValue();
		} else {
			throw new RuntimeException("Index out of bound.");
		}

	}
	
	

}
