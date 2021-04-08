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
        boolean o1IsLargest;
        boolean o2IsLargest;

        for (int i = 0; i < input.length; i++) {
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
            }
            if (operand2.contains("-")) {
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

            System.out.printf("%6s\n", operand1Before);
            System.out.printf("%-2s ", operator);
            System.out.printf("%-2s\n", operand2Before);
            System.out.println("-------------");

            /*////////////////////////////// Debug output \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            System.out.printf("%7s\n", operand1);
            System.out.printf("%-2s ", operator);
            System.out.printf("%-2s\n", operand2);
            System.out.println("-------------");
            //////////////////////// REMOVE ME BEFORE SUBMITTAL \\\\\\\\\\\\\\\\\\\\\\\\\\\\*/

            // Push operands to their stacks
            for (int j = 0; j < operand1.length(); j++) {
                o1Stack.push(operand1.charAt(j));
                o2Stack.push(operand2.charAt(j));
            }

            if (!o1IsLargest) {
                Stack tmp = o1Stack;
                o1Stack = o2Stack;
                o2Stack = tmp;
            }

            // Add or Subtract stacks
            if (operator.contains("+") && !o1IsNegative && !o2IsNegative) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), false);
            } else if (operator.contains("+") && !o1IsNegative && o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), false);
            } else if (operator.contains("+") && o1IsNegative && !o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), true);
            } else if (operator.contains("+") && o1IsNegative && o2IsNegative) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), true);
            } else if (operator.contains("-") && !o1IsNegative && !o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), false);
            } else if (operator.contains("-") && !o1IsNegative && o2IsNegative) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), false);
            } else if (operator.contains("-") && o1IsNegative && !o2IsNegative) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), true);
            } else if (operator.contains("-") && o1IsNegative && o2IsNegative) {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), true);
            } else if (operator.contains("+")) {
                printResult(add(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), false);
            } else if (operator.contains("-")) {
                printResult(subtract(o1Stack, o2Stack, o1IsNegative, o2IsNegative, o1IsLargest, o2IsLargest), true);
            }
        }
    }

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

    private Stack add(Stack o1Stack, Stack o2Stack, boolean o1IsNegative, boolean o2IsNegative,
                      boolean o1IsLargest, boolean o2IsLargest) {
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

    private Stack subtract(Stack o1Stack, Stack o2Stack, boolean o1IsNegative, boolean o2IsNegative,
                           boolean o1IsLargest, boolean o2IsLargest) {
        int tmpResult = 0;
        int borrow = 0;
        Stack result = new Stack();

        while (!o1Stack.isEmpty() && !o2Stack.isEmpty()) {
            tmpResult = ((o1Stack.pop() - '0') - (o2Stack.pop() - '0')) - borrow;

            if (tmpResult < 0) {
                result.push((char) ((tmpResult += 10) + '0'));
                char temp = (char) ((tmpResult += 10) + '0');
                borrow = 1;
            } else {
                result.push((char) (tmpResult + '0'));
                char temp = (char) (tmpResult + '0');
                borrow = 0;
            }
        }

        return result;
    }

    private void printResult(Stack resultStack, boolean negative) {
        StringBuilder result = new StringBuilder();

        if (negative) {
            result.append("-");
        }

        int firstChar = (resultStack.pop() - '0');
        int secondChar = (resultStack.pop() - '0');

        // Remove leading zeros
        if (firstChar != 0) {
            result.append(firstChar);
        } else if (secondChar != 0) {
            result.append(secondChar);
        }

        while (!resultStack.isEmpty()) {
            result.append(resultStack.pop());
        }
        System.out.printf("%6s\n\n", result);
    }
}