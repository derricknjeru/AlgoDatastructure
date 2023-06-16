package CodingInterviewPatterns.InterestingQuiz;

public class Substrings {
    public static void main(String[] args) {
        String s1 = "abc";     // Define the target substring
        String s2 = "abcdef";  // Define the main string

        Substrings substrings = new Substrings();  // Create an instance of Substrings class
        substrings.getSubstrings(s1, s2);           // Call the getSubstrings method
    }

    public void getSubstrings(String s1, String s2) {
        // Iterate through possible starting indices in s2
        // The loop condition ensures that the loop doesn't go beyond the valid indices to form a substring of length s1
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            // Extract the substring of length s1 starting from index i
            String substring = s2.substring(i, i + s1.length());

            // Print the generated substring
            System.out.println(substring);
        }
    }

}


