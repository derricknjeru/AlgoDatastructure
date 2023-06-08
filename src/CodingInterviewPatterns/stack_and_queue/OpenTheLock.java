package CodingInterviewPatterns.stack_and_queue;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Arrays;

public class OpenTheLock {
    /**
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
     * <p>
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     * <p>
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
     * <p>
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * Output: 6
     * Explanation:
     * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
     * Note.md.md.md.md that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
     * because the wheels of the lock become stuck after the display becomes the dead end "0102".
     * Example 2:
     * <p>
     * Input: deadends = ["8888"], target = "0009"
     * Output: 1
     * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
     * Example 3:
     * <p>
     * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * Output: -1
     * Explanation: We cannot reach the target without getting stuck.
     */
    //https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1375/
    //https://www.youtube.com/watch?v=IHT8Sx0832k&t=5s
    //https://www.youtube.com/watch?v=Pzg3bCDY87w
    public int openLock(String[] deadends, String target) {

        HashSet<String> deadendsMap = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet<>();

        Queue<String> q = new LinkedList<>();

        q.offer("0000");
        visited.add("0000");

        int level = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                String curr = q.poll();

                if (deadendsMap.contains(curr)) {
                    continue;
                }

                if (curr.equals(target)) {
                    return level;
                }

                StringBuilder sb = new StringBuilder(curr);

                for (int j = 0; j < 4; j++) {

                    char c = sb.charAt(j);

                    int addedCur = c == '9' ? 0 : c - '0' + 1;
                    String s1 = sb.substring(0, j) + addedCur + sb.substring(j + 1);

                    int subCur = c == '0' ? 9 : c - '0' - 1;
                    String s2 = sb.substring(0, j) + subCur + sb.substring(j + 1);


                    if (!visited.contains(s1) && !deadendsMap.contains(s1)) {
                        q.offer(s1);
                        visited.add(s1);
                    }

                    if (!visited.contains(s2) && !deadendsMap.contains(s2)) {
                        q.offer(s2);
                        visited.add(s2);
                    }
                }


            }

            level++;
        }

        return -1;


    }
}
