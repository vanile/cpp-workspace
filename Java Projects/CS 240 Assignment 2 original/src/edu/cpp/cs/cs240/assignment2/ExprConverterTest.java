package edu.cpp.cs.cs240.assignment2;

import java.util.Scanner;

public class ExprConverterTest {

	public static void main(String[] args) {
		ExprConverter ec = new ExprConverter();
		String infix = infixPrompt();
		//Postfix = character based, Postfix2 = string based. See comments below
		System.out.println("Postfix: " + ec.toPostfix(infix));
		System.out.println("Postfix2: " + ec.toPostfix2(infix));
		System.out.println("");
		System.out.println("Prefix: " + ec.toPrefix(infix));

		
		//I got all the examples on the assignment paper to convert infix to prefix/postfix correctly except 
		//a+b*c for prefix. it prints it sort of swapped (*bc+a), but it works correctly if i do (a+b*c). i dunno
		//I spent like 3-5 hours for like 5 days, couldn't fix.
		
		//my toPostfix is using character based stack because I forgot it said string stack, but it works 100% in my cases
		
		//ok, i made a string stack based postfix method (toPostfix2), but only one bug on one test case i found for a*(b+c)/d.
		// the rest of the cases seem to work
		

	}
	
	public static String infixPrompt() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter an infix expression: ");
		String statement = sc.nextLine();
		
		return statement;
	}
	
	

}
