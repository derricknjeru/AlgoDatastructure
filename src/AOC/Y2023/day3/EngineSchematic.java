package AOC.Y2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineSchematic {

    public static void main(String[] args) {

        // Change the file path to the location of your input file
        String filePath = "/Users/derrick/IdeaProjects/AlgoDatastructure/src/AOC/Y2023/day3/puzzleInput";

        List<String> fileContent = readFile(filePath);
        int sum = 0;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            List<Map<String, Integer>> numbers = getNumbers(line);

            for (Map<String, Integer> number : numbers) {
                if (hasAdjacentSymbol(i, number, fileContent)) {
                    sum += number.get("number");
                }
            }
        }

        System.out.println(sum);
    }


    private static List<String> readFile(String filePath) {
        List<String> file = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                file.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static List<Map<String, Integer>> getNumbers(String line) {
        List<Map<String, Integer>> numbersList = new ArrayList<>();

        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(line);

        while (m.find()) {
            Map<String, Integer> map = new HashMap<>();
            map.put("Start", m.start());
            map.put("End", m.end());
            map.put("number", Integer.parseInt(m.group(1)));

            numbersList.add(map);
        }

        return numbersList;
    }

    private static boolean isSymbol(char c) {
        return !Character.isDigit(c) && (c != '.');
    }

    private static boolean hasAdjacentSymbol(int row, Map<String, Integer> number, List<String> file) {
        return hasNorthSymbol(row, number, file) || hasSouthSymbol(row, number, file) || hasRightSymbol(row, number, file) ||
                hasLeftSymbol(row, number, file) || hasNorthWestSymbol(row, number, file) || hasNorthEastSymbol(row, number, file) ||
                hasSouthWestSymbol(row, number, file) || hasSouthEastSymbol(row, number, file);
    }

    private static boolean hasNorthSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == 0) return false;

        String aboveLine = file.get(row - 1);

        for (int i = number.get("Start"); i < number.get("End"); i++) {
            char c = aboveLine.charAt(i);
            if (isSymbol(c)) return true;
        }
        return false;
    }

    private static boolean hasSouthSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == file.size() - 1) return false;

        String belowLine = file.get(row + 1);

        for (int i = number.get("Start"); i < number.get("End"); i++) {
            char c = belowLine.charAt(i);
            if (isSymbol(c)) return true;
        }
        return false;
    }

    private static boolean hasRightSymbol(int row, Map<String, Integer> number, List<String> file) {
        String line = file.get(row);
        int endRight = number.get("End");
        if (endRight - 1 == line.length() - 1) return false;

        char rightChar = line.charAt(endRight);
        return isSymbol(rightChar);
    }

    private static boolean hasLeftSymbol(int row, Map<String, Integer> number, List<String> file) {
        int start = number.get("Start");
        if (start == 0) return false;

        String line = file.get(row);
        char leftChar = line.charAt(start - 1);

        return isSymbol(leftChar);
    }

    //Diagonal
    private static boolean hasNorthWestSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == 0 || number.get("Start") == 0) return false;

        char nwChar = file.get(row - 1).charAt(number.get("Start") - 1);
        return isSymbol(nwChar);
    }

    private static boolean hasNorthEastSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == 0 || (number.get("End") - 1) == file.get(row).length() - 1) return false;

        char neChar = file.get(row - 1).charAt(number.get("End"));
        return isSymbol(neChar);
    }

    private static boolean hasSouthWestSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == file.size() - 1 || number.get("Start") == 0) return false;

        char swChar = file.get(row + 1).charAt(number.get("Start") - 1);
        return isSymbol(swChar);
    }

    private static boolean hasSouthEastSymbol(int row, Map<String, Integer> number, List<String> file) {
        if (row == file.size() - 1 || (number.get("End") - 1) == file.get(row).length() - 1) return false;

        char seChar = file.get(row + 1).charAt(number.get("End"));
        return isSymbol(seChar);
    }


}


