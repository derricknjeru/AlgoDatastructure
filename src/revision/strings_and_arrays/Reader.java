package revision.strings_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    // { Driver Code Starts
//Initial Template for Java

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            inputLine = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int x = Integer.parseInt(inputLine[1]);
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            //boolean ans = new Solution().hasArrayTwoCandidates(arr, n, x);
            // System.out.println(ans ? "Yes" : "No");
        }
    }
}


