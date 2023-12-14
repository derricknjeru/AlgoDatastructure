package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightSumII {
    //https://aaronice.gitbook.io/lintcode/trees/nested-list-weight-sum-ii
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

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
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

        System.out.println("Nested List 1: " + nestedList1.toString());
        System.out.println("Depth Sum Inverse: " + solution.depthSumInverse(nestedList1)); // Output: 8

        // Example 2: [1,[4,[6]]]
        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestedInteger(1));

        NestedInteger item3 = new NestedInteger();
        item3.add(new NestedInteger(4));

        NestedInteger item4 = new NestedInteger();
        item4.add(new NestedInteger(6));

        item3.add(item4);

        nestedList2.add(item3);

        System.out.println("Nested List 2: " + nestedList2);
        System.out.println("Depth Sum Inverse: " + solution.depthSumInverse(nestedList2)); // Output: 17
    }

    static class Solution {
        private int depthSumInverse(List<NestedInteger> nestedList) {
            int depth = findDepth(nestedList);
            return depthSumInverse(nestedList, depth);
        }

        private int findDepth(List<NestedInteger> nestedList) {
            int maxDepth = 1; // Initialize to 1 as the leaf level integers have weight 1
            for (NestedInteger ni : nestedList) {
                if (!ni.isInteger()) {
                    int subDepth = findDepth(ni.getList()) + 1;
                    maxDepth = Math.max(maxDepth, subDepth);
                }
            }
            return maxDepth;
        }

        private int depthSumInverse(List<NestedInteger> nestedList, int depth) {
            int sum = 0;
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    sum += depth * ni.getInteger();
                } else {
                    sum += depthSumInverse(ni.getList(), depth - 1);
                }
            }
            return sum;
        }
    }

    static class Solution2 {
        public int depthSumInverse(List<NestedInteger> nestedList) {
            Queue<NestedInteger> queue = new LinkedList<>();
            int prevSum = 0;
            int totalSum = 0;

            // Add the elements of the nestedList in reverse order
            for (NestedInteger ni : nestedList) {
                queue.offer(ni);
            }

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                int levelSum = 0;

                for (int i = 0; i < levelSize; i++) {
                    NestedInteger ni = queue.poll();
                    if (ni != null) {
                        if (ni.isInteger()) {
                            levelSum += ni.getInteger();
                        } else {
                            for (NestedInteger subNi : ni.getList()) {
                                queue.offer(subNi);
                            }
                        }
                    }
                }

                // Keep track of the sum at each level, and accumulate it from bottom to top
                prevSum += levelSum;
                totalSum += prevSum;
            }

            return totalSum;
        }
    }

}
