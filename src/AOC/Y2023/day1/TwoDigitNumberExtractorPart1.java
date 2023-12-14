package AOC.Y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TwoDigitNumberExtractorPart1 {
    /**
     * -- Day 1: Trebuchet?! ---
     * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
     * -
     * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
     * -
     * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
     * -
     * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").
     * -
     * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.
     * -
     * The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.
     * -
     * For example:
     * -
     * 1abc2
     * pqr3stu8vwx
     * a1b2c3d4e5f
     * treb7uchet
     * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
     * -
     * Consider your entire calibration document. What is the sum of all of the calibration values?
     * Given puzzle inputs here /Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/puzzleInput
     */
    private static final String FILE_PATH = "/Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/puzzleInput";

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

}

