package edu.cpp.cs.cs240.assignment2;

public class StringStack<T> {
	
	private Node<T> head;
	private int size;
	
	public StringStack() {
		head = null;
		size = 0;
	}

	
	public T peek() {
		if (head != null) {
			return head.getValue();
		} else {
			throw new RuntimeException("Can't peek from an empty stack");
		}
	}

	
	public T pop() {
		if (head != null) {
			T value = head.getValue();
			head = head.getNext();
			size--;
			return value;
		} else {
			throw new RuntimeException("Can't pop from an empty stack");
		}
	}

	
	public void push(T value) {
		Node<T> newNode = new Node<T>(value, head);
		head = newNode;
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	
	public int size() {
		return size;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		Node<T> cursor = head;
		while(cursor != null) {
			sb.append(cursor.getValue());
			if (cursor.getNext() != null) {
				sb.append(",");
			}
			cursor = cursor.getNext();
		}
		sb.append("]");
		return sb.toString();
	}
}
