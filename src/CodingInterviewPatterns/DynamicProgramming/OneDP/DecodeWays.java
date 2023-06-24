package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class DecodeWays {
    //https://www.youtube.com/watch?v=o1i7JYWbwOE&ab_channel=KnowledgeCenter

    static class Solution {
        /**
         * The time complexity of the provided numDecodings function is O(n), where n is the length of the string s.
         *
         * The function uses a single loop that iterates through the string s once, from index 1 to n-1. Inside the loop, each iteration performs constant time operations, such as character comparisons, conversions, and updates to variables.
         *
         * Therefore, the overall time complexity is linear with respect to the input size, making it O(n).
         * @param s
         * @return
         */
        public int numDecodings(String s) {
            int n = s.length();
            if(n==0 || s.charAt(0)=='0') return 0;
            if(n==1) return 1;

            int twoBack=1, oneBack = 1;
            for(int i=1; i<n; i++){
                int currSingleDigit = s.charAt(i) - '0';
                int lastTwoDigits = (s.charAt(i-1) - '0')*10 + currSingleDigit;
                int count = 0;
                if(currSingleDigit > 0) count = oneBack;
                if(lastTwoDigits >=10 && lastTwoDigits <=26) count +=twoBack;
                //move count forward
                twoBack = oneBack;
                oneBack = count;
            }
            return oneBack;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "12";
        int result1 = solution.numDecodings(s1);
        System.out.println("Input: " + s1);
        System.out.println("Output: " + result1);
        System.out.println();

        String s2 = "226";
        int result2 = solution.numDecodings(s2);
        System.out.println("Input: " + s2);
        System.out.println("Output: " + result2);
        System.out.println();

        String s3 = "06";
        int result3 = solution.numDecodings(s3);
        System.out.println("Input: " + s3);
        System.out.println("Output: " + result3);
        System.out.println();
    }
}
