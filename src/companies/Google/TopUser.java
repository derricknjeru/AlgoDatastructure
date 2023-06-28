package companies.Google;

import java.util.HashMap;
import java.util.Map;

public class TopUser {
    public static void main(String[] args) {
        String[] logs = {
                "Preamble <bob> code check in",
                "Commit 2 <alice> change variables",
                // Additional log entries
        };

        topUser(logs);
    }

    /**
     * Let's analyze the time and space complexity of the solution:
     *
     * Time Complexity:
     *
     * Iterating over each log entry takes O(N) time, where N is the total number of log entries.
     * Extracting the username and message portions from each log entry takes O(1) time.
     * Splitting the message into words using whitespace as the delimiter takes O(M) time, where M is the length of the message.
     * Counting the number of words in the message also takes O(M) time.
     * Updating the word count for each username in the map takes O(1) time.
     * Finding the user with the highest word count takes O(K) time, where K is the number of unique usernames in the map.
     * Considering all the steps, the overall time complexity of the solution is O(N * M), where N is the number of log entries and M is the average length of the messages.
     *
     * Space Complexity:
     *
     * The space required to store the user-word count mapping depends on the number of unique usernames in the logs. Therefore, the space complexity is O(K), where K is the number of unique usernames.
     * Additional space is used to store temporary strings and arrays during the execution, but their sizes are constant and do not depend on the input size.
     * In summary, the space complexity is O(K), and the time complexity is O(N * M) for the given solution.
     */

    /**
     * You are given a set of user logs, similar to commit logs, which follow a fixed structure:
     * Problem Statement: Finding the Top User by Word Count in User Logs
     *
     * Message type <username> Message
     *
     * Preamble <bob> code check-in
     * Commit 2 <alice> change variables
     *
     * The goal is to determine the top user based on their word count in the messages.
     * A word is defined as a sequence of alphanumeric characters.
     * ...
     * @param logs
     */

    public static void topUser(String[] logs) {

        Map<String, Integer> userWordCounts = new HashMap<>();

        for (String log : logs) {
            int usernameStartIndex = log.indexOf('<');
            int usernameEndIndex = log.indexOf('>');

            if (usernameStartIndex != -1 && usernameEndIndex != -1) {
                String username = log.substring(usernameStartIndex + 1, usernameEndIndex);
                String message = log.substring(usernameEndIndex + 1);

                String[] words = message.split("\\W+"); // Split by non-alphanumeric characters

                int wordCount = words.length;

                userWordCounts.put(username, userWordCounts.getOrDefault(username, 0) + wordCount);
            }
        }

        String topUser = "";
        int maxWordCount = 0;

        for (Map.Entry<String, Integer> entry : userWordCounts.entrySet()) {
            if (entry.getValue() > maxWordCount) {
                topUser = entry.getKey();
                maxWordCount = entry.getValue();
            }
        }

        System.out.println("Top user by word count: " + topUser);
        System.out.println("Word count: " + maxWordCount);
    }
}
