import java.util.Random;
import java.math.BigInteger;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Clear the console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

        int[] values = new int[10];
        // for loop that executes simple multiplication for n = 1 to 10
        for (int n = 1; n <= 10; n++) {

            Random rand = new Random();

            int lowerBound = (int) Math.pow(10, n - 1);
            int upperBound = (int) Math.pow(10, n) - 1;

            BigInteger multiplier = BigInteger.valueOf(rand.nextInt(upperBound - lowerBound) + lowerBound);
            BigInteger multiplicand = BigInteger.valueOf(rand.nextInt(upperBound - lowerBound) + lowerBound);
            // Calculate the sum of operations for the current multiplication
            int sumOfOperations = multiply(multiplier, multiplicand);
    
            // Store the sum of operations in the values array
            values[n - 1] = sumOfOperations;
        }

        try {
            FileWriter fileWriter = new FileWriter("result.csv", false); // Set append to false
            PrintWriter writer = new PrintWriter(fileWriter);
        
            // Loop through the array and write each value to the file
            for (int i = 0; i < values.length; i++) {
                writer.println(values[i]);
            }
        
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static int multiply(BigInteger num1, BigInteger num2) {
        String strNum1 = num1.toString();
        String strNum2 = num2.toString();
        BigInteger result = BigInteger.ZERO;

        int additions = 0;
        int multiplications = 0;
        int divisions = 0;
        int comparisons = 0;
        int assignments = 0;

        System.out.println("    " + strNum1);
        System.out.println("x   " + strNum2);
        System.out.println("----------- ");

        comparisons++;
        for (int i = strNum2.length() - 1; i >= 0; i--) {
            int n2 = strNum2.charAt(i) - '0';
            int carry = 0;
            String line = "";
            String carryLine = "";

            comparisons++;
            for (int j = strNum1.length() - 1; j >= 0; j--) {
                int n1 = strNum1.charAt(j) - '0';
                BigInteger product = BigInteger.valueOf(n1).multiply(BigInteger.valueOf(n2));
                multiplications++; // Count multiplication

                BigInteger[] divMod = product.divideAndRemainder(BigInteger.TEN);
                carry = divMod[0].intValue();
                assignments++;
                divisions++; // Count division

                line = divMod[1].toString() + line;
                assignments++;
                divisions++;
                additions++;

                carryLine = BigInteger.valueOf(carry).toString() + carryLine;
                assignments++;
                additions++;

                comparisons++; // for loop to check j>=0
            }

            carryLine += "0";
            assignments++;
            additions++;

            comparisons++;
            for (int k = 0; k < strNum2.length() - 1 - i; k++) {
                line += "0";
                carryLine += "0";
                assignments += 2;
                additions += 2;

                comparisons++;// for loop to check k < strNum2.length()-1-i
            }

            System.out.println(line + "   partial products for (" + num1 + " x " + n2 + ")");
            System.out.println(carryLine + "   carries for (" + num1 + " x " + n2 + ")");
            System.out.println("______");

            result = result.add(new BigInteger(line)).add(new BigInteger(carryLine));
            additions += 2;
        }

        System.out.println("Final result: " + result);
        /*
         * System.out.println("Primitive operations:");
         * System.out.println("Additions: " + additions);
         * System.out.println("Multiplications: " + multiplications);
         * System.out.println("Divisions: " + divisions);
         * System.out.println("Assignments: " + assignments);
         */
        
         return additions+multiplications+divisions+assignments;
    }
}