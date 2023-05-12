package CodingInterviewPatterns.Interesting;

import java.util.TreeSet;

public class ContainsDuplicateIII {
    //https://www.youtube.com/watch?v=x9pdHIxHgWA
    //https://www.youtube.com/watch?v=DUPwzvuI4vQ
    //https://www.youtube.com/watch?v=Cu7g9ovYHNI

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //https://leetcode.com/problems/contains-duplicate-iii/
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long no = (long) nums[i];

            Long floor = set.floor(no);
            Long ceil = set.ceiling(no);

            if (floor != null && Math.abs(floor - no) <= t) {
                return true;
            }

            if (ceil != null && Math.abs(ceil - no) <= t) {
                return true;
            }

            set.add(no);
            if (set.size() > k) { // size of set is always k and hence becomes a sliding window problem
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //2,3,1

        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        //set.add(2);
        //set.add(3);

        System.out.println();

        System.out.println("floor " + set.floor(2));
        System.out.println("ceil " + set.ceiling(2));


    }
}
