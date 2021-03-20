import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String SCORES_FILE = "dailyScores.txt";
    private static String[] datesArray = new String[5];
    private static double[] scoreArray = new double[120];

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        welcomeMessage();

        // Read the txt file
        readFile();
        computeMeanAndSD();

        //System.out.println(Arrays.toString(datesArray));
        //System.out.println(Arrays.toString(scoreArray));

    }

    private static void welcomeMessage() {
        System.out.println("Welcome! COP5416 - Project I");
    }

    private static void readFile() throws FileNotFoundException {
        Scanner fileReader = null;

        try {
            File dailyScoresTxt = new File(SCORES_FILE);
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
                    for(int j = 0; j < scoreArray.length; j++) {
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

    private static void computeMeanAndSD() {
        System.out.println("\nMeans and Standard Deviations of Scores\n");
        System.out.printf("%-7s %-9s %-9s %-9s %-9s %-9s\n", "Student", datesArray[0], datesArray[1], datesArray[2], datesArray[3], datesArray[4]);

        // Display score data
        for (int i = 0; i < 24; i++) {
            System.out.printf("%-10d %-9.0f %-9.0f %-9.0f %-9.0f %-9.0f\n", (i + 1), scoreArray[i], scoreArray[i + 24], scoreArray[i + 48], scoreArray[i + 72], scoreArray[i + 96]);
        }

        // Compute and display the mean
        double mean1 = 0, mean2 = 0, mean3 = 0, mean4 = 0, mean5 = 0;

        for (int i = 0; i < scoreArray.length; i++) {
            if (i < 24) {
                mean1 += scoreArray[i];
            } else if (i > 24 && i < 48) {
                mean2 += scoreArray[i];
            } else if (i > 48 && i < 72) {
                mean3 += scoreArray[i];
            } else if (i > 72 && i < 96) {
                mean4 += scoreArray[i];
            } else if (i > 96 && i < 120) {
                mean5 += scoreArray[i];
            }
        }
        mean1 = mean1 / 24;
        mean2 = mean2 / 24;
        mean3 = mean3 / 24;
        mean4 = mean4 / 24;
        mean5 = mean5 / 24;

        System.out.println("************************************************************");
        System.out.printf("%-10s %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f\n", "Mean", mean1, mean2, mean3, mean4, mean5);

        // Compute and display the Standard Deviation
        double sd1 = 0, sd2 = 0, sd3 = 0, sd4 = 0, sd5 = 0;

        
    }
}
