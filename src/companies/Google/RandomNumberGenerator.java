package companies.Google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    //https://leetcode.com/discuss/interview-question/1293144/f-me-interview-question-from-failed-google-phone-screen
    /**
     * I had my phone interview today with Google after months of preparation, and it didn't go well at all.
     * I feel so stupid for not being able to make any progress on the question. Need to grind more LC.
     * <p>
     * Conversation started out with walkthrough of resume and then coding session on Google docs.
     * <p>
     * Question:
     * Implement a random number generator for numbers between [0, n]. Every time the generator function is called,
     * you should return a random number. However, no repetitions are allowed (i.e. you cannot return the same number twice).
     * Once you've used up all numbers, return 0.
     * <p>
     * One other follow up was that the generator could be given a range [start, end]
     * and it should only return numbers between that range.
     * <p>
     * Anyone know how it can be done? Any advice on preparing for such questions?
     */

    private final List<Integer> numbers;
    private final Random random;

    public RandomNumberGenerator(int n) {
        numbers = new ArrayList<>();
        random = new Random();

        for (int i = 0; i <= n; i++) {
            numbers.add(i);
        }
    }

    public int getRandomNumber() {
        if (numbers.isEmpty()) {
            return 0;
        }

        int index = random.nextInt(numbers.size());
        int randomNumber = numbers.get(index);
        numbers.remove(index);

        return randomNumber;
    }

    public static void main(String[] args) {
        int n = 10;  // Example range: [0, 10]
        RandomNumberGenerator generator = new RandomNumberGenerator(n);

        // Generate and print random numbers until 0 is returned
        int randomNumber;
        while ((randomNumber = generator.getRandomNumber()) != 0) {
            System.out.println(randomNumber);
        }
    }
}
