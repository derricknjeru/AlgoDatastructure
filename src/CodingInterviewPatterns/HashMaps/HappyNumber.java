package CodingInterviewPatterns.HashMaps;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    private final Set<Integer> set = new HashSet<>();
    //https://leetcode.com/problems/happy-number/solution/

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }


    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int res = getNext(n);
        if (res == 1) return true;
        else if (set.contains(res)) return false;
        else {
            set.add(res);
            return isHappy2(res);
        }
    }

}
