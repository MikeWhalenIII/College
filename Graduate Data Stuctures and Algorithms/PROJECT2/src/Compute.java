/***********************************************************************
 Michael Whalen
 Compute.java
 COP5416 - Project II

 This class reads the 2D array that is passed to it and processes the data
 using stacks.
 ************************************************************************/

public class Compute {
    String[][] input;

    /**
     * Constructor
     *
     * @param input the 2D array
     */
    public Compute(String[][] input) {
        this.input = input;
        compute();
    }

    /**
     * This method iterates through the 2D array and processes
     * each expression.
     */
    private void compute() {
        Stack o1Stack = new Stack();
        Stack o2Stack = new Stack();

        // Iterate through the array
        for (int i = 0; i < input.length; i++) {
            String operand1 = input[i][0];
            String operand2 = input[i][2];
            String operand1Before = input[i][0];
            String operand2Before = input[i][2];
            String operator = input[i][1];

            /* Check to see if operand 1 or operand 2 is a negative number
               and remove the negative sign if one exist. */
            boolean o1IsNegative, o2IsNegative;
            if (operand1.contains("-")) { // operand1 is a negative number
                o1IsNegative = true;
                operand1 = operand1.substring(1); // remove negative sign.
            } else {
                o1IsNegative = false;
            }

            if (operand2.contains("-")) { // operand2 is a negative number
                o2IsNegative = true;
                operand2 = operand2.substring(1); // remove negative sign.
            } else {
                o2IsNegative = false;
            }

            // Check to see which operand is the largest and pad zeros before each operand
            boolean o1IsLargest;
            if (operand1.length() < operand2.length()) {
                o1IsLargest = false;
                operand1 = padZeros(operand1, operand2.length() + 1);
                operand2 = padZeros(operand2, operand2.length() + 1);
            } else {
                o1IsLargest = true;
                operand1 = padZeros(operand1, operand1.length() + 1);
                operand2 = padZeros(operand2, operand1.length());
            }

            // Display each expression before the result
            System.out.printf("%6s\n", operand1Before);
            System.out.printf("%-2s ", operator);
            System.out.printf("%-2s\n", operand2Before);
            System.out.println("-------------");

            // Push the operands to their stacks
            for (int j = 0; j < operand1.length(); j++) {
                o1Stack.push(operand1.charAt(j));
                o2Stack.push(operand2.charAt(j));
            }

            // Swap operands so that the largest one is first
            if (!o1IsLargest && !operator.contains("-")) {
                Stack tmp = o1Stack;
                boolean tmpB = o1IsNegative;

                o1Stack = o2Stack;
                o1IsNegative = o2IsNegative;
                o2Stack = tmp;
                o2IsNegative = tmpB;
            } else if (!o1IsLargest && operator.contains("-") && !o1IsNegative && !o2IsNegative) {
                Stack tmp = o1Stack;
                o1Stack = o2Stack;
                o2Stack = tmp;
                o1IsNegative = true; // Added to trigger a negative result
                o2IsNegative = true;
            }

            // Add/Subtract stacks based on the operator and negative values
            if (operator.contains("+") && !o1IsNegative && !o2IsNegative) {
                printResult(add(o1Stack, o2Stack), false);
            } else if (operator.contains("+") && !o1IsNegative && o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack), false);
            } else if (operator.contains("+") && o1IsNegative && !o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack), true);
            } else if (operator.contains("+") && o1IsNegative && o2IsNegative) {
                printResult(add(o1Stack, o2Stack), true);
            } else if (operator.contains("-") && !o1IsNegative && !o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack), false);
            } else if (operator.contains("-") && !o1IsNegative && o2IsNegative) {
                printResult(add(o1Stack, o2Stack), false);
            } else if (operator.contains("-") && o1IsNegative && !o2IsNegative) {
                printResult(add(o1Stack, o2Stack), true);
            } else if (operator.contains("-") && o1IsNegative && o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack), true);
            }
        }
    }

    /**
     * This method pads zeros to the start of a string.
     *
     * @param s The string that will be padded with zeros.
     * @param c The length of the other operands string
     * @return A string with x number of zeros prepended
     */
    private String padZeros(String s, int c) {
        int sLen = s.length();
        StringBuilder sBuilder = new StringBuilder(s);
        while (sLen < c) {
            sLen++;
            sBuilder.insert(0, '0');
        }
        s = sBuilder.toString();
        return s;
    }

    /**
     * This method takes two stacks and adds them, creating
     * a result stack.
     *
     * @param o1Stack operand 1 stack
     * @param o2Stack operand 2 stack
     * @return A result stack
     */
    private Stack add(Stack o1Stack, Stack o2Stack) {
        int tmpResult = 0, carry = 0;
        Stack result = new Stack();

        while (!o1Stack.isEmpty() && !o2Stack.isEmpty()) {
            tmpResult = (o1Stack.pop() - '0') + (o2Stack.pop() - '0') + carry;
            carry = tmpResult / 10;
            result.push((char) (tmpResult % 10 + '0'));
        }

        // Push the remaining carry
        if (carry > 0) {
            result.push((char) (carry + '0'));
        }

        return result;
    }

    /**
     * This method takes two stacks and subtracts them, creating
     * a result stack.
     *
     * @param o1Stack operand 1 stack
     * @param o2Stack operand 2 stack
     * @return A result stack
     */
    private Stack subtract(Stack o1Stack, Stack o2Stack) {
        int tmpResult = 0;
        int borrow = 0;
        Stack result = new Stack();

        while (!o1Stack.isEmpty() && !o2Stack.isEmpty()) {
            tmpResult = ((o1Stack.pop() - '0') - (o2Stack.pop() - '0')) - borrow;

            if (tmpResult < 0) {
                result.push((char) (tmpResult + 10 + '0'));
                borrow = 1;
            } else {
                result.push((char) (tmpResult + '0'));
                borrow = 0;
            }
        }
        return result;
    }

    /**
     * This method pops the Chars from the result stack
     * and displays the answer to each expression.
     *
     * @param resultStack The stack containing the results.
     * @param negative Will be true is the method should prepend a
     *                 negative sign to the begining of the result string.
     */
    private void printResult(Stack resultStack, boolean negative) {
        StringBuilder result = new StringBuilder();

        // Append a negative sign if negative result
        if (negative) {
            result.append("-");
        }

        // Pop stack
        while (!resultStack.isEmpty()) {
            result.append(resultStack.pop());
        }

        // Remove any leading zeros
        String r = result.toString();
        String pattern = "^(-?)0*";
        r = r.replaceAll(pattern, "$1");

        // Print final result
        System.out.printf("%6s\n\n", r);
    }
}