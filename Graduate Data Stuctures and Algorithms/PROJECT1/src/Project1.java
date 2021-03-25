import java.io.FileNotFoundException;

public class Project1 {

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("COP5416 - Project I\nMichael Whalen");

        Reader r = new Reader("dailyScores.txt");

        Compute c = new Compute(r.getDatesArray(), r.getScoreArray());
        c.computeMeanAndSD();
        c.computeDifferences();
    }
}
