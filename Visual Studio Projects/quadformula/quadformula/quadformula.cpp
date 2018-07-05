// quadformula.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <cmath>

using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	int a = 1, b = 3, c = 2;
	
	double d = (b * b) - (4 * a * c), x1, x2;
	
	if (d < 0)
	{
		cout << "no real solution" << endl;
		cin.get();
		return 0;
	}

	x1 = (-b - sqrt(d)) / (2 * a);
	x2 = (-b + sqrt(d)) / (2 * a);

	cout << x1 << endl << x2 << endl;
	cin.get();

	
	return 0;
}