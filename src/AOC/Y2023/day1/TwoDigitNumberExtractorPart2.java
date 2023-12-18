package AOC.Y2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TwoDigitNumberExtractorPart2 {
    private static final String FILE_PATH = "/Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/day1/puzzleInput";

    //private static final Map<String, Integer> DIGITS_MAP = new HashMap<>();
    static Map<String, String> DIGITS_MAP = Map.of("one", "o1e", "two", "t2o", "three", "th3ee", "four", "f4ur", "five", "f5ve", "six", "s6x", "seven", "se7en", "eight", "ei8ht", "nine", "n9ne");

    public static void main(String[] args) {

        //The total sum is 54697
        processFile(FILE_PATH);
    }

    private static void processFile(String filePath) {
        /**
         * The Files.lines method returns a Stream<String>, and it's a resource that should be closed properly.
         * To address this, you can modify your code to use try-with-resources for the stream.
         * By using try-with-resources with the Stream,
         * you ensure that the stream is closed properly when the try block is exited, either normally or due to an exception.
         * This is important for resource management and to avoid potential resource leaks.
         */
        Path path = Paths.get(filePath);
        AtomicInteger totalSum = new AtomicInteger(0);

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                int result = extractTwoDigitNumber(line);
                if (result != -1) {
                    System.out.println("The 2-digit number on " + line + " is: " + result);
                    totalSum.addAndGet(result);
                } else {
                    System.out.println("No digit found on this line: " + line);
                }
            });
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("The total sum is " + totalSum.get());
    }

    private static int extractTwoDigitNumber(String input) {
        int firstDigit = -1;
        int lastDigit = -1;
        input = getDigitValue(input);

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                int digitValue = Character.getNumericValue(c);

                if (firstDigit == -1) {
                    firstDigit = digitValue;
                }

                lastDigit = digitValue;
            }
        }

        if (firstDigit != -1 && lastDigit != -1) {
            return firstDigit * 10 + lastDigit;
        } else if (firstDigit != -1) {
            return firstDigit * 10 + firstDigit;
        } else {
            return -1; // Indicates that a 2-digit number was not found
        }
    }

    private static String getDigitValue(String input) {
        for (Map.Entry<String, String> entry : DIGITS_MAP.entrySet()) {
            input = input.replaceAll(entry.getKey(), entry.getValue());
        }
        return input;
    }
}

