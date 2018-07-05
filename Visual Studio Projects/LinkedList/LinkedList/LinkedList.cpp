#include <iostream>
#include "stdafx.h"
#include "Node.cpp"

using namespace std;

class LinkedList
{
public:
	LinkedList()
	{
		size = 0;
		head = NULL;
		head->setNext(NULL);
	}

	void add(int item)
	{
		Node *newNode = new Node;
		newNode->setValue(item);
		newNode->setNext(NULL);

		if (size = 0)
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


