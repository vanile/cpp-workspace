package edu.cpp.cs.cs240.assignment2;

public class ExprConverter {

	public ExprConverter() {

	}
	//StringStack class is actually a generic type class <T> but i named it wrong in the beginning
	public String toPrefix(String statement) {
		StringStack<String> od = new StringStack<String>(); //operand stack
		StringStack<String> os = new StringStack<String>(); //operands stack
		statement = statement.toLowerCase();
		
		String output = "";
		String op = "";
		String LeftOperand = "";
		String RightOperand = "";
		
		if (statement.length() == 0) {
			throw new RuntimeException("Can't convert empty expression");
		}
		
		for (int i = 0; i < statement.length(); i++) {
			char c = statement.charAt(i);
			String c1 = Character.toString(c);
			
			switch (c1) {
			case "a": case "b": case "c": case "d":case "e":case "f":case "g":case "h":case "i":case "j":case "k":case "l":
			case "m": case "n": case "o": case "p":case "q":case "r":case "s":case "t":case "u":case "v":case "w":case "x":
			case "y":
			case "z": 
				od.push(c1);
				break;
			case "(":
				// for some reason if I pushed the actual string index (c1), then it would give a different expression at the end
				os.push("(");
				break;
			case ")":
				if (os.isEmpty() == false) {
					//used while loop to continue comparing to next operands stack while the top of operands is not a left paranthesis, if so while loop ends
					boolean flag = true;
					while (flag == true) {
						if (os.isEmpty() == false && os.peek() != "(") {
							op = os.pop();
							RightOperand = od.pop();
							LeftOperand = "";
							if (od.isEmpty() == false) {
								LeftOperand = od.pop();
							}
							
							String expr = op + LeftOperand + RightOperand;
							od.push(expr);
						} else {
							if (os.isEmpty() == false) {
								os.pop();
							}
							flag = false;
						}
					}
				} else {
					throw new RuntimeException("unbalanced expression");
				}
				break;
			case "*":
			case "/":
				if (os.isEmpty() == false) {
					if (os.peek() != "/" || os.peek() != "*") {
						os.push(c1);
					} else {
						while (os.peek() == "*" || os.peek() == "/") {
							if (os.isEmpty() == false) {
								op = os.pop();
								
								RightOperand = od.pop();
								LeftOperand = "";
								
								if (od.isEmpty() == false) {
									LeftOperand = od.pop();
								}
								
								String expr = op + LeftOperand + RightOperand;
								od.push(expr);
							} else {
								os.push(c1);	
							}
						}
					}
				} else {
					os.push(c1);
				}
				break;
			case "+":
			case "-":
				if (os.isEmpty() == false) {
					// similar while loop as previously commented for case ")":
					boolean flag = true;
					while (flag == true) {
						if (os.isEmpty() == false) {
							if (os.peek() == "(") {
								os.pop();
								flag = false;
							} else {
								op = os.pop();
								RightOperand = od.pop();
								LeftOperand = "";
								
								if (od.isEmpty() == false) {
									LeftOperand = od.pop();
								}
								
								String expr = op + LeftOperand + RightOperand;
								od.push(expr);
							}
							os.push(c1);
							flag = false;
						} else {
							os.push(c1);
							
							flag = false;
						}
					} 
				} else {
					os.push(c1);
				}
				break;
			}
		}

		while (od.isEmpty() == false) {
			op = "";
			if (os.isEmpty() == false) {
				op = os.pop();
			}
			
			RightOperand = od.pop();
			LeftOperand = "";
			
			if (od.isEmpty() == false) {
				LeftOperand = od.pop();
			}
			String expr =  op + LeftOperand + RightOperand;
			
			output += expr;
		}
		return output;
	}
	
	
	public String toPostfix(String statement) {
		// character based, works 100% in all my test cases
		StringStack<Character> ss = new StringStack<Character>();
		statement = statement.toLowerCase();
		
		String output = "";
		if (statement.length() == 0) {
			throw new RuntimeException("Can't convert empty expression");
		}
		
		for (int i = 0; i < statement.length(); i++) {
			char c = statement.charAt(i);
			
			switch (c) {
			case 'a': case 'b': case 'c': case 'd':case 'e':case 'f':case 'g':case 'h':case 'i':case 'j':case 'k':case 'l':
			case 'm': case 'n': case 'o': case 'p':case 'q':case 'r':case 's':case 't':case 'u':case 'v':case 'w':case 'x':
			case 'y':
			case 'z': 
				output += c;
				break;
			case '(':
            	ss.push('(');
            	break;
			case ')': 
				if (ss.isEmpty() == false) {
					while (ss.peek() != '(') {
						output += ss.pop();
					}
				} else {
					throw new RuntimeException("unbalanced expression");
				}
				ss.pop();
				break;
			case '*': 
			case '/':
				if (ss.isEmpty() == false) {
					boolean flag = true;
					while (flag == true) {
						if (ss.isEmpty() == false) {
							if (ss.peek() == '*' || ss.peek() == '/') {
								output += ss.pop();
							
							} else {
								ss.push(c);
								flag = false;
							}
						} else {
							ss.push(c);
							flag = false;
						}
					}
				} else {
					ss.push(c);
				}
				break;
			case '+':
			case '-':
				if (ss.isEmpty() == false) {
					
					boolean flag = true;
					while (flag == true) {
						if (ss.isEmpty() == false) {
							if (ss.peek() == '*' || ss.peek() == '/'  || ss.peek() == '+' || ss.peek() == '-') {
								output += ss.pop();
							} else {
								ss.push(c);
								flag = false;
							}
						} else {
							ss.push(c);
							flag = false;
						}
					}
				} else {
					ss.push(c);
				}
				break;
			default: 
				throw new RuntimeException("'" + c + "'" + " is an invalid character in expression, try again.");
			}
		}
		
		while (ss.isEmpty() == false) {
			if (ss.peek() == '(') {
				throw new RuntimeException("Original expression is unbalanced");
			} else {
				output += ss.pop();
			}
		}
		return output;
	}
	
	public String toPostfix2(String statement) {
		//this uses a string stack instead of character, wrong output on a*(b+c)/d
		StringStack<String> ss = new StringStack<String>();
		statement = statement.toLowerCase();
		
		String output = "";
		
		if (statement.length() == 0) {
			throw new RuntimeException("Can't convert empty expression");
		}
		
		for (int i = 0; i < statement.length(); i++) {
			char c = statement.charAt(i);
			String c1 = Character.toString(c);
			
			switch (c1) {
			case "a": case "b": case "c":
			case "d": 
				output += c1;
				break;
			case "(":
            	ss.push("(");
            	break;
			case ")": 
				if (ss.isEmpty() == false) {
					boolean flag = true;
					while (flag == true) {
						if (ss.isEmpty() == false) {
							if (ss.peek() != "(") {
								output += ss.pop();
							} else {
								ss.pop();
								flag = false;
							}
						} else {
							flag = false;
						}
						
						
					}
				} else {
					throw new RuntimeException("unbalanced expression");
				}

				break;
			case "*": 
			case "/":
				if (ss.isEmpty() == false) {
					boolean flag = true;
					while (flag == true) {
						if (ss.isEmpty() == false) {
							if (ss.peek() == "*" || ss.peek() == "/") {
								output += ss.pop();
							} else {
								ss.push(c1);
								flag = false;
							}
						} else {
							ss.push(c1);
							flag = false;
						}
					}
				} else {
					ss.push(c1);
				}
				break;
			case "+":
			case "-":
				if (ss.isEmpty() == false) {
					
					boolean flag = true;
					while (flag == true) {
						if (ss.isEmpty() == false) {
							if (ss.peek() != "(") {
								output += ss.pop();
							} else {
								ss.push(c1);
								flag = false;
							}
						} else {
							ss.push(c1);
							flag = false;
						}
					}
				} else {
					ss.push(c1);
				}
				break;
			default: 
				throw new RuntimeException("'" + c1 + "'" + " is an invalid character in expression, try again.");
			}
		
		}
		while (ss.isEmpty() == false) {
			if (ss.peek() == "(") {
				throw new RuntimeException("Original expression is unbalanced");
				
			} else {
				output += ss.pop();
			}
		}

		return output;
	}
}
