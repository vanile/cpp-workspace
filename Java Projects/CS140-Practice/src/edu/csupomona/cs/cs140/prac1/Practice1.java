/**
 * This file pedagogical material for the course
 * CS 140: Introduction to Computer Science
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2012 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs140.prac1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import sun.security.util.BigInt;

/**
 * This is a simple class file used for an in-class practice.
 * 
 * @author Edwin Rodr&iacute;guez
 * 
 */
public class Practice1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(iterFib(20000));
	}

	/*
	 * 
	 */
	public static int add(int x, int y) {
		int result = x + y;
		return result;
	}

	public static int sub(int x, int y) {
		assert (x >= y);
		int result = x - y;
		return result;
	}

	public static int mult(int x, int y) {
		if (x == 0 || y == 0) {
			return 0;
		} else {
			int prod = 0;

			while (x > 0) {
				prod = prod + y;
				x = x - 1;
			}

			return prod;
		}
	}

	public static int div(int x, int y) {
		int result = 0;

		if (y == 0)
			return -1;

		while (x >= y) {
			x -= y;
			++result;
		}

		return result;
	}

	public static int mod(int x, int y) {
		return sub(x, mult(div(x, y), y));
	}

	public static int avg(int[] numbers) {
		int sum = 0;

		for (int i = 0; i < numbers.length; i = add(i, 1)) {
			sum = add(sum, numbers[i]);
		}

		return div(sum, numbers.length);
	}

	public static int occr(int[] numbers, int x) {
		int count = 0;

		for (int n : numbers) {
			if (n == x)
				++count;
		}

		return count;
	}

	public static int mode(int[] numbers) {
		int maxCount = 0;
		int currentMode = -1;

		for (int x : numbers) {
			int count = occr(numbers, x);

			if (count > maxCount) {
				currentMode = x;
				maxCount = count;
			}
		}

		return currentMode;
	}

	public static boolean factor(int x, int y) {
		return mod(y, x) == 0;
	}

	public static void printPrimeFactors(int x) {
		System.out.println("Prime factors for x:");

		for (int i = 1; i < (x / 2); ++i) {
			if (((x % i) == 0) && prime(i)) {
				System.out.print(x + " ");
			}
		}
	}

	public static boolean isPerfect(int x) {
		int sumOfProperDivisors = 0;
		boolean answer = false;

		for (int i = 1; i < x; ++i) {
			if ((x % i) == 0) {
				sumOfProperDivisors += i;
			}
		}

		if (sumOfProperDivisors == x) {
			answer = true;
		}

		return answer;
	}

	public static boolean prime(int x) {
		boolean answer = true;

		for (int i = 2; i <= div(x, 2); i = add(i, 1)) {
			if (factor(i, x) && i != x) {
				answer = false;
				break;
			}
		}

		return answer;
	}

	public static int[] primes(int[] numbers) {
		int primesCount = 0;

		for (int i : numbers) {
			if (prime(i))
				++primesCount;
		}

		if (primesCount == 0)
			return null;

		int[] result = new int[primesCount];
		int index = 0;

		for (int i : numbers) {
			if (prime(i)) {
				result[index] = i;
				++index;
			}
		}

		return result;
	}

	public static int factorial(int x) {
		if (x == 0) {
			return 1;
		} else {
			int result = 1;

			while (x > 0) {
				result *= x;
				--x;
			}

			return result;
		}
	}

	public static int recFactorial(int x) {
		if (x == 0)
			return 1;

		return x * recFactorial(x - 1);
	}

	public static void counter(int init, int end) {
		assert (init < end);

		for (int i = init; i <= end; ++i) {
			System.out.println(i);
		}
	}

	public static void whileCounter(int init, int end) {
		assert (init < end);

		int i = init;

		while (i <= end) {
			System.out.println(i);
			i += 2;
		}
	}

	public static void grader(int grade) {
		assert (grade >= 0 && grade <= 100);

		int score = 0;

		if (grade >= 90) {
			score = 1;
		} else if (grade >= 80) {
			score = 2;
		} else if (grade >= 70) {
			score = 3;
		} else if (grade >= 60) {
			score = 4;
		} else {
			score = 5;
		}

		switch (score) {
		case 1:
			System.out.println("A");
			break;
		default:
			System.out.println("Everybody else... F!");
			break;
		}

	}

	public static void monthToString(int mo) {
		switch (mo) {
		case 1:
			System.out.println("January");
			break;
		case 2:
			System.out.println("February");
			break;
		default:
			System.out.println("Other months ot supported yet!");
			break;
		}
	}

	public static void evilSideEffectExp() {
		int x = 5;
		int y = 7;

		boolean z = (((++x) + (y++)) == 13) || (((x = 3) + (++y)) == 11);

		System.out.println("The value of the evil expression is: " + z);
		System.out.println("The final values of x and y are: " + x + " " + y);
	}

	public static int indexOfMax(int[] numbers) {
		int maxIndex = 0;

		for (int i = 1; i < numbers.length; ++i) {
			if (numbers[maxIndex] < numbers[i]) {
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	public static int arraySum(int[] numbers) {
		int sum = 0;

		for (int x : numbers)
			sum += x;

		return sum;
	}

	public static int[] shuffle(int[] list) {
		int[] result = new int[list.length];

		for (int i = 0; i < list.length; ++i) {
			// Assign random between i - list.length
			Random rand = new Random();
			int index = rand.nextInt(list.length - i) + i;
			result[i] = list[index];

			if (index != i) {
				// swaps elements i and index in list
				int t = list[i];
				list[i] = list[index];
				list[index] = t;
			}
		}

		return result;
	}
	
	public static int fib(int n) {
		if (n <= 2) return 1;
		return fib(n-1) + fib(n-2);
	}
	
	public static BigInteger iterFib(int n) {
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("1");
		BigInteger temp = new BigInteger("0");
		
		for (int i = 0; i < n-1 ; ++i) {
			temp = b;
			b = b.add(a);
			a = temp;
		}
		
		return b;
	}
	
}
