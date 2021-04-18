/***********************************************************************
 Michael Whalen
 Project3.java
 COP5416 - Project III
 This class is the starting point to the program with the main method
 that creates
 ************************************************************************/

import java.util.Arrays;

public class Project3 {

    public static void main(String[] args) {
        // Display welcome message
        System.out.println("COP5416 - Project III\nMichael Whalen\n");

        // Read the data from the two text files
        Reader r = new Reader("words.txt", "encoded.txt");

        String[][] wordsArr = r.getWordsArray();
        String[] encodedArr = r.getEncodedArray();

        // Debug output
        System.out.println(Arrays.deepToString(wordsArr));
        System.out.println(Arrays.toString(encodedArr));

        // Create a new Binary Tree
        BinaryTree tree = new BinaryTree();

        // Insert words into Tree
        for (int i = 0; i < wordsArr.length; i++) {
            tree.insert(wordsArr[i][0], wordsArr[i][1]);
        }
    }
}
