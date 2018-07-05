// ConsoleApplication3.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
 using namespace std;

int fun(int &k) {
	 k += 4;
	 cout << 3 * k - 1 << endl;
	 return 3 * k - 1;
}

int _tmain(int argc, _TCHAR* argv[])
{
	char key = 0;
	int i = 10, j = 10, sum1, sum2;
	sum1 = (i / 2) + fun(i);
	sum2 = fun(j) + (j / 2);

	cout << sum1 << " " << sum2 << endl;
	cin >> key;

	return 0;
}



