
public class BinarySearchTree {

	public Node root; // Reference to the root
	public static int nodesVisited; // Used to count the number of nodes visited
									// during the find method.

	public BinarySearchTree() {
		root = null;
	}

	public void insert(String state, int population) {
		Node newNode = new Node(state, population);
		newNode.stateName = state;
		newNode.statePopulation = population;

		if (root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (state.compareTo(current.stateName) < 0) {
					current = current.leftChild; // Go left
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild; // Go right
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}

	}

	public int find(String state) {
		nodesVisited = 1;
		Node current = root;

		while (!current.stateName.equals(state)) {
			if (state.compareTo(current.stateName) < 0) {
				current = current.leftChild; // Go left
				nodesVisited++;
			} else {
				current = current.rightChild; // Go right
				nodesVisited++;
			}
			if (current == null) {
				return -1;
			}
		}
		return current.statePopulation;
	}

	public void delete(String state) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while (!current.stateName.equals(state)) {
			parent = current;
			if (state.compareTo(current.stateName) < 0) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return;
		}
		// If no children, delete the node
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		// If no right child, replace with the left subtree
		else if (current.rightChild == null)
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;

		// If no left child, replace with the right subtree
		else if (current.leftChild == null)
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		
		// The node to be deleted has two children
		else {
			Node successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = current.leftChild;
		}

	}

	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;

		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void printInorder(Node localRoot) {
		if (localRoot != null) {
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
		}
	}

	public void printPreorder(Node localRoot) {
		if (localRoot != null) {
			localRoot.printNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
	}

	public void printPostorder(Node localRoot) {
		if (localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}

	public void printFiveMin() {

	}

	public void printFiveMax() {

	}

	/**
	 * This class definition is used for the nodes in the binary search tree.
	 * 
	 * @author Michael Whalen (n01425161)
	 * @version 1.0, 06/17/2019
	 */
	private class Node {
		String stateName;
		int statePopulation;
		Node leftChild;
		Node rightChild;

		public Node(String state, int population) {
			stateName = state;
			statePopulation = population;
		}

		public void printNode() {
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);
		}
	}

}
