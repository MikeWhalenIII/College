import java.io.*;

public class Project3 {

	private static BinarySearchTree tree = new BinarySearchTree();

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
		
		System.out.println("====");
		tree.printFiveMax(tree.root);

	}

	/**
	 * This method displays a welcome message to the user with the number of
	 * records imported.
	 * 
	 * @param numRecords
	 *            takes in the number of records read in from the csv file.
	 */
	private static void welcomeMessage(int numNodes) {
		System.out.println("COP3538 Project 3 - Michael Whalen\n" + "Instructor: Xudong Liu\n\n"
				+ "Binary Search Trees\n\n" + "There were " + numNodes + " records put on the binary search tree.\n");
	}

	/**
	 * This method reads the data from the specified CSV file. Then pushes the
	 * states onto the stack.
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
