package edu.cpp.cs.cs331.project1;

public interface ISortAlgorithms {
	
	public int[] insertionSort(int[] array);
	public int[] mergeSort(int[] array, int low, int high);
	public int[] quickSort1(int[] array, int p, int q);
	public int[] quickSort2(int[] array, int p, int q);
	public int[] quickSort3(int[] array, int p, int q);
}
