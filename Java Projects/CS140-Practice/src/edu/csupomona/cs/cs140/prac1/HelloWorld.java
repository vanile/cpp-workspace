/**
 * 
 */
package edu.csupomona.cs.cs140.prac1;

/**
 * @author apoloimagod
 *
 */
public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello Fools!");
		
		int x;
		
		x = 9;
		
		boolean cond = true;
		
		if (cond) {
			x = 3;
		} else {
			x = 4;
		}
		
		x = 1;
		
		switch(x) {
		case 0:
			System.out.println("Case 1");
			break;
		case 1:
			System.out.println("Case 2");
		case 2:
			System.out.println("Case 3");
		default:
			System.out.println("Default Case");
			break;
			
		}
		
		int[] numbers = new int[100];
		int j = 0;
		
		while (j < numbers.length) {
			numbers[j] = j;
			++j;
		}
		
		for (int i = 0; i < numbers.length; ++i) {
			numbers[i] = i;
		}
		
		for (int y : numbers) {
			System.out.println(y);
		}
	}

}
