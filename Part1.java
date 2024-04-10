import java.util.Stack;

public class Part1 {
    public static void main(String[] args) {
        // Clear the console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

        // later will be replaced by random numbers to check the steps correct or not
        int multiplier = 52301;
        int multiplicand = 380;
        System.out.println("Multiplication result: " + multiply(multiplier, multiplicand));
    }

    public static String multiply(int multiplier, int multiplicand) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack_partial = new Stack();
        Stack<Integer> stack_carrier = new Stack();

        // Convert the numbers to strings
        String multiplicand_str = String.valueOf(multiplicand);
        String multiplier_str = String.valueOf(multiplier);

        // Create an array to store the result
        int[] result = new int[multiplicand_str.length() + multiplier_str.length()];

        // Perform multiplication digit by digit
        for (int i = multiplicand_str.length() - 1; i >= 0; i--) {
            for (int j = multiplier_str.length() - 1; j >= 0; j--) {

                int product = (multiplicand_str.charAt(i) - '0') * (multiplier_str.charAt(j) - '0');
                if (product >= 10) {
                    stack_partial.push(product % 10);
                    stack_carrier.push(product / 10);
                } else {
                    stack_partial.push(product);
                    stack_carrier.push(0);
                }

            }
            // Pop stack_partial and append to a string

            while (!stack_partial.isEmpty()) {
                String partial = String.valueOf(stack_partial.pop());
                sb.append(partial);
            }
            System.out.println("\nPartial Result: " + sb.toString());
            sb.setLength(0); // Clear sb after printing

            // Pop stack_carrier and append to a string
            while (!stack_carrier.isEmpty()) {
                String carrier = String.valueOf(stack_carrier.pop());
                sb.append(carrier);
            }
            System.out.println("Carrier Result: " + sb.toString());
            sb.setLength(0); // Clear sb after printing

            // Clear the queues
            stack_partial.clear();
            stack_carrier.clear();

        }

        // Convert the result array to a string
        for (int num : result) {
            // Ignore leading zeros
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        // Return the final result as a string
        return sb.length() == 0 ? "0" : sb.toString();
    }
}