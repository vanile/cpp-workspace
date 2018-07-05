

/**
 * @author Alexander Kimea
 *
 */
class BTNode {
	//The tree node class used by the BST class to build the tree using multiple BTNode classes to form the tree
	private int data;
	private BTNode left;
	private BTNode right;
	
	
	public BTNode (int initialData, BTNode initialLeft, BTNode initialRight) { 
		//constructor, takes a type of initial data, left and right nodes
		data = initialData;
		left = initialLeft;
		right = initialRight;
		
	}
	
	public boolean isLeaf() {
		//checks if a node is a leaf node, if so returns true
		return left == null && right == null;
	}
	
	public int getLeftmostData() { 
		//continues down the leftmost subtree and will return the data of the final leftmost node
		if (left == null) {
			return data;
		} else {
			return left.getLeftmostData();
		}
	}
	
	public int getRightmostData() { 
		//continues down the rightmost subtree and will return the data of the final rightmost node
		if (right == null) {
			return data;
		} else {
			return right.getRightmostData();
		}
	}
	
	public BTNode getLeftmostNode() { 
		//continues down the leftmost subtree and will return the node of the final leftmost node
		if (left == null) {
			return this;
		} else {
			return left.getLeftmostNode();
		}
	}
	
	public BTNode getRightmostNode() { 
		//continues down the rightmost subtree and will return the node of the final rightmost node
		if (right == null) {
			return this;
		} else {
			return right.getRightmostNode();
		}
	}
	
	public BTNode removeLeftmost() {
		// the node that activates removeLeftmost has no left child, thus is itself the leftmost node of the subtree
		if (left == null) {
			return right;
		} else {
			// a recursive call removes the leftmost node from my left subtree
			left = left.removeLeftmost();
			return this;
		}
	}
	
	public BTNode removeRightmost() {
		// the node that activates removeRightmost has no right child, thus is itself the rightmost node of the subtree
		if (right == null) {
			return left;
		} else {
			// a recursive call removes the leftmost node from my left subtree
			right = right.removeRightmost();
			return this;
		}
	}
	
	public static BTNode treeCopy(BTNode source) { 
		//recursively creates a copy of tree starting at a source node by recursively going through the left and right nodes of each node
		BTNode leftCopy, rightCopy;
		if (source == null) {
			return null;
		} else {
			leftCopy = treeCopy(source.getLeft());
			rightCopy = treeCopy(source.getRight());
			return new BTNode(source.getData(), leftCopy, rightCopy);
		}
	}
	
	
	public static int height(BTNode node) { 
		// Height of a node
	  if (node == null)
	    return -1; // it's an empty tree
	  else
	    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}
	
	public static int treeSize(BTNode root) { 
		//gets the number of nodes in the binary true starting from the given root
		if (root == null) {
			return 0;
		} else {
			return 1 + treeSize(root.getLeft()) + treeSize(root.getRight());
		}
	}
	
	public static int countLeaves(BTNode root) { //calculates the number of leaves in a binary tree starting from the given root
		if (root == null) {
			return 0;
		} else if (root.getLeft() == null && root.getRight() == null) {
			return -1;
		} else {
			return countLeaves(root.getLeft()) + countLeaves(root.getRight());
		}
	}

	//below are getters and setters for initial values
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BTNode getLeft() {
		return left;
	}

	public void setLeft(BTNode left) {
		this.left = left;
	}

	public BTNode getRight() {
		return right;
	}

	public void setRight(BTNode right) {
		this.right = right;
	}
	

}