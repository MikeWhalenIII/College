/***********************************************************************
 Michael Whalen
 Project2.java
 COP5416 - Project II

 This class is the starting point to the program with the main method
 that creates a new instance of the Reader and Compute classes.

 This purpose of this program is to add and subtract arbitrarily large integers
 using stacks.
 ************************************************************************/
import java.io.FileNotFoundException;

public class Project2 {

    /**
     * The main method for the Project II program. It creates an
     * instance of the Reader and Compute class.
     *
     * @param args Unused.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("COP5416 - Project II\nMichael Whalen\n");

        // Read the data from the text file
        Reader r = new Reader("addsAndSubtracts.txt");

        // Pass the data as a 2D array
        Compute c = new Compute(r.getTxtFileArray());
    }
}
