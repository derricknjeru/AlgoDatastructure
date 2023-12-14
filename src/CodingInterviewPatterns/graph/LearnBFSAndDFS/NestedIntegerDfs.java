package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerDfs {


    // The provided NestedInteger class
    // The provided NestedInteger class
    static class NestedInteger {
        List<NestedInteger> list;
        Integer integer;

        public NestedInteger() {
            list = new ArrayList<>();
        }

        public NestedInteger(int value) {
            this.integer = value;
        }

        public boolean isInteger() {
            return this.integer != null;
        }

        public Integer getInteger() {
            return this.integer;
        }

        public void setInteger(int value) {
            this.list = null;
            this.integer = value;
        }

        public void add(NestedInteger ni) {
            if (this.integer != null) {
                this.list = new ArrayList<>();
                list.add(new NestedInteger(this.integer));
                this.integer = null;
            }
            list.add(ni);
        }

        public List<NestedInteger> getList() {
            return list;
        }

        public String toString() {
            if (isInteger()) {
                return integer.toString();
            } else {
                StringBuilder result = new StringBuilder("[");
                boolean first = true;

                for (NestedInteger ni : list) {
                    if (!first) {
                        result.append(",");
                    } else {
                        first = false;
                    }

                    result.append(ni.toString());
                }

                result.append("]");
                return result.toString();
            }
        }

    }

    static class Solution {
        public int depthSum(List<NestedInteger> nestedList) {
            return depthSumHelper(nestedList, 1);
        }

        private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
            int sum = 0;
            for (NestedInteger item : nestedList) {
                if (item.isInteger()) {
                    sum += item.getInteger() * depth;
                } else {
                    sum += depthSumHelper(item.getList(), depth + 1);
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ////https://www.educative.io/courses/decode-coding-interview-java/diy-nested-list-weight-sum
        //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/339-nested-list-weight-sum.html
        /**
         * Time Complexity: O(N)
         * N is the total number of integers in the nested list.
         * This implementation visits each integer once, and for each integer,
         * it performs a constant amount of work (adding the integer value to the sum and tracking the depth).
         */

        // Example 1: [[1,1],2,[1,1]]
        List<NestedInteger> nestedList1 = new ArrayList<>();
        NestedInteger item1 = new NestedInteger();
        item1.add(new NestedInteger(1));
        item1.add(new NestedInteger(1));

        nestedList1.add(item1);
        nestedList1.add(new NestedInteger(2));

        NestedInteger item2 = new NestedInteger();
        item2.add(new NestedInteger(1));
        item2.add(new NestedInteger(1));

        nestedList1.add(item2);

        System.out.println(solution.depthSum(nestedList1)); // Output: 10

        // Example 2: [1,[4,[6]]]
        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestedInteger(1));

        NestedInteger item3 = new NestedInteger();
        item3.add(new NestedInteger(4));

        NestedInteger item4 = new NestedInteger();
        item4.add(new NestedInteger(6));

        item3.add(item4);

        nestedList2.add(item3);


        System.out.println(solution.depthSum(nestedList2)); // Output: 27
    }

}


