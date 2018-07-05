// ConsoleApplication1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;

class Node
{
public:
	Node()
	{
		value = 0;
		next = NULL;
	}
	Node(int data, Node* pointer)
	{
		value = data;
		next = pointer;
	}

	int getValue()
	{
		return value;
	}

	void setValue(int item)
	{
		value = item;
	}

	Node* getNext()
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


class LinkedList
{
public:
	LinkedList()
	{
		size = 0;
		head = NULL;
	}

	void add(int item)
	{
		Node *newNode = new Node;
		newNode->setValue(item);
		newNode->setNext(NULL);

		if (size == 0)
		{
			head = newNode;
		}
		else
		{
			Node *node = head;
			while (node->getNext() != NULL)
			{
				node = node->getNext();
			}
			node->setNext(newNode);
		}
		size++;
	}
	void print()
	{
		Node *node = head;
		while (node->getNext() != NULL)
		{
			cout << node->getValue() << endl;
			node = node->getNext();
		}
	}
private:
	int size;
	Node *head;
};


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
