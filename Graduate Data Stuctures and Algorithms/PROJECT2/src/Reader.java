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
    private final String[] datesArray;
    private final double[] scoreArray;

    /**
     * Constructor
     * @param filename The filename for the text file.
     * @throws FileNotFoundException If the file is not found.
     */
    public Reader(String filename) throws FileNotFoundException {
        datesArray = new String[5];
        scoreArray = new double[120];
        this.filename = filename;
        readFile();
    }

    /**
     * A getter method that returns an array with the dates.
     * @return An array filled with the dates.
     */
    public String[] getDatesArray() {
        return datesArray;
    }

    /**
     * A getter method that returns an array with the scores.
     * @return An array filled with the scores.
     */
    public double[] getScoreArray() {
        return scoreArray;
    }

    /**
     * This method reads the text file and extracts the data into a
     * dates array and a scores array.
     * @throws FileNotFoundException Thrown if the file is not found.
     */
    private void readFile() throws FileNotFoundException {
        Scanner fileReader = null;

        try {
            File dailyScoresTxt = new File(filename);
            fileReader = new Scanner(dailyScoresTxt);

            int count = 0;
            while (fileReader.hasNextLine()) {
                // Extract the 5 dates and 120 scores.
                if (count < 5) {
                    for (int i = 0; i < datesArray.length; i++) {
                        datesArray[i] = fileReader.nextLine();
                        count++;
                    }
                } else {
                    for (int j = 0; j < scoreArray.length; j++) {
                        scoreArray[j] = Double.parseDouble(fileReader.nextLine());
                        count++;
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