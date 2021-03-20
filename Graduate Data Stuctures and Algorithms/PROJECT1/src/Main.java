import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String[] datesArray = new String[5];
    private static double[] scoreArray = new double[120];

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        welcomeMessage();

        readFile();

        System.out.println(Arrays.toString(datesArray));
        System.out.println(Arrays.toString(scoreArray));

    }

    private static void welcomeMessage() {
        System.out.println("Welcome! COP5416 - Project I");
    }

    private static void readFile() throws FileNotFoundException {
        //Scanner fileReader = null;

        try {
            File dailyScoresTxt = new File("dailyScores.txt");
            Scanner fileReader = new Scanner(dailyScoresTxt);

            int count = 0;
            while (fileReader.hasNextLine()) {
                // Extract the 5 dates
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
        }
    }
}
