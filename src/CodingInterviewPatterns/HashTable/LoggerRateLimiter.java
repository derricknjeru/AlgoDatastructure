package CodingInterviewPatterns.HashTable;

import java.util.*;

public class LoggerRateLimiter {
    //https://www.youtube.com/watch?v=a5vcIOaKuuY
    //https://www.youtube.com/watch?v=UVE8GrzZ_tI
    //https://www.youtube.com/watch?v=g8r59EosQhU

    /**
     * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
     * <p>
     * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
     * <p>
     * It is possible that several messages arrive roughly at the same time.
     * <p>
     * Example:
     * <p>
     * Logger logger = new Logger();
     * <p>
     * // logging string "foo" at timestamp 1
     * logger.shouldPrintMessage(1, "foo"); returns true;
     * <p>
     * // logging string "bar" at timestamp 2
     * logger.shouldPrintMessage(2,"bar"); returns true;
     * <p>
     * // logging string "foo" at timestamp 3
     * logger.shouldPrintMessage(3,"foo"); returns false;
     * <p>
     * // logging string "bar" at timestamp 8
     * logger.shouldPrintMessage(8,"bar"); returns false;
     * <p>
     * // logging string "foo" at timestamp 10
     * logger.shouldPrintMessage(10,"foo"); returns false;
     * <p>
     * // logging string "foo" at timestamp 11
     * logger.shouldPrintMessage(11,"foo"); returns true;
     */

    public class Logger {
        Map<String, Integer> map = new HashMap<>(); // msg : lst print timestamp
        int limiter = 10;

        /**
         * Initialize your data structure here.
         */
        public Logger() {

        }

        /**
         * Returns true if the message should be printed in the given timestamp, otherwise returns false.
         * If this method returns false, the message will not be printed.
         * The timestamp is in seconds granularity.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {

            if (!map.containsKey(message)) {
                map.put(message, timestamp);
                return true;
            } else {
                if (timestamp - map.get(message) >= limiter) {
                    map.put(message, timestamp);
                    return true;
                }
            }

            return false;
        }
    }


    public class Logger2 {
        public Map<String, Integer> map;
        int lastSecond = 0;

        /**
         * Initialize your data structure here.
         */
        public Logger2() {
            // Create a LinkedHashMap with initial capacity 100, load factor 0.75, and access order enabled
            // Time complexity: O(1)
            map = new LinkedHashMap<>(100, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                    // Override removeEldestEntry to remove entries older than 10 seconds
                    // Time complexity: O(1)
                    return lastSecond - eldest.getValue() > 10;
                }
            };
        }

        /**
         * Returns true if the message should be printed in the given timestamp, otherwise returns false.
         * If this method returns false, the message will not be printed.
         * The timestamp is in seconds granularity.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            // Update the lastSecond with the provided timestamp
            // Time complexity: O(1)
            lastSecond = timestamp;

            if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
                // Check if the message is not present in the map or the time difference is greater than or equal to 10

                // Update the map with the new timestamp for the message
                // Time complexity: O(1)
                map.put(message, timestamp);

                // Return true as the message should be printed
                // Time complexity: O(1)
                return true;
            }

            // Return false as the message should not be printed within the last 10 seconds
            // Time complexity: O(1)
            return false;
        }
    }


/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
}

