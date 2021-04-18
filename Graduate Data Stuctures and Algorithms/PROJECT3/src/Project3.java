/***********************************************************************
 Michael Whalen
 Project3.java
 COP5416 - Project III
 This class is the starting point to the program with the main method
 that creates an instance of the Reader class to read the two text files
 words.txt and encoded.txt.

 This method also creates a new Binary tree and then inserts the words from
 words.txt into the tree. Next, the main method calls the search method in
 the binary tree to decode the lines in encoded.txt.
 ************************************************************************/

public class Project3 {

    /**
     * The main method for the Project III program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Display welcome message
        System.out.println("COP5416 - Project III\nMichael Whalen\n");

        // Read the data from the two text files
        Reader r = new Reader("words.txt", "encoded.txt");

        String[][] wordsArr = r.getWordsArray();
        String[] encodedArr = r.getEncodedArray();

        // Create a new Binary Tree
        BinaryTree tree = new BinaryTree();

        // Insert words into the tree
        for (String[] strings : wordsArr) {
            tree.insert(strings[0], strings[1]);
        }

        // Decode messages
        for (String s : encodedArr) {
            System.out.println(tree.search(s));
        }
    }
}
