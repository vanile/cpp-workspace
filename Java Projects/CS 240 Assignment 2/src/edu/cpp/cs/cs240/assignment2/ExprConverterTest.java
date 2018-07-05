package edu.cpp.cs.cs240.assignment2;

import java.util.Scanner;

public class ExprConverterTest {

	public static void main(String[] args) {
		ExprConverter ec = new ExprConverter();
		String infix = infixPrompt();
		System.out.println("Postfix: " + ec.toPostfix(infix));
//		System.out.println("Prefix: " + ec.toPrefix(infix));
//		
	}
		
	
	
	public static String infixPrompt() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter an infix expression: ");
		String statement = sc.nextLine();
		
		return statement;
	}
	
	

}
