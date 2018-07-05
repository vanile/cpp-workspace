package edu.cpp.cs.cs240.assignment3;

public interface GenericList<T> {

	    public void add(T value);

	    public void add(int index, T value);

	    public void remove(int index);

	    public int size();

	    public int indexOf(T value);

	    public T get(int index);

	    public void set(int index, T value);

	    public String toString();

	    public void clear();
}
