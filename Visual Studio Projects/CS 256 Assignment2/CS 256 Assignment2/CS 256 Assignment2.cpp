// CS 256 Assignment2.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include <iostream>
#include <stack>
#include <string>
#include <vector>

using namespace std;

float Eval(string expression) //works for positive and negative multiple digits including decimals
{
	stack<float> stack;
	bool neg = false;

	for (int i = 0; i < expression.length(); i++)
	{
		char element = expression[i];
		if (element != 42 && element != 47 && element != 43 && element != 45 && element != 32 && element != 46) //check if element is a number
		{
			/*
			if (neg == true) //since 0 * -1 = 0 we just multiply the final answer by -1 at the end
			{
				float elem = stack.top();
				stack.pop();
				elem = elem * -1;
				stack.push(elem);
				neg = false;
			}
			*/
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
			}
			else if (stack.empty() == 0 && stack.top() == 45)
				//upon checking first number after a negative sign (checks if top of stack is negative sign), will create the negative number by multiplying number * -1
			{
				stack.pop();
				float ele = (int)element - 48;
				stack.push(ele); //push number onto stack
			}
			else
			{
				int ele = (int)element - 48;
				stack.push(ele); //push number onto stack
			}
		}
		else if (element == 46) //if a decimal is found then will do appropriate procedures
		{
			if (stack.empty() == 0 && expression[i - 1] != 32) //if it has whole numbers in front i.e 1.1 or 2.1, not 0.1
			{
				int ten = 10;
				i++;

				int top = stack.top();
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
				//HERE was original neg flag checker
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
			if (expression[i + 1] != 46)
			{
				neg = true;
				element = expression[i + 1];
				int ele = (int)element - 48;
				ele = ele * -1; //test
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
					elem = elem * 1;
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
					elem = elem * 1;//
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

	return result;
}


class aVariable
{
public:
	string parse()
	{
		string newName, newExp;
		int i = 0;
		
		while (expression[i] != 32)
		{
			newName = newName + expression[i];
			i++;
		}
		i++; //
		name = newName;

		for (i; i < expression.length(); i++)
		{
			newExp = newExp + expression[i];
		}
		expression = newExp;

		return expression;
	}

	float evaluate()
	{
		output = Eval(expression);
		return output;
	}

	string expression;
	string name;
	float output;
};
string superConvert(string expression, vector<aVariable> list) //given an expression with a variable in it (not assigning), it will convert the expression to the include variable's value
{
	int i = 0;
	if (list.size() == 0) //list holds the variables stored, so if is empty then there is no variable in the expression
	{
		return expression;
	}
	while (expression[i] != 32) //skip the assigning variable
	{
		i++;
	}
	i++;

	int check = list.size();
	while (check != 0) 
	{
		string name;
		bool erased = false;
		for (i; i < expression.length(); i++)
		{
			if (expression[i] > 64 && expression[i] < 91)
			{
				name = name + expression[i];
				expression.erase(expression.begin() + i);
				erased = true;

			}
			else if (expression[i] > 96 && expression[i] < 123)
			{
				name = name + expression[i];
				expression.erase(expression.begin() + i);
				erased = true;
			}

			if (name.length() != 0 && expression[i] == 32)
			{
				break;
			}

			if (erased == true) i--;
			
		}
		for (unsigned int n = 0; n < list.size(); n++)
		{
			if (list.at(n).name == name)
			{
				std::string newOut = std::to_string(list.at(n).output);
				expression.replace(expression.begin() + i, expression.end() - expression.length() + i, newOut);
			}
		}
		check--;
	}
	return expression;
}


int _tmain(int argc, _TCHAR* argv[])
{
	std::string expression;
	char key = 0;

	vector<aVariable> listVar;
	bool quit = 1;
	while (quit == 1)
	{
		cout << "Enter a postfix expression: ";
		std::getline(std::cin, expression);

		if (expression == "quit")
		{
			cout << expression << endl;
			expression.clear();
			quit = 0;
			break;
		}
		//check if user is asking for variable's value using '^'
		int i = 0;
		string name = "";
		while (expression[i] != 32)
		{
			name = name + expression[i];
			i++;
		}
		i++;
		bool found = false;
		if (expression[i] == '^')
		{
			for (int j = 0; j < listVar.size(); j++)
			{
				if (listVar.at(j).name == name)
				{
					cout << listVar.at(j).output << endl;
					found = true;
				}
			}

			if (found == false)
			{
				cout << "--variable not found--" << endl;
			}
		}
		else
		{
			aVariable vars;
			expression = superConvert(expression, listVar);

			if (expression == "errornovariable")
			{
				cout << endl << "<invalid expression, invalid variable>" << endl << endl;
			}
			else
			{
				vars.expression = expression;
				vars.parse();
				vars.evaluate();
				cout << vars.output << endl;
				listVar.push_back(vars);
			}
		}
		/*
		aVariable vars;
		expression = superConvert(expression, listVar);
		if (expression == "errornovariable")
		{
		cout << endl << "<invalid expression, invalid variable>" << endl << endl;
		}
		else
		{
		vars.expression = expression;
		vars.parse();
		vars.evaluate();
		cout << vars.output << endl;
		listVar.push_back(vars);
		}
		*/
	}
	cin >> key;
	return 0;
}
