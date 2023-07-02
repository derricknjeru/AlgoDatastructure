package companies.Google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGeneratorII {

    private final List<Integer> numbers;
    private final Random random;

    public RandomNumberGeneratorII(int start, int end) {
        numbers = new ArrayList<>();
        random = new Random();

        for (int i = start; i <= end; i++) {
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
        int start = 5;  // Example start range
        int end = 15;  // Example end range
        RandomNumberGeneratorII generator = new RandomNumberGeneratorII(start, end);

        // Generate and print random numbers until 0 is returned
        int randomNumber;
        while ((randomNumber = generator.getRandomNumber()) != 0) {
            System.out.println(randomNumber);
        }
    }
}
