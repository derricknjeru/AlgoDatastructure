package AOC.Y2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CubeConundrumPart1 {
    private static final Map<String, Integer> TARGET_MAPPING = new HashMap<>();
    private static final String FILE_PATH = "/Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/day2/puzzleInput";

    static {
        // Predefined mapping
        TARGET_MAPPING.put("red", 12);
        TARGET_MAPPING.put("green", 13);
        TARGET_MAPPING.put("blue", 14);
    }

    public static void main(String[] args) {
        processFile();
    }

    private static void processFile() {
        Path path = Paths.get(CubeConundrumPart1.FILE_PATH);
        AtomicInteger totalSum = new AtomicInteger(0);

        try (Stream<String> lines = Files.lines(path)) {
            lines.filter(CubeConundrumPart1::isValidGame)
                    .mapToInt(CubeConundrumPart1::getGameNumber)
                    .forEach(totalSum::addAndGet);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("The total sum is " + totalSum.get());
    }

    private static int getGameNumber(String game) {
        String gameNo = game.split(":")[0].split("\\s+")[1];
        return Integer.parseInt(gameNo);
    }

    private static boolean isValidGame(String game) {
        String[] cubes = game.split(":")[1].split(";");

        for (String g : cubes) {
            String[] cubeInfo = g.trim().split(",");

            for (String info : cubeInfo) {
                String[] parts = info.trim().split("\\s+");
                int count = Integer.parseInt(parts[0]);
                String color = parts[1].toLowerCase();

                System.out.println(count + " ---> " + color);

                int targetCount = TARGET_MAPPING.get(color);
                if (count > targetCount) {
                    return false;
                }
            }

            System.out.println("--------------------------------------");
        }

        return true;
    }
}
