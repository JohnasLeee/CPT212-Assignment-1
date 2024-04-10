import java.util.Stack;

public class Part1 {
    public static void main(String[] args) {
        int num1 = 52301;
        int num2 = 380;
        System.out.println("Multiplication result: " + multiply(num1, num2));
    }

    public static String multiply(int multiplier, int multiplicand) {
        Stack stack_partial = new Stack();
        Stack stack_carrier = new Stack();

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
                System.out.println("\nMultiplier: " + multiplier_str.charAt(j));
                System.out.println("Multiplicand: " + multiplicand_str.charAt(i));
                System.out.println("Partial: " + stack_partial);
                System.out.println("Carrier: " + stack_carrier);
            }

        }

        // Convert the result array to a string
        StringBuilder sb = new StringBuilder();
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