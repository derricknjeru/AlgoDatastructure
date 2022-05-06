package HashTable;
import java.util.*;
public class SingleNumber {

    //https://leetcode.com/problems/single-number/
    public int singleNumber(int[] nums) {

        HashMap<Integer, Integer> occurrences = new HashMap<>();


        for (int i : nums) {
            occurrences.put(i, occurrences.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> m : occurrences.entrySet()) {
            System.out.printf("%d occur %d time(s)\n", m.getKey(), m.getValue());
            if (m.getValue() == 1) {
                return m.getKey();
            }


        }

        return 0;


    }
}
