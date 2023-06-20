package CodingInterviewPatterns.ForLoops;

public class ForLoops {
    //https://www.youtube.com/watch?v=hPX1pjG1wwY&ab_channel=Errichto
    public static void loopThroughEverything(String str) {
        int N = str.length();
        for (int i = 0; i <= N - 1; i++) System.out.print(str.charAt(i) + "->");
        System.out.println("null");
    }

    public static String findConsecutiveEqualCharacters(String str) {
        //"aabbcccdd"; //since we are considering 3 things. We check the last 3-> n-1,n-2, n-3. Then we pick <=n-3
        int N = str.length();
        for (int i = 0; i <= N - 3; i++) {
            if (str.charAt(i) == str.charAt(i + 1) && str.charAt(i) == str.charAt(i + 2)) {
                return str.substring(i, i + 3);
            }
        }
        return null;
    }

    public static String findConsecutiveEqualCharacters(String str, int K) {
        //"aabbcccdd";  //Since we are considering K things. We check from last. n-1, n-2 ....n-k
        int N = str.length();

        for (int i = 0; i <= N - K; i++) {
            //if I need 5 element I need to end at i, i+1, i+2, i+3, i+4
            int count = 0;
            int start = 0, end = 0;
            for (int j = i; j <= i + K - 1; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    count++;
                    end = j;
                }
            }

            if (count == K) {
                return str.substring(i, end + 1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String inputString = "aabbcccdd";

        loopThroughEverything(inputString);
        String result = findConsecutiveEqualCharacters(inputString);
        System.out.println(result);

        int K = 3;

        String result1 = findConsecutiveEqualCharacters(inputString, K);
        System.out.println(result1);
    }


}
