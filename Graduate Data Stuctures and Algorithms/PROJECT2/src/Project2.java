import java.io.FileNotFoundException;
import java.util.Arrays;

/***********************************************************************
 Michael Whalen
 Project2.java
 COP5416 - Project II
 ************************************************************************/

public class Project2 {

    public static void main(String[] args) throws FileNotFoundException {
        // Display welcome message
        System.out.println("COP5416 - Project II\nMichael Whalen");

        Reader r = new Reader("addsAndSubtracts.txt");

        // Print 2D array for debugging.
        System.out.println(Arrays.deepToString(r.getTxtFileArray()));

        Compute c = new Compute(r.getTxtFileArray());
    }
}
