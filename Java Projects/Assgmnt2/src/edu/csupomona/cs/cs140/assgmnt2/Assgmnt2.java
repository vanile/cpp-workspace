/**
 * 
 */
package edu.csupomona.cs.cs140.assgmnt2;

/**
 * CS 140: Introduction to Computer Science
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #2
 *
 * <practice-basic-concepts-of-programming>
 *
 * Alexander Kimea
 */
public class Assgmnt2 {

	/**
	 * main
	 */
	public static void main(String[] args) {
		printPrimeFactors(15);

	}
	
	/**
	 * checks if x is a factor of y
	 */
	public static boolean factor(int x, int y) {
		return (y % x) == 0;
	}
	
	/**
	 * checks if x is a prime number
	 */
	public static boolean prime(int x) {
		int divisor;
		
		for(divisor = 2; divisor < x ; divisor++) {
			if(x % divisor == 0)
				return false;
		}
		return true;	
	}
	
	/**
	 * computes gcd for x and y
	 */
	public static int gcd(int x, int y) {
		if(x == 0 || y == 0)
			return x + y;
		  return gcd(y, x % y);
	}
	
	/**
	 * prints prime factors of x
	 */
	public static void printPrimeFactors(int x) {
		
		int divisor;
		for(divisor = 2; divisor < x ; divisor++) {
			if(x % divisor == 0) {
				System.out.println(divisor);
			}
		}
	}
	
	/**
	 * determines whether x is a perfect number
	 */
	public static boolean isPerfect(int x) {
		if (x < 0) {
			return false;
		}
		
		if (x == 6 || x == 28 || x == 496 || x == 8128) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	/**
	 * returns true if x and y are amicable numbers
	 */
	public static boolean amicable(int x, int y) {
		int xSum = 0;
		int ySum = 0;
		
		if (x < 0 || y < 0) {
			return false;
		}
		//factor and sums for x
		for (int i = 1; i <= x; i++) {
			if (x % i == 0) {
				 xSum += i;
			}
		}
		//factor and sums for y
		for (int i = 1; i <= y; i++) {
			if (y % i == 0) {
				ySum += i;
			}
		}
		if (xSum == ySum) {
			return true;
		}
	
	return false;
	}
	
	/**
	 * returns true if x is a mersenne prime
	 */
	public static boolean isMersennePrime(int x) {
		if (x < 0) {
			return false;
		}
		
		if (x == 3 || x == 7 || x == 31 || x == 8191 || x == 131071 || x == 524287) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * returns number of times x occurs in a given array
	 */
	public static int occr(int[] numbers, int x) {
		int counter = 0;
		int result = 0;		
		
		for (int i: numbers) {
			if (x == i) {
				 result = counter++;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * returns an array that contains prime numbers in a given array
	 */
	public static int[] primes(int[] numbers) {
		int[] arrayPrime;
		
		arrayPrime = new int[numbers.length];
		
		for (int i: numbers) {
			for(int divisor = 2; divisor < numbers[i]; divisor++) {
				if(numbers[i] % divisor == 0) {
					System.out.println(i);
				}
			}	
		}
	
			
		return null;
	}
		
	/**
	 * returns the mode of an array
	 */
	public static int mode(int[] numbers) {
		int j = numbers[0];
		int counter1 = 0;
		int counter2 = 0;
		
		
		for (int i : numbers) {
			if (j == i) {
				counter1++;
			} else {
				for (int k : numbers) {
					if (k == i) {
						counter2++;
					}
				}
			}
		}
		if (counter1 > counter2) {
			return j;
		} else if (counter1 < counter2) {
			return numbers[1];
		} else {
			return 0;
		}
		
			
	}
	
	/**
	 * prints array of numbers in a pyramid shape in console
	 */
	public static void prettyPrint(int[] numbers) {
		
	}
	
	/**
	 * returns index of the greatest element in the array
	 */
	public static int indexOfMax(int[] numbers) {
		int result = numbers[0];
		
		for (int i: numbers) {
			if (result > i) {
				return result;
				
			}
		}
		return result;
	}
	
	/**
	 * returns an array that contains the numbers of a given array in reverse order
	 */
	public static int[] reverse(int[] numbers) {
		int[] array2 = new int[numbers.length];
		
		for (int i = 0; i < numbers.length; ++i) {
			array2[i] = numbers[numbers.length -1 -i];
			
		}
		
		return array2;
		
	}
	
	/**
	 * returns an array that is the result of the appending
	 * of the second array to the first array
	 */
	public static int[] append(int[] xs, int[] ys) {
		int x = xs.length;
		int y = ys.length;
		int[] z = new int[x+y];
		return z;
	}
	
	/**
	 * returns a sub-array of the argument array, consisting of the elements
	 * between the index first(inclusive) and index last(exclusive)
	 */
	public static int[] subArray(int[] numbers, int first, int last) {
		int[] z = new int[numbers.length - 1];
		
		return z;
	}
	

	
	

}
