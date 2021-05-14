/***********************************************************************
 Michael Whalen
 Project4.java
 COP5416 - Project IV
 This class is the starting point to the program with the main method
 that creates an instance of the Reader class to read the two text files
 Dictionary.txt and testTextFile.txt.

 This method also creates a new HashTable and then imports the words from
 the dictionary file into the hashtable. Next, the method creates an
 instance of the SpellChecker class and checks the testTextFile.txt
 for spelling errors.
 ************************************************************************/

import java.util.ArrayList;

public class Project4 {

    public static void main(String[] args) {

        System.out.println("==================================\nInput\n==================================\n");
        // Read the Dictionary and Test file
        Reader r = new Reader("Dictionary.txt", "testTextFile.txt");

        // Get Dictionary Array and import them into hash table
        String[] dictionaryArr = r.getDictionaryArray();

        // Get Test file ArrayList
        ArrayList<String> testFileArrList = r.getTestFileArray();

        // Create new HashTable
        HashTable dictionary = new HashTable((4 * dictionaryArr.length) + 1);

        // Import dictionary into HashTable
        for (String value : dictionaryArr) {
            dictionary.insert(value);
        }

        System.out.println("==================================\nSpelling Checker\n==================================\n");
        // Spell check the test file
        SpellChecker sc = new SpellChecker(dictionary);
        for (String s : testFileArrList) {
            sc.check(s);
        }
    }
}
