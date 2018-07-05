#include <iostream>
#include "stdafx.h"
using namespace std;

class Node
{
public:
	Node();
	Node(int data, Node *pointer)
	{
		value = data;
		next = pointer;
	}

	int getValue() const
	{
		return value;
	}

	void setValue(int item)
	{
		value = item;
	}
	Node *getNext() const
	{
		return next;
	}

	void setNext(Node *node)
	{
		next = node;
	}

private:
	int value;
	Node *next;
};