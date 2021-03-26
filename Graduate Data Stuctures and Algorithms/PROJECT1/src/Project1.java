/***********************************************************************
 Michael Whalen
 Project1.java
 COP5416 - Project I

 This class is the starting point to the program with the main method that
 creates a new instance of the Reader and Compute classes.
 ************************************************************************/

import java.io.FileNotFoundException;

public class Project1 {

    /**
     * The main method for the Project I program. It creates instances of the
     * Reader and Compute class.
     *
     * @param args Unused.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("COP5416 - Project I\nMichael Whalen");

        Reader r = new Reader("dailyScores.txt");

        Compute c = new Compute(r.getDatesArray(), r.getScoreArray());
        c.computeMeanAndSD();
        c.computeDifferences();
    }
}
