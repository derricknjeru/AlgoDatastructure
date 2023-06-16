package CodingInterviewPatterns.InterestingQuiz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HammingDistance {
    public static boolean checkHammingDistance_1(List<String> strings) {
        Set<String> seen = new HashSet<>();

        for (String s : strings) {
            for (int i = 0; i < s.length(); i++) {
                String newS = s.substring(0, i) + s.substring(i + 1);
                if (seen.contains(newS)) {
                    return true;
                }
                seen.add(newS);
            }
        }
        return false;
    }

    public static boolean checkHammingDistance(List<String> strings) {
        Set<String> seen = new HashSet<>();
        int n = strings.get(0).length();

        for (int i = 0; i < strings.size(); i++) {
            for (int j = 1; j < strings.size(); j++) {
                if(i==j) continue;
                String s1 = strings.get(i);
                String s2 = strings.get(j);
                System.out.println(s1 + "----" + s2);
                //compare(s1,S2);

            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> strings = List.of("abcd", "bbcd", "xyzs");
        boolean result = checkHammingDistance(strings);
        System.out.println(result); // Output: true
    }
}

