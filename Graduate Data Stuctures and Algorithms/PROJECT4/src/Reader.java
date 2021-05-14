/***********************************************************************
 Michael Whalen
 Reader.java
 COP5416 - Project IV
 This class reads the Dictionary.txt and testTextFile.txt files and then
 returns an array of dictionary words and an ArrayList of the testTextFile.
 ************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {

    private final String[] dictionaryArray;
    private final ArrayList<String> testFileArray;

    /**
     * Constructor
     *
     * @param dictionaryFile dictionary txt file
     * @param testFile       test txt file
     */
    public Reader(String dictionaryFile, String testFile) {
        dictionaryArray = new String[61353];
        testFileArray = new ArrayList();
        readDictionaryFile(dictionaryFile);
        readTestFile(testFile);
    }

    /**
     * Returns an array of words found in the dictionary file.
     *
     * @return dictionary array
     */
    public String[] getDictionaryArray() {
        return dictionaryArray;
    }

    /**
     * Returns the contents of the testTextFile as an ArrayList
     *
     * @return test file array
     */
    public ArrayList<String> getTestFileArray() {
        return testFileArray;
    }

    /**
     * This method reads the dictionary.txt file and creates an array
     * from the file contents.
     *
     * @param dictionaryFile The path to the dictionary.txt file
     */
    private void readDictionaryFile(String dictionaryFile) {
        Scanner fileReader = null;

        try {
            File textFile = new File(dictionaryFile);
            fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                for (int i = 0; i < dictionaryArray.length; i++) {
                    String line = fileReader.nextLine();
                    dictionaryArray[i] = line.toLowerCase().trim();
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Check path.");
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

    /**
     * This methods reads the test file and saves the content
     * in an ArrayList.
     *
     * @param testFile The path to the testTextFile.txt
     */
    private void readTestFile(String testFile) {
        Scanner fileReader = null;

        try {
            File textFile = new File(testFile);
            fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // Print test input
                System.out.println(line + "\n");

                // Remove punctuation from test file and convert everything to lowercase
                String linePunctRemoved = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();

                // Split the line into an array.
                String[] splitLine = linePunctRemoved.split(" ");

                testFileArray.addAll(Arrays.asList(splitLine));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Check path.");
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }


}
