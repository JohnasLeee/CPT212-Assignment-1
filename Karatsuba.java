/// Java Program to Implement Karatsuba Algorithm
 
// Importing Random class from java.util packahge
import java.util.Random;
 
// MAin class 
class Karatsuba {
 
    // Main driver method 
    public static long mult(long x, long y) {
 
        // Checking only if input is within range  
        if (x < 10 && y < 10) {
           
            // Multiplying the inputs entered 
            return x * y;
        }
<<<<<<< Updated upstream
      
        // Declaring variables in order to  
        // Find length of both integer
        // numbers x and y
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);
 
        // Finding maximum length from both numbers
        // using math library max function
        int maxNumLength
            = Math.max(noOneLength, noTwoLength);
 
        // Rounding up the divided Max length
        Integer halfMaxNumLength
            = (maxNumLength / 2) + (maxNumLength % 2);
 
        // Multiplier
        long maxNumLengthTen
            = (long)Math.pow(10, halfMaxNumLength);
 
        // Compute the expressions
        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;
 
 
        // Compute all mutilpying variables
        // needed to get the multiplication    
        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);
 
        long ans = (z0 * (long)Math.pow(10, halfMaxNumLength * 2) + 
                   ((z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2));
 
=======

        assignmentsCount++;
        functionCallCount++;
        int noOneLength = numLength(x);

        assignmentsCount++;
        functionCallCount++;
        int noTwoLength = numLength(y);

        assignmentsCount++;
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
>>>>>>> Stashed changes
        return ans;
 
    }
   
      // Method 1
    // To calculate length of the number
    public static int numLength(long n)
    {
        int noLen = 0;
<<<<<<< Updated upstream
        while (n > 0) {
            noLen++;
            n /= 10;
        }
 
        // Returning length of number n
=======
        comparisonCount++;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            assignmentsCount++;
            additionCount++;
            noLen++;

            assignmentsCount++;
            divisionsCount++;
            n = n.divide(BigInteger.TEN);
        }
        returnCount++;
>>>>>>> Stashed changes
        return noLen;
    }
 
     // Method 2
    // Main driver function
    public static void main(String[] args)
    {
        // Showcasing karatsuba multiplication
         
      // Case 1: Big integer lengths
        long expectedProduct = 1234 * 5678;
        long actualProduct = mult(1234, 5678);
 
        // Printing the expected and corresponding actual product 
        System.out.println("Expected 1 : " + expectedProduct);
        System.out.println("Actual 1 : " + actualProduct + "\n\n");
       
        assert(expectedProduct == actualProduct);
 
 
        expectedProduct = 102 * 313;
        actualProduct = mult(102, 313);
 
        System.out.println("Expected 2 : " + expectedProduct);
        System.out.println("Actual 2 : " + actualProduct + "\n\n");
         
      assert(expectedProduct == actualProduct);
 
        expectedProduct = 1345 * 63456;
        actualProduct = mult(1345, 63456);
 
        System.out.println("Expected 3 : " + expectedProduct);
        System.out.println("Actual 3 : " + actualProduct + "\n\n");
         
      assert(expectedProduct == actualProduct);        
     
        Integer x = null;
        Integer y = null;
        Integer MAX_VALUE = 10000;
 
        // Boe creating an object of random class
        // inside main() method 
        Random r = new Random();
 
        for (int i = 0; i < MAX_VALUE; i++) {
            x = (int) r.nextInt(MAX_VALUE);
            y = (int) r.nextInt(MAX_VALUE);
 
            expectedProduct = x * y;
 
            if (i == 9999) {
               
              // Prove assertions catch the bad stuff.
                expectedProduct = 1;    
            }
            actualProduct = mult(x, y);
 
             // Again printing the expected and 
            // corresponding actual product 
            System.out.println("Expected: " + expectedProduct);
            System.out.println("Actual: " + actualProduct + "\n\n");
 
            assert(expectedProduct == actualProduct);        
        }
    }
}