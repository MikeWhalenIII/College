/**
 * This class is used to implement the Binary Search Tree. When states are
 * imported into the BST, they are organized by their state names.
 * 
 * @author Michael Whalen (n01425161)
 * @version 1.0, 06/28/2019
 */
public class BinarySearchTree {

	public Node root; // Reference to the root
	public static int nodesVisited; // Used to count the number of nodes visited
									// during the find method.
	public Node largestPopulation = null;

	// Create a Priority Queue to be used to find the five min/max population
	// states.
	PriorityQueue stateQueue = new PriorityQueue(50);

	/**
	 * Constructor
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Used to insert states into the Binary Search Tree based off of their
	 * state names. The two parameters passed to the insert method are used to
	 * create a new node object.
	 * 
	 * @param state
	 *            The state name for the state
	 * @param population
	 *            The state population for the state.
	 */
	public void insert(String state, int population) {
		Node newNode = new Node(state, population);

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

	/**
	 * This method will search for a given state name and return the states
	 * population or -1 if the state is not found.
	 * 
	 * @param state
	 *            The state name you would like to find.
	 * @return The states population, or -1 if the state is not found.
	 */

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

	/**
	 * This method will delete a state from the Binary Search Tree that matched
	 * the provided key.
	 * 
	 * @param state
	 *            The name of the state you would like to remove from the BST.
	 */

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

	/**
	 * This method is used by the delete() method to find the successor of the
	 * node to be deleted.
	 * 
	 * @param delNode
	 *            The node that is to be deleted.
	 * @return The successor.
	 */

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

	/**
	 * This method will inorder traverse the entire tree using recursion and
	 * print every node along the way.
	 * 
	 * @param localRoot
	 *            the current root.
	 */

	public void printInorder(Node localRoot) {
		if (localRoot != null) {
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
		}
	}

	/**
	 * This method will preorder traverse the entire tree using recursion and
	 * print every node along the way.
	 * 
	 * @param localRoot
	 *            the current root.
	 */

	public void printPreorder(Node localRoot) {
		if (localRoot != null) {
			localRoot.printNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
	}

	/**
	 * This method will postorder traverse the entire tree using recursion and
	 * print every node along the way.
	 * 
	 * @param localRoot
	 *            the current root.
	 */

	public void printPostorder(Node localRoot) {
		if (localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}

	/**
	 * This method will print the five minimum population states from the
	 * priority queue.
	 */

	public void printFiveMin() {
		System.out.println("\nStates with five minimal populations\n");
		System.out.printf("%-25s %-15s", "State Name", "State Population");
		System.out.println("\n------------------------------------------");
		stateQueue.printFiveMinFromQ();
	}

	/**
	 * This method will print the five maximum population states from the
	 * priority queue.
	 */

	public void printFiveMax() {
		System.out.println("\nStates with five maximal populations\n");
		System.out.printf("%-25s %-15s", "State Name", "State Population");
		System.out.println("\n------------------------------------------");
		stateQueue.printFiveMaxFromQ();
	}

	/**
	 * This method will take a Binary Search Tree and insert the entire tree
	 * into a Priority Queue using recursion.
	 * 
	 * @param localRoot
	 *            The current root.
	 */

	public void sendBST2PQ(Node localRoot) {
		if (localRoot != null) {
			sendBST2PQ(localRoot.leftChild);
			stateQueue.insert(localRoot);
			sendBST2PQ(localRoot.rightChild);
		}
	}

	/**
	 * This class is used to implement a priority queue using an array which
	 * will be used to sort the states from the BST into order based off of
	 * their populations.
	 * 
	 * @author Michael Whalen (n01425161)
	 * @version 1.0, 06/28/2019
	 */

	private class PriorityQueue {
		private int maxSize;
		private Node[] stateQueue;
		private int numItems;

		/**
		 * Constructor
		 * 
		 * @param max
		 *            The size of the array.
		 */
		private PriorityQueue(int max) {
			maxSize = max;
			stateQueue = new Node[maxSize];
			numItems = 0;
		}

		/**
		 * This method will insert the states into the priority queue based off
		 * of their population.
		 * 
		 * @param state
		 *            The state.
		 */

		private void insert(Node state) {
			int i;

			if (numItems == 0) {
				stateQueue[0] = state;
				numItems++;
			} else {
				for (i = numItems - 1; i >= 0; i--) {

					if (state.statePopulation < stateQueue[i].statePopulation) {
						stateQueue[i + 1] = stateQueue[i];
					} else {
						break;
					}
				}
				stateQueue[i + 1] = state;
				numItems++;
			}
		}
		
		/**
		 * This method prints the first five items from the front of the queue.
		 */

		private void printFiveMaxFromQ() {
			for (int i = numItems - 1; i >= numItems - 5; i--) {
				if (stateQueue[i] != null)
					System.out.printf("%-25s%,10d\n", stateQueue[i].stateName, stateQueue[i].statePopulation);
			}
		}

		/**
		 * This method prints the first five items in back of the queue.
		 */
		
		private void printFiveMinFromQ() {
			for (int i = 0; i < 5; i++) {
				System.out.printf("%-25s%,10d\n", stateQueue[i].stateName, stateQueue[i].statePopulation);
			}
		}
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

		/**
		 * Constructor
		 * 
		 * @param state The state name
		 * @param population The state population
		 */
		public Node(String state, int population) {
			stateName = state;
			statePopulation = population;
		}

		/**
		 * This method will print a nodes state name and population.
		 */
		public void printNode() {
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);
		}
	}
}
