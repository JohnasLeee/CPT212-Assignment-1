import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.math.BigInteger;

class Karatsuba {

    // Counter variables
    static long additionCount = 0;
    static long subtractionCount = 0;
    static long multiplicationCount = 0;
    static long divisionsCount = 0;
    static long comparisonCount = 0;
    static long returnCount = 0;
    static long functionCallCount = 0;
    static long assignmentsCount = 0;

    public static BigInteger mult(BigInteger x, BigInteger y) {

        comparisonCount += 3; // compare x and y with 10 and a logical AND
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            // Counting multiplication when x and y are less than 10
            multiplicationCount++;
            returnCount++;
            return x.multiply(y);
        }

        // not part of core functionality
        int noOneLength = numLength(x);
        // not part of core functionality
        int noTwoLength = numLength(y);
        // not part of core functionality
        int maxNumLength = Math.max(noOneLength, noTwoLength);
        // not part of core functionality
        BigInteger halfMaxNumLength = BigInteger.valueOf(maxNumLength / 2 + maxNumLength % 2);
        // not part of core functionality
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength.intValue());
        // not part of core functionality
        BigInteger a = x.divide(maxNumLengthTen);
        // not part of core functionality
        BigInteger b = x.mod(maxNumLengthTen);
        // not part of core functionality
        BigInteger c = y.divide(maxNumLengthTen);
        // not part of core functionality
        BigInteger d = y.mod(maxNumLengthTen);

        assignmentsCount++;
        functionCallCount++;
        BigInteger z0 = mult(a, c);

        assignmentsCount++;
        functionCallCount++;
        additionCount += 2;
        BigInteger z1 = mult(a.add(b), c.add(d));

        assignmentsCount++;
        functionCallCount++;
        BigInteger z2 = mult(b, d);

        // Counting multiplications
        multiplicationCount += 3;
        additionCount += 2;
        subtractionCount += 2;
        functionCallCount += 2;
        assignmentsCount++;
        BigInteger ans = z0.multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue() * 2))
                .add((z1.subtract(z0).subtract(z2)).multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue())).add(z2));

        returnCount++;
        return ans;
    }

    public static int numLength(BigInteger n) {
        int noLen = 0;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            noLen++;
            n = n.divide(BigInteger.TEN);
        }
        return noLen;
    }

    public static void main(String[] args) {
        // clear the console
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

        BigInteger expectedProduct, actualProduct;

        long[] values = new long[100];

        // for loop that executes karatsuba algorithm from 1-100 digits
        for (int n = 1; n <= 100; n++) {

            // generate random numbers of n digits
            Random rand = new Random();

            BigInteger lowerBound = BigInteger.TEN.pow(n - 1);
            BigInteger upperBound = BigInteger.TEN.pow(n).subtract(BigInteger.ONE);

            BigInteger multiplier = new BigInteger(upperBound.subtract(lowerBound).bitLength(), rand)
                    .mod(upperBound.subtract(lowerBound))
                    .add(lowerBound);
            BigInteger multiplicand = new BigInteger(upperBound.subtract(lowerBound).bitLength(), rand)
                    .mod(upperBound.subtract(lowerBound))
                    .add(lowerBound);

            // Calculate the sum of operations for the current multiplication
            expectedProduct = multiplier.multiply(multiplicand);
            actualProduct = mult(multiplier, multiplicand);

            System.out.println("Multiplier:   " + multiplier);
            System.out.println("Multiplicand: " + multiplicand);
            System.out.println("Expected:     " + expectedProduct);
            System.out.println("Actual:       " + actualProduct);

            // Print counts of primitive operations
            System.out.println("Primitive operations:");
            System.out.println("Additions: " + additionCount);
            System.out.println("Subtractions: " + subtractionCount);
            System.out.println("Multiplications: " + multiplicationCount);
            System.out.println("Divisions: " + divisionsCount);
            System.out.println("Returns: " + returnCount);
            System.out.println("Function Called: " + functionCallCount);
            System.out.println("Assignment: " + assignmentsCount);
            System.out.println("Comparisons: " + comparisonCount + "\n\n");

            assert (expectedProduct == actualProduct);
            // Store the sum of operations in the values array
            values[n - 1] = additionCount + subtractionCount + multiplicationCount + comparisonCount + returnCount
                    + functionCallCount + divisionsCount + assignmentsCount;
        }

        // Write the values array to a file
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
