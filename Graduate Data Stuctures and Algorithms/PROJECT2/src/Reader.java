/***********************************************************************
 Michael Whalen
 Reader.java
 COP5416 - Project II

 This class reads the text file and extracts the expression to a 2D array.
 ************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private final String filename;
    private final String[][] txtFileArray;

    /**
     * Constructor
     *
     * @param filename The file to be read.
     * @throws FileNotFoundException If the file is not found.
     */
    public Reader(String filename) throws FileNotFoundException {
        txtFileArray = new String[18][3];
        this.filename = filename;
        readFile();
    }

    /**
     * Returns the text file data in a 2D array.
     * @return 2D array of text file data.
     */
    public String[][] getTxtFileArray() {
        return txtFileArray;
    }

    /**
     * The method reads the text file.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private void readFile() throws FileNotFoundException {
        Scanner fileReader = null;

        try {
            File textFile = new File(filename);
            fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                for (int i = 0; i < txtFileArray.length; i++) {
                    String line = fileReader.nextLine();
                    String[] splitLine = line.split(" ");
                    for (int j = 0; j < 3; j++) {
                        txtFileArray[i][j] = splitLine[j].trim();
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
}