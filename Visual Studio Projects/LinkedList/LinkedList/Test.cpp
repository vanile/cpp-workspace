
#include "stdafx.h"
#include <iostream>
#include "LinkedList.cpp"

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	LinkedList list;

	list.add(10);
	list.add(1);
	list.add(9);
	list.add(2);
	list.add(8);
	list.add(3);
	list.add(7);
	list.add(4);
	list.add(6);
	list.add(5);

	list.print();
	cin.get();
	return 0;
}