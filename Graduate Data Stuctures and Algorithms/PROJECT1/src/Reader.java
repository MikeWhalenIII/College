import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private final String filename;

    private final String[] datesArray;
    private final double[] scoreArray;

    public Reader(String filename) throws FileNotFoundException {
        datesArray = new String[5];
        scoreArray = new double[120];
        this.filename = filename;
        readFile();
    }

    public String[] getDatesArray() {
        return datesArray;
    }

    public double[] getScoreArray() {
        return scoreArray;
    }

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
