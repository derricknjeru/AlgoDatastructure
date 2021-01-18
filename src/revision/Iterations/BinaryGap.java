package revision.Iterations;

public class BinaryGap {
    public static void main(String[] args) {

    }

    public int solution(int N) {
        // write your code in Java SE 8
        String binaryRep = Integer.toBinaryString(N);

        if (binaryRep.charAt(0) != '1' ||
                !binaryRep.contains("0")) {
            return 0;
        }

        char[] arr = binaryRep.toCharArray();
        int count = 0;
        int max = 0;

        for (char c : arr) {
            if (c == '0') {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0; //reset count;
            }
        }

        return max;
    }
}


