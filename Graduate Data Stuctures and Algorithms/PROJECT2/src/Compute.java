/***********************************************************************
 Michael Whalen
 Compute.java
 COP5416 - Project II

 ************************************************************************/

public class Compute {
    String[][] input;

    public Compute(String[][] input) {
        this.input = input;
        compute();
    }

    private void compute() {
        Stack o1Stack = new Stack();
        Stack o2Stack = new Stack();

        boolean o1IsNegative = false;
        boolean o2IsNegative = false;
        boolean o1IsLargest = false;
        boolean o2IsLargest = false;

        for (int i = 0; i < input.length; i++) {
            int o1Length = 0, o2Length = 0;
            String operand1 = input[i][0];
            String operand2 = input[i][2];
            String operand1Before = input[i][0];
            String operand2Before = input[i][2];
            String operator = input[i][1];

            /* Check to see if operand 1 or operand 2 is a negative number
               and remove the negative sign if one exist. */
            if (operand1.contains("-")) {
                o1IsNegative = true;
                operand1 = operand1.substring(1); // remove negative sign.
            } if(operand2.contains("-")) {
                o2IsNegative = true;
                operand2 = operand2.substring(1); // remove negative sign.
            }

            // Check to see which operand is the largest.
            if (operand1.length() < operand2.length()) {
                o1IsLargest = false;
                o2IsLargest = true;
                operand1 = padZeros(operand1, operand2.length() + 1);
                operand2 = padZeros(operand2, operand2.length() + 1);
            } else {
                o1IsLargest = true;
                o2IsLargest = false;
                operand1 = padZeros(operand1, operand1.length() + 1);
                operand2 = padZeros(operand2, operand1.length());
            }

            /*System.out.printf("%6s\n", operand1Before);
            System.out.printf("%-2s ", operator);
            System.out.printf("%-2s\n", operand2Before);
            System.out.println("-------------");*/

            System.out.printf("%7s\n", operand1);
            System.out.printf("%-2s ", operator);
            System.out.printf("%-2s\n", operand2);
            System.out.println("-------------");


            // Push operands to their stacks.
            for (int j = 0; j < operand1.length(); j++) {
                o1Stack.push(operand1.charAt(j));
                o2Stack.push(operand2.charAt(j));
            }

            // Add or Subtract stacks
            if (operator.contains("+")) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest));
            } else {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest));
            }
        }
    }

    private String padZeros(String s, int c) {
        int sLen = s.length();
        while (sLen < c) {
            sLen++;
            s = '0' + s;
        }
        return s;
    }

    private Stack add(Stack o1Stack, Stack o2Stack, boolean o1IsNegative, boolean o2IsNegative,
                      boolean o1IsLargest, boolean o2IsLargest) {
        boolean carry = false;
        Stack result = new Stack();

        return result;
    }

    private Stack subtract(Stack o1Stack, Stack o2Stack, boolean o1IsNegative, boolean o2IsNegative,
                           boolean o1IsLargest, boolean o2IsLargest) {
        boolean borrow = false;
        Stack result = new Stack();

        return result;
    }

    private void printResult(Stack results) {

    }
}