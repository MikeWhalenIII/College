/***********************************************************************
 Michael Whalen
 Compute.java
 COP5416 - Project II

 ************************************************************************/

public class Compute {
    String[][] input = new String[18][3];
    Stack o1Stack, o2Stack, resultStack;
    boolean carry = false;
    boolean borrow = false;

    public Compute(String[][] input) {
        this.input = input;
        o1Stack = new Stack();
        o2Stack = new Stack();
        resultStack = new Stack();
        compute();
    }

    private void compute() {
        for (int i = 0; i < input.length; i++) {



            // Display results
            System.out.printf("%6s\n", input[i][0]);
            System.out.printf("%-2s ", input[i][1]);
            System.out.printf("%-2s\n", input[i][2]);
            System.out.println("-------------");
            System.out.println("ANSWER\n");
        }
    }
}