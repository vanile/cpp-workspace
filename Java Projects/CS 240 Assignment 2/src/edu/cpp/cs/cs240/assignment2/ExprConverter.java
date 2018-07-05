package edu.cpp.cs.cs240.assignment2;

public class ExprConverter {

	
	public ExprConverter() {
		
	}
	
//	public String toPrefix(String statement) {
//		StringStack od = new StringStack();
//		StringStack os = new StringStack();
//		statement.toLowerCase();
//		String output = "";
//		String op = "";
//		String LeftOperand = "";
//		String RightOperand = "";
//		
//		if (statement.length() == 0) {
//			throw new RuntimeException("Can't convert empty expression");
//		}
//		
//		for (int i = 0; i < statement.length(); i++) {
//			char c = statement.charAt(i);
//			
//			switch (c) {
//			case 'a': case 'b': case 'c': case 'd':case 'e':case 'f':case 'g':case 'h':case 'i':case 'j':case 'k':case 'l':
//			case 'm': case 'n': case 'o': case 'p':case 'q':case 'r':case 's':case 't':case 'u':case 'v':case 'w':case 'x':
//			case 'y':
//			case 'z': 
//				od.push(c);
//				break;
//			case '(':
//				os.push(c);
//			case ')':
//				if (os.isEmpty() == false) {
//					op += os.pop();
//					
//					RightOperand += od.pop();
//					LeftOperand += od.pop();
//					
//					String expr = op + LeftOperand + RightOperand;
//					
//
//					od.push(expr);
//					//
//				}
//			case '*':
//			case '/':
//				if (os.isEmpty() == false) {
//					if (os.peek() != '/' || os.peek() != '*') {
//						os.push(c);
//					} else {
//						while (os.peek() == '*' || os.peek() == '/') {
//							output += os.pop();
//						}
//					}
//				}
//				break;
//			case '+':
//			case '-':
//				if (os.isEmpty() == false) {
//					while (os.peek() == '*' || os.peek() == '/') {
//						output += os.pop();
//					}
//				}
//				break;
//			}
//		}
//		
//		return output;
//	}
//	
//	
	public String toPostfix(String statement) {
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
