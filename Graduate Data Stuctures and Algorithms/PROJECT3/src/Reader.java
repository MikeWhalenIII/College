/***********************************************************************
 Michael Whalen
 Reader.java
 COP5416 - Project III
 This class reads the two text files, words.txt and encoded.txt and then
 stores the results in a 1D and 2D array.
 ************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private final String[][] wordsArray;
    private final String[] encodedArray;

    public Reader(String wordsTxtFile, String encodedTxtFile) {
        wordsArray = new String[25][2];
        encodedArray = new String[5];
        readWordsFile(wordsTxtFile);
        readEncodedFile(encodedTxtFile);
    }

    public String[][] getWordsArray() {
        return wordsArray;
    }

    public String[] getEncodedArray() {
        return encodedArray;
    }

    private void readWordsFile(String wordsTxtFile) {
        Scanner fileReader = null;

        try {
            File textFile = new File(wordsTxtFile);
            fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                for (int i = 0; i < wordsArray.length; i++) {
                    String line = fileReader.nextLine();
                    String[] splitLine = line.split(" ");
                    for (int j = 0; j < 2; j++) {
                        wordsArray[i][j] = splitLine[j].trim();
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Check path.");
        } finally {
            fileReader.close();
        }
    }

    private void readEncodedFile(String encodedTxtFile) {
        Scanner fileReader = null;

        try {
            File textFile = new File(encodedTxtFile);
            fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                for (int i = 0; i < encodedArray.length; i++) {
                    String line = fileReader.nextLine();
                    encodedArray[i] = line;
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
