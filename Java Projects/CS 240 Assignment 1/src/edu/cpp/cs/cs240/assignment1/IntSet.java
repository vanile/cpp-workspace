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
		while (current != null) {
			if (value == current.getValue()) {
				return true;
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
		Node newNode = new Node(value, null);
		
		while (current != null) {
			if (value == current.getValue()) {
				if (head != null) {
					tail.setNext(newNode);
				} else {
					head = newNode;
				}
			}
		}
		
		tail = newNode;
		size++;
	}
	
	public boolean subsetOf(IntSet givenSet) {
		int counter1 = 0;
		int counter2 = 0;
		
		while (current != null) {
			
		}
		return false;
	}
		
	
	public boolean isEqual(IntSet givenSet) {
		//
		return false;
	}
}
