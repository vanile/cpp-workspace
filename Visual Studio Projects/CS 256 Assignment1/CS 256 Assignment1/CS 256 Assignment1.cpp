// CS 256 Assignment1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <stack>
#include <string>

using namespace std;
float Eval3(string expression) //works for positive and negative multiple digits including decimals
{
	stack<float> stack;
	cout << "length = " << expression.length() << endl;
	bool neg = false;

	for (int i = 0; i < expression.length(); i++)
	{
		char element = expression[i];
		if (element != 42 && element != 47 && element != 43 && element != 45 && element != 32 && element != 46) //check if element is a number
		{
			if (stack.empty() == 0 && expression[i - 1] != 32 && stack.top() != 45) { //determining multiple digit numbers
				float ele = (int)element - 48;
				float ele2 = stack.top();
				stack.pop();

				ele2 = ele2 * 10;
				if (ele2 < 0) //make sure to add tens and ones correctly if positive/negative
				{
					ele = ele2 - ele;
					stack.push(ele);
				}
				else
				{
					ele = ele2 + ele;
					stack.push(ele); //push number onto stack
				}

				//****debug
				float test = ele;
				cout << test << endl;
				//
			}
			else if (stack.empty() == 0 && stack.top() == 45)
				//upon checking first number after a negative sign (checks if top of stack is negative sign), will create the negative number by multiplying number * -1
			{
				stack.pop();
				float ele = (int)element - 48;

				ele = ele * (-1);
				stack.push(ele); //push number onto stack
			}
			else
			{
				int ele = (int)element - 48;
				stack.push(ele); //push number onto stack

				//****debug
				float test = ele;
				cout << test << endl;
				//
			}

		}
		else if (element == 46) //if a decimal is found then will do appropriate procedures
		{
			if (stack.empty() == 0 && expression[i - 1] != 32) //if it has whole numbers in front i.e 1.1 or 2.1, not 0.1
			{
				int ten = 10;
				i++;
				//bool flag = false;
				int top = stack.top();

				//if (top == 0) //checks if whole name was entered as 0.3 instead of .3
				//{
				//	flag = true;
				//}
				while (expression[i] != 32)
				{
					float elem = stack.top();
					stack.pop();
					float elem2 = (int)expression[i] - 48;
					elem2 = elem2 / ten;

					if (elem < 0)
					{
						elem = elem - elem2;
						stack.push(elem);
					}
					else
					{
						elem = elem + elem2;
						stack.push(elem);
					}
					
					ten = ten * 10;
					i++;
				}

				if (neg == true) //since 0 * -1 = 0 we just multiply the final answer by -1 at the end
				{
					float elem = stack.top();
					stack.pop();
					elem = elem * -1;
					stack.push(elem);
					neg = false;
				}
			}
			else //other case for fraction decimals only such as 0.1 or 0.3, but no whole numbers
			{
				int ten = 10;
				float elem = 0;
				i++;

				while (expression[i] != 32)
				{
					float elem2 = (int)expression[i] - 48;
				    elem = elem + (elem2 / ten);
					ten = ten * 10;
					i++;
				}
				stack.push(elem);
			}
		}
		else if (element == 32) //element is space so skip
		{

		}
		else if (element == 45 && expression[i + 1] != 32 && i != expression.length() - 1)
			//check if it is a negative sign of a number, while checking to make sure it is not the operand for subtraction
		{
			neg = true;
			if (expression[i + 1] != 46)
			{
				element = expression[i + 1];
				int ele = (int)element - 48;
				ele = ele * -1;
				stack.push(ele);
				i++;
			}
			else
			{
				if (stack.empty() == 0 && expression[i - 1] != 32) //if it has whole numbers in front i.e 1.1 or 2.1, not 0.1
				{
					int ten = 10;
					i = i + 2;
					while (expression[i] != 32)
					{
						float elem = stack.top();
						stack.pop();
						float elem2 = (int)expression[i] - 48;
						elem2 = elem2 / ten;

						elem = elem + elem2;
						stack.push(elem);
						ten = ten * 10;
						i++;
					}
					
					float elem = stack.top();
					stack.pop();
					elem = elem * -1;
					stack.push(elem);
				}
				else //other case for fraction decimals only such as 0.1 or 0.3, but no whole numbers
				{
					int ten = 10;
					float elem = 0;
					i = i + 2;

					while (expression[i] != 32)
					{
						float elem2 = (int)expression[i] - 48;
						elem = elem + (elem2 / ten);
						ten = ten * 10;
						i++;
					}
					elem = elem * -1;
					stack.push(elem);
				}
			}
			
		}
		else //element is a symbol
		{
			float n1 = stack.top(); //top element
			stack.pop();
			float n2 = stack.top(); //next  element
			stack.pop();

			//perform math operations depending on element read, then push result back onto the stack
			if (element == 42) //perform * operation
			{
				float result = n1 * n2;
				stack.push(result);
			}
			else if (element == 47) //perform / operation
			{
				float result = n2 / n1;
				stack.push(result);
			}
			else if (element == 43) //perform + operation
			{
				float result = n1 + n2;
				stack.push(result);
			}
			else if (element == 45) //perform - operation
			{
				float result = n2 - n1;
				stack.push(result);
			}
			else {

			}
		}
	}
	float result = stack.top();
	stack.pop();
	//***debug
	cout << stack.empty() << endl;
	//debug


	return result;
}

float Eval2(string expression) //works for positive and negative multiple digits  (no decimals)
{
	stack<float> stack;
	cout << "length = " << expression.length() << endl;
	
	for (int i = 0; i < expression.length(); i++)
	{
		char element = expression[i];
		if (element != 42 && element != 47 && element != 43 && element != 45 && element != 32) //check if element is a number
		{
			if (stack.empty() == 0 && expression[i - 1] != 32 && stack.top() != 45) { //determining multiple digit numbers
				int ele = (int)element - 48;
				int ele2 = stack.top();
				stack.pop();

				ele2 = ele2 * 10;
				if (ele2 < 0) //make sure to add tens and ones correctly if positive/negative
				{
					ele = ele2 - ele;
					stack.push(ele);
				}
				else
				{
					ele = ele2 + ele;
					stack.push(ele); //push number onto stack
				}

				//****debug
				float test = ele;
				cout << test << endl;
				//
			}
			else if (stack.empty() == 0 && stack.top() == 45)
				//upon checking first number after a negative sign (checks if top of stack is negative sign), will create the negative number by multiplying number * -1
			{
				stack.pop();
				int ele = (int)element - 48;

				ele = ele * (-1);
				stack.push(ele); //push number onto stack
			}
			else
			{
				int ele = (int)element - 48;
				stack.push(ele); //push number onto stack

				//****debug
				float test = ele;
				cout << test << endl;
				//

			}

		}
		else if (element == 32) //element is space so skip
		{

		}
		else if (element == 45 && expression[i+1] != 32 && i != expression.length() -1)
		//check if it is a negative sign of a number, while checking to make sure it is not the operand for subtraction
		{
			//stack.push(45);
			element = expression[i + 1];
			int ele = (int)element - 48;
			ele = ele * -1;
			stack.push(ele);
			i++;
		}
		else //element is a symbol
		{
			float n1 = stack.top(); //top element
			stack.pop();
			float n2 = stack.top(); //next  element
			stack.pop();

			//perform math operations depending on element read, then push result back onto the stack
			if (element == 42) //perform * operation
			{
				float result = n1 * n2;
				stack.push(result);
			}
			else if (element == 47) //perform / operation
			{
				float result = n2 / n1;
				stack.push(result);
			}
			else if (element == 43) //perform + operation
			{
				float result = n1 + n2;
				stack.push(result);
			}
			else if (element == 45) //perform - operation
			{
				float result = n2 - n1;
				stack.push(result);
			}
			else {

			}
		}
	}
	float result = stack.top();
	stack.pop();
	//***debug
	cout << stack.empty() << endl;
	//debug


	return result;
}

float Eval1(string expression) //works for positive multiple digits  (no decimals)
{
	stack<char> stack;
	cout << "length =" << expression.length() << endl;
	for (int i = 0; i < expression.length(); i++)
	{
		char element = expression[i];
		if (element != 42 && element != 47 && element != 43 && element != 45 && element != 32) //check if element is a number
		{	
			if (stack.empty() == 0 && expression[i-1] != 32) { //determining multiple digit numbers
				int ele = (int)element - 48;
				int ele2 = stack.top();
				stack.pop();

				ele2 = ele2 * 10;
				ele = ele2 + ele;
				stack.push(ele); //push number onto stack
			}
	
			else
			{	
				int ele = (int)element - 48;
				stack.push(ele); //push number onto stack
			}

		}
		else if (element == 32) //element is a space so skip
		{

		}
		else //element is a symbol
		{
			float n1 = stack.top(); //top element
			stack.pop();
			float n2 = stack.top(); //next  element
			stack.pop();

			//perform math operations depending on element read, then push result back onto the stack
			if (element == 42) //perform * operation
			{
				float result = n1 * n2;
				stack.push(result);
			}
			else if (element == 47) //perform / operation
			{
				float result = n2 / n1;
				stack.push(result);
			}
			else if (element == 43) //perform + operation
			{
				float result = n1 + n2;
				stack.push(result);
			}
			else if (element == 45) //perform - operation
			{
				float result = n2 - n1;
				stack.push(result);
			}
			else {

			}
		}
	}
	float result = stack.top();
	stack.pop();

	return result;
}

float Eval(string expression) //only positive single digits
{
	stack<float> stack;

	for (int i = 0; i < expression.length(); i++)
	{
		char element = expression[i];
		if (element != 42 && element != 47 && element != 43 && element != 45) //check if element is a number
		{
			int ele = (int)element - 48;
			stack.push(ele); //push number onto stack
		}
		else //element is a symbol
		{
			float n1 = stack.top(); //top element
			stack.pop();
			float n2 = stack.top(); //next  element
			stack.pop();

			//perform math operations depending on element read, then push result back onto the stack
			if (element == 42) //perform * operation
			{
				float result = n1 * n2;
				stack.push(result);
			}
			else if (element == 47) //perform / operation
			{
				float result = n2 / n1;
				stack.push(result);
			}
			else if (element == 43) //perform + operation
			{
				float result = n1 + n2;
				stack.push(result);
			}
			else if (element == 45) //perform - operation
			{
				float result = n2 - n1;
				stack.push(result);
			}
		}
	}
	float result = stack.top();
	stack.pop();

	return result;
}

int _tmain(int argc, _TCHAR* argv[])
{
	std::string expression;
	char key = 0;

	cout << "Enter a postfix expression: ";
	std::getline(std::cin, expression);
	float result = Eval3(expression);

	cout << "output = " << result << endl;
	cin >> key;

	return 0;
}



