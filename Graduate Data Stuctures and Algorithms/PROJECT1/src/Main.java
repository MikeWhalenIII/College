import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("Welcome! COP5416 - Project I");

        // Read the txt file
        Reader r = new Reader("dailyScores.txt");

        Compute c = new Compute(r.getDatesArray(), r.getScoreArray());
        c.computeMeanAndSD();
        c.computeDifferences();
    }
}
