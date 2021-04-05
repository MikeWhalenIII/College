/***********************************************************************
 Michael Whalen
 Reader.java
 COP5416 - Project II


 ************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private final String filename;
    private final String[][] txtFileArray;

    public Reader(String filename) throws FileNotFoundException {
        txtFileArray = new String[18][3];
        this.filename = filename;
        readFile();
    }

    public String[][] getTxtFileArray() {
        return txtFileArray;
    }

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