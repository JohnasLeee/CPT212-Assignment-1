import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class Karatsuba {

    // Counter variables
    static long additionCount = 0;
    static long subtractionCount = 0;
    static long multiplicationCount = 0;
    static long comparisonCount = 0;

    public static long mult(long x, long y) {
        if (x < 10 && y < 10) {
            // Counting multiplication when x and y are less than 10
            multiplicationCount++;
            return x * y;
        }

        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        int maxNumLength = Math.max(noOneLength, noTwoLength);

        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);

        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength);

        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;

        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);

        // Counting multiplications
        multiplicationCount += 3;

        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2));

        // Counting additions and subtractions
        additionCount += 2;
        subtractionCount += 1;

        return ans;
    }

    public static int numLength(long n) {
        int noLen = 0;
        while (n > 0) {
            noLen++;
            n /= 10;
        }
        return noLen;
    }

    public static void main(String[] args) {

        long expectedProduct, actualProduct;

        long[] values = new long[10];

        // for loop that executes simple multiplication for n = 1 to 4
        for (int n = 1; n <= 10; n++) {
            
            // Reset counters to 0 for each iteration
            additionCount = 0;
            subtractionCount = 0;
            multiplicationCount = 0;
            comparisonCount = 0;

            Random rand = new Random();

            int lowerBound = (int) Math.pow(10, n - 1);
            int upperBound = (int) Math.pow(10, n) - 1;

            long multiplier = rand.nextInt(upperBound - lowerBound) + lowerBound;
            long multiplicand = rand.nextInt(upperBound - lowerBound) + lowerBound;
            // Calculate the sum of operations for the current multiplication
            expectedProduct = multiplier * multiplicand;
            actualProduct = mult(multiplier, multiplicand);

            System.out.println("Multiplier:   " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            System.out.println("Karatsuba:    " + actualProduct);

            // Print counts of primitive operations
            System.out.println("Primitive operations:");
            System.out.println("Additions: " + additionCount);
            System.out.println("Subtractions: " + subtractionCount);
            System.out.println("Multiplications: " + multiplicationCount);
            System.out.println("Comparisons: " + comparisonCount+"\n\n");

            assert (expectedProduct == actualProduct);
            // Store the sum of operations in the values array
            values[n - 1] = additionCount+subtractionCount+subtractionCount+multiplicationCount+comparisonCount;
        }

        try {
            FileWriter fileWriter = new FileWriter("result_K.csv", false); // Set append to false
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
}
