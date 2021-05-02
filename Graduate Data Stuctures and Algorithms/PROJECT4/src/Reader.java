/***********************************************************************
 Michael Whalen
 Reader.java
 COP5416 - Project IV
 This class reads
 ************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    private final String[] dictionaryArray;
    private final ArrayList<String> testFileArray;

    public Reader(String dictionaryFile, String testFile) {
        dictionaryArray = new String[61353];
        testFileArray = new ArrayList();
        readDictionaryFile(dictionaryFile);
        readTestFile(testFile);
    }

    /**
     *
     * @return
     */
    public String[] getDictionaryArray() {
        return dictionaryArray;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getTestFileArray() {
        return testFileArray;
    }


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
            fileReader.close();
        }
    }

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

                for (int i = 0; i < splitLine.length; i++) {
                    testFileArray.add(splitLine[i]);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Check path.");
        } finally {
            fileReader.close();
        }
    }


}
