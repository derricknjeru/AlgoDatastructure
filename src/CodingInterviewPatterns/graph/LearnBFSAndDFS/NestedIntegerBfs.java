package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class NestedIntegerBfs {

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
    }

    static class Solution {
        public int depthSum(List<NestedInteger> nestedList) {
            Queue<NestedInteger> q = new LinkedList<>(nestedList);
            return depthSumBfs(q);
        }

        private int depthSumBfs(Queue<NestedInteger> q) {
            int sum = 0;
            int depth = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    NestedInteger nestedInteger = q.poll();
                    if (nestedInteger != null) {
                        if (nestedInteger.isInteger()) {
                            sum += nestedInteger.getInteger() * depth;
                        } else {
                            q.addAll(nestedInteger.getList());
                        }
                    }
                }
                depth++;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

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




