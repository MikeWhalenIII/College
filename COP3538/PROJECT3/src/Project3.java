import java.io.*;

/**
 * COP 3538: Project 3 – Binary Search Trees - Summer 2019
 * <p>
 * This class is the starting point to the program with the main method. There
 * is no user input for this program, you only need to provide a CSV file for
 * the program to use.<br>
 * 
 * 1. First, the program will read a CSV file (States3.csv), then import the
 * states into a Binary Search Tree(BST).<br>
 * 
 * 2. The program will inorder traverse the BST displaying all of the states
 * imported.<br>
 * 
 * 3. California, Florida, and New York will be deleted from the BST and the
 * remaining states will be displayed using a preorder traversal.<br>
 * 
 * 4. The program will search for American Samoa, Rhode Island and Florida.
 * Showing the results and the number of nodes visited during the search.<br>
 * 
 * 5. U.S. Virgin Islands, Wyoming, West Virginia, and New Mexico are
 * deleted.<br>
 * 
 * 6. The program will then postorder traverse the entire BST, printing out the
 * remaining states.<br>
 * 
 * 7. The program will then print the five minimal populations and the five
 * maximal populations. (These two task are accomplished using a Priority
 * Queue)<br>
 * <p>
 * 
 * @author Michael Whalen (n01425161)
 * @version 1.0, 06/28/2019
 *
 */

public class Project3 {

	// Create the Binary Search Tree.
	private static BinarySearchTree tree = new BinarySearchTree();

	/**
	 * The main method for the program. The following things happen in this main
	 * method: - The BST is populated from the States3.csv file.<br>
	 * - The BST is displayed using inorder traversal.<br>
	 * - California, Florida, and New York are deleted.<br>
	 * - The BST is displayed using preorder traversal.<br>
	 * - A search is run, looking for American Samoa, Rhode Island, and
	 * Florida.<br>
	 * - U.S. Virgin Islands, Wyoming, West Virginia, and New Mexico are
	 * deleted.<br>
	 * - The BST is displayed using postorder traversal.<br>
	 * - The data in the BST is pushed into a Priority Queue.<br>
	 * - The five minimum population states are displayed.<br>
	 * - The five maximum population states are displayed.<br>
	 * 
	 * @param args
	 *            Unused.
	 * @throws IOException
	 *             For the file I/O.
	 */
	public static void main(String[] args) throws IOException {

		// Call the method to read the file and input the data into a BST.
		int numStates = readFile();

		// Display welcome message
		welcomeMessage(numStates);

		// Print BST (Inorder Traversal)
		System.out.println("Inorder Traversal:\n");
		System.out.printf("%-25s %-15s", "State Name", "State Population");
		System.out.println("\n------------------------------------------");
		tree.printInorder(tree.root);

		// Delete California, Florida, and New York
		tree.delete("California");
		System.out.println("\nCalifornia has been deleted from tree");
		tree.delete("Florida");
		System.out.println("Florida has been deleted from tree");
		tree.delete("New York");
		System.out.println("New York has been deleted from tree");

		// Print BST (Preorder Traversal)
		System.out.println("\nPreorder Traversal:\n");
		System.out.printf("%-25s %-15s", "State Name", "State Population");
		System.out.println("\n------------------------------------------");
		tree.printPreorder(tree.root);

		// Find American Samoa
		if (tree.find("American Samoa") >= 0)
			System.out.printf("%s %,d\n", "\nAmerican Samoa is found with a population of",
					tree.find("American Samoa"));
		else
			System.out.println("American Samoa is not found");
		System.out.println(BinarySearchTree.nodesVisited + " nodes visited");

		// Find Rhode Island
		if (tree.find("Rhode Island") >= 0)
			System.out.printf("%s %,d\n", "\nRhode Island is found with a population of", tree.find("Rhode Island"));
		else
			System.out.println("Rhode Island is not found");
		System.out.println(BinarySearchTree.nodesVisited + " nodes visited\n");

		// Find Florida
		if (tree.find("Florida") >= 0)
			System.out.printf("%s %,d\n", "\nFlorida is found with a population of", tree.find("Florida"));
		else
			System.out.println("Florida is not found");
		System.out.println(BinarySearchTree.nodesVisited + " nodes visited");

		// Delete U.S. Virgin Islands, Wyoming, West Virginia, and New Mexico
		tree.delete("U.S. Virgin Islands");
		System.out.println("\nU.S. Virgin Islands has been deleted from tree");
		tree.delete("Wyoming");
		System.out.println("Wyoming has been deleted from tree");
		tree.delete("West Virginia");
		System.out.println("West Virginia has been deleted from tree");
		tree.delete("Mexico");
		System.out.println("Mexico has been deleted from tree");

		// Print BST (Postorder Traversal)
		System.out.println("\nPostorder Traversal:\n");
		System.out.printf("%-25s %-15s", "State Name", "State Population");
		System.out.println("\n------------------------------------------");
		tree.printPostorder(tree.root);

		// Send Binary Search Tree TO Priority Queue
		tree.sendBST2PQ(tree.root);

		// Display the 5 min and 5 max population states.
		tree.printFiveMin();
		tree.printFiveMax();

	}

	/**
	 * This method displays a welcome mes1sage to the user with the number of
	 * records imported.
	 * 
	 * @param numNodes
	 *            takes in the number of records read in from the csv file.
	 */
	private static void welcomeMessage(int numNodes) {
		System.out.println("COP3538 Project 3 - Michael Whalen\n" + "Instructor: Xudong Liu\n\n"
				+ "Binary Search Trees\n\n" + "There were " + numNodes + " records put on the binary search tree.\n");
	}

	/**
	 * This method reads the data from the specified CSV file. Then inserts the
	 * states into a Binary Search Tree.
	 * 
	 * @return Returns the number of states imported from the csv file.
	 * @throws IOException
	 *             Throws error if the cannot be read or cannot be found.
	 */
	private static int readFile() throws IOException {
		int i = 0;

		try {
			BufferedReader readCSV = new BufferedReader(new FileReader("States3.csv"));

			String fileRead = readCSV.readLine();

			while ((fileRead = readCSV.readLine()) != null) {
				String[] field = fileRead.split(",");

				String tempStateName = field[0];
				int tempPopulation = Integer.parseInt(field[3]);
				{
					tree.insert(tempStateName, tempPopulation);
					i++;
				}
			}
			readCSV.close(); // Close the reader

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return i; // return the number of records imported.
	}
}
