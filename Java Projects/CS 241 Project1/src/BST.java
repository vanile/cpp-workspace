
import java.util.ArrayList;

/**
 * @author Alexander Kimea
 *
 */
class BST { 
	//The binary search tree class which has the functions pertaining to the tree such as inserting, remove, printing traversals, and finding an element
	private BTNode root;
	
	public BST() {
		root = null;
	}
	
	public BTNode getRoot() {
		return root;
	}

	public void setRoot(BTNode root) {
		root = null;
	}

	public void add(int element) { 
		//calls the recursive add method, uses 2 method because add() is used for the element input and the second one is used to recursively call itself with
		// a node input and output
		root = addElement(element, root);
	}
	public BTNode addElement(int element, BTNode node) { 
		//recursive add (insert) method to add an element to the tree
		BTNode newN = new BTNode(element, null, null);
		if (node == null) {
			return newN;
		} else if (node.getData() >= element) { //if element is less than or equal then it will call the function on the node's left child
			node.setLeft(addElement(element, node.getLeft()));
		} else {//right child recursive function
			node.setRight(addElement(element, node.getRight()));
		} 
		return node;
	}
	
	public BTNode findElement(int element, BTNode root) { 
		//recursive method used to find the node of a given element
		if (root != null){
			if (element == root.getData()) {
				return root;
			} else {
				BTNode newNode = findElement(element, root.getLeft());
				if (newNode == null) {
					newNode = findElement(element, root.getRight());
				}
				return newNode;
			}
		} else {
			return null;
		}
	}
	
	
	public BTNode remove(int target, BTNode node) {
		//this method uses the target value and the target's node. Checks the node's child to see if
		//they are null in order to switch the removed node with a leaf node if needed. 
		if (node == null) {
			return node;
		} else if (node.getData() == target) {
			//first checks if each individual child is null
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				//if both are not null
				node.setLeft(removeTarget(node.getLeft(), node));//finding element of node's left subtree
				return node;
			}
		} else {
			//did not find value yet
			if (node.getData() > target) {
				node.setLeft(remove(target, node.getLeft()));
			} else {
				node.setRight(remove(target, node.getRight()));
			}
			return node;
		}
	}
	
	public BTNode removeTarget(BTNode node, BTNode parent) {
		//looks for the node of removal's rightmost node of its left subtree
		if (node.getRight() == null) {
			parent.setData(node.getData());
			return node.getLeft();
		} else {
			node.setRight(removeTarget(node.getRight(), parent));
			return node;
		}
	}
	
	public void printPreOrder(BTNode root) { 
		//this will recursively call itself for each node given the tree's root and print the tree in Pre-Order
		if (root == null) {
			
		} else {
			System.out.print(root.getData() + " ");
			printPreOrder(root.getLeft());
			printPreOrder(root.getRight());
		}
	}
	
	public void printInOrder(BTNode root) { 
		//this recursively calls itself given the root of the tree and prints the tree in In-order
		if (root == null) {
			
		} else {
			printInOrder(root.getLeft());
			System.out.print(root.getData() + " ");
			printInOrder(root.getRight());
		}
		
	}
	
	public void printPostOrder(BTNode root) { 
		//recursively calls itself on the left and right nodes of the root to print each node, then prints the root at the end
		if (root == null) {
			
		} else {
			printPostOrder(root.getLeft());
			printPostOrder(root.getRight());
			System.out.print(root.getData() + " ");
		}
	}
	
	public void printTraversals(BTNode root) {
		//this method simply combines the three traversal printing to one and prints them all out.
		System.out.print("Pre-order: ");
		printPreOrder(root);
		System.out.println();
		
		System.out.print("In-order: ");
		printInOrder(root);
		System.out.println();
		
		System.out.print("Post-order: ");
		printPostOrder(root);
		System.out.println();
	}
	
	public int findPredecessor(int element) { 
		//this method will find the predacssor node of a given integer and return its integer value.
		//This and the next method recursively uses the findElement() method to recursively search the tree
		if (findElement(element, root) == null) {
			return findElement(element, root).getData();
		} else if (findElement(element, root).isLeaf() == true){
			return findElement(element, root).getData();
		} else {
			if (findElement(element, root).getRight() != null) {
				return findElement(element, root).getRight().getLeftmostData();
			} else {
				return root.getData();
			}
			
		}
	}
	
	public int findSuccessor(int element) {
		//this method will find the successor node of a given integer and return its integer value
		if (findElement(element, root) == null) {
			return -1;
		} else if (findElement(element, root).isLeaf() == true){
			return findElement(element, root).getData();
		} else {
			if (findElement(element, root).getLeft() != null) {
				return findElement(element, root).getLeft().getRightmostData();
			} else {
				return root.getData();
			}
			
		}
	}

}
