package CodingInterviewPatterns.Interesting;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {
    //https://leetcode.com/discuss/interview-question/1293144/f-me-interview-question-from-failed-google-phone-screen
    public static void main(String[] args) {
        randomPicker(randomGenerator(10, 16));
    }

    public static List<Integer> randomGenerator(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        return list;
    }

    public static void randomPicker(List<Integer> list) {
        while (list.size() != 0) {
            int randPicker = (int) (System.currentTimeMillis() % list.size());
            int ans = list.size() != 0 ? list.get(randPicker) : -1;
            list.remove(randPicker);

            System.out.println(ans);
        }
    }
}
