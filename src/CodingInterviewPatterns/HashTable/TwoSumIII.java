package CodingInterviewPatterns.HashTable;

import java.util.*;

public class TwoSumIII {


    public static class TwoSum {
        private Set<Integer> numbers;
        private Set<Integer> sums;

        public TwoSum() {
            numbers = new HashSet<>();
            sums = new HashSet<>();
        }

        // Add the number to the internal data structure
        public void add(int number) {
            // Check if there exists a sum (target - number) in the set of numbers
            for (int num : numbers) {
                sums.add(num + number);
            }
            numbers.add(number);
        }

        // Find if there exists any pair of numbers which sum is equal to the value
        public boolean find(int value) {
            return sums.contains(value);
        }


    }


    //https://aaronice.gitbook.io/lintcode/high_frequency/two-sum-iii-data-structure-design

    /**
     * Design and implement a TwoSum class. It should support the following operations:addandfind.
     * add- Add the number to an internal data structure.
     * find- Find if there exists any pair of numbers which sum is equal to the value.
     * Example 1:
     * add(1); add(3); add(5);
     * find(4) -> true
     * find(7) -> false
     * Example 2:
     * add(3); add(1); add(2);
     * find(3) -> true
     * find(6) -> false
     *
     */


    /**
     * @param number: An integer
     * @return: nothing
     */

    Map<Integer, Integer> map = new HashMap<>();

    public void add(int number) {
        // write your code here
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        for (Map.Entry<Integer, Integer> res : map.entrySet()) {

            int otherNumber = value - res.getKey();

            if (map.containsKey(otherNumber)) {
                if (otherNumber == res.getKey() && res.getValue() == 1) {
                    continue;
                } else {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(2);
        twoSum.add(3);
        twoSum.add(4);
        twoSum.add(5);

        System.out.println(twoSum.find(7));  // true, 2 + 5 = 7
        System.out.println(twoSum.find(9));  // true, 4 + 5 = 9
        System.out.println(twoSum.find(1));  // false
    }
}
