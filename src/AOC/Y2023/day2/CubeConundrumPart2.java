package AOC.Y2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CubeConundrumPart2 {
    private static final String FILE_PATH = "/Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/day2/puzzleInput";

    public static void main(String[] args) {
        processFile();
    }

    private static void processFile() {
        Path path = Paths.get(CubeConundrumPart2.FILE_PATH);
        AtomicInteger totalSum = new AtomicInteger(0);

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                System.out.println(line);
                totalSum.addAndGet(getPowerSet(line));
            });
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("The total sum is " + totalSum.get());
    }

    private static int getPowerSet(String game) {
        String[] cubes = game.split(":")[1].split(";");

        // Map to store the maximum count for each color
        Map<String, Integer> maxCounts = new HashMap<>();

        for (String g : cubes) {
            String[] cubeInfo = g.trim().split(",");

            for (String info : cubeInfo) {
                String[] parts = info.trim().split("\\s+");
                int count = Integer.parseInt(parts[0]);
                String color = parts[1].toLowerCase();
                // Update the maximum count for the color
                maxCounts.merge(color, count, Integer::max);
            }
        }

        // Print the maximum counts after processing each cubeInfo
        System.out.println("Maximum Counts: " + maxCounts);


        int power = 1;
        for (int count : maxCounts.values()) {
            power *= count;
        }

        System.out.println("The power is " + power);
        System.out.println("--------------------------------------");

        return power;
    }
}
