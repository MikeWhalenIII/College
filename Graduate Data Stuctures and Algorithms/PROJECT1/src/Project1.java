import java.io.FileNotFoundException;

public class Project1 {

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("Welcome! COP5416 - Project I");

        Reader r = new Reader("dailyScores.txt");

        Compute c = new Compute(r.getDatesArray(), r.getScoreArray());
        c.computeMeanAndSD();
        c.computeDifferences();
    }
}
