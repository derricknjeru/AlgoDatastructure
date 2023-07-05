package CodingInterviewPatterns;

import java.util.Random;
import java.util.stream.DoubleStream;

public class RandomInJava {
    public static void main(String[] args) {
        Random random = new Random(); //Random(long seed): Creates a new random number generator using a single long seed

        System.out.println(random.nextInt(10));

        System.out.println(random.nextBoolean());
        System.out.println(random.nextDouble());
        System.out.println(random.nextFloat());
        System.out.println(random.nextGaussian());

        /**
         * , the random.nextDouble(),nextFloat,e.t.c method in Java's java.util.Random class
         * does not directly support generating bounded random numbers.
         * By default, it returns a pseudorandom double value between 0 (inclusive) and 1 (exclusive).
         */

        double min = 10.0; // Minimum value (inclusive)
        double max = 20.0; // Maximum value (exclusive)

        // Generating a bounded random double value
        double boundedValue = min + (max - min) * random.nextDouble();

        System.out.println("Bounded Random Value: " + boundedValue);


    }
}
