package edu.cpp.cs.cs331.project1;

import java.util.concurrent.ThreadLocalRandom;

public class SortingAlgorithms implements ISortAlgorithms {

	public SortingAlgorithms() {
	}
	
	@Override
	public int[] insertionSort(int[] array) {
		int i, j, target;
		for (i = 0; i <= array.length - 1; i++) {
			target = array[i];
			j = i - 1;
			while (j >= 0 && target < array[j]) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = target;
		}
		return array;
	}

	@Override
	public int[] mergeSort(int[] array, int low, int high) {
		int mid;
		if (low < high) {
			mid = (low + high)/2;
			mergeSort(array, low, mid);
			mergeSort(array, mid + 1, high);
			merge(array, low, mid, high);
		}
		return array;
	}
	
	private void merge(int[] array, int low, int mid, int high) { 
		int[] temp = new int[array.length];
		for (int i = low; i <= high; i++) {
			temp[i] = array[i];
		}
		int i = low;
		int j = mid + 1;
		int k = low;
		
		while (i <= mid && j <= high) {
			if (temp[i] <= temp[j]) {
				array[k] = temp[i];
				i++;
			} else {
				array[k] = temp[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			array[k] = temp[i];
			k++;
			i++;
		}
	}

	@Override
	public int[] quickSort1(int[] array, int p, int q) {
		int pp = partition(array, p, q);
		if (p < pp - 1) {
			quickSort1(array, p, pp - 1);
		}
		if (q > pp) {
			quickSort1(array, pp, q);
		}
		return array;
	}
	
	private int partition(int[] array, int first, int last) {
		int pivot = array[first];

		while (first <= last) {
			while (array[first] < pivot) {
				first++;
			}
			while (array[last] > pivot) {
				last--;
			}
			if (first <= last) {
				int temp = array[first];
				array[first] = array[last];
				array[last] = temp;

				first++;
				last--;
			}
		}
		return first;
	}

	@Override
	public int[] quickSort2(int[] array, int p, int q) {
		if (array.length > 16) {
			return quickSort1(array, p, q);
		} else {
			return insertionSort(array);
		}
	}

	@Override
	public int[] quickSort3(int[] array, int p, int q) {
		int pp = 0;
		if (p < q) {
			if ((q - p + 1 >= 16)) {
				int rand = ThreadLocalRandom.current().nextInt(0, array.length - 1);
				
				int temp = array[p];
				array[p] = array[p + (rand % (q - p + 1))];
				array[p + (rand % (q - p + 1))] = temp;
			}
		}
		pp = partition(array, p, q);
		if (p < pp - 1) {
			quickSort1(array, p, pp - 1);
		}
		if (q > pp) {
			quickSort1(array, pp, q);
		}
		return array;
	}
}
