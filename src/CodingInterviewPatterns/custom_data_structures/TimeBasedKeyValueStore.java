package CodingInterviewPatterns.custom_data_structures;

import java.util.*;

public class TimeBasedKeyValueStore {
    //https://leetcode.com/problems/time-based-key-value-store/description/
    //https://www.youtube.com/watch?v=fu2cD_6E8Hw&t=1004s


    class TimeMap {
        class Node {
            int timestamp;
            String value;

            Node(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }

        }

        Map<String, List<Node>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            //O(1)
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(new Node(timestamp, value));
            // map.computeIfAbsent(key, k-> new ArrayList<>()).add(new Node(timestamp,value));
        }

        public String get(String key, int timestamp) {
            //O(logn) --n is size of pairs
            if (!map.containsKey(key)) {
                return "";
            }
            List<Node> pairs = map.get(key);
            int index = binarySearchResult(pairs, timestamp);
            if (index == -1) {
                return "";
            }
            return pairs.get(index).value;
        }

        private int binarySearchResult(List<Node> list, int timestamp) {
            int left = 0;
            int right = list.size() - 1;
            int result = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                int midTimestamp = list.get(mid).timestamp;

                if (midTimestamp <= timestamp) {
                    result = mid; //we want to return the largest previous
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
        }
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */

    class TimeMap2 {
        /**
         * The time complexity of the set method in the TimeMap2 class is O(log N),
         * where N represents the number of entries for a specific key. This is because set inserts the value into a TreeMap,
         * which internally uses a Red-Black tree data structure. The insertion operation in a Red-Black tree has a time complexity of O(log N).
         * <p>
         * The time complexity of the get method is also O(log N). It first checks if the key exists in the map,
         * which has a constant time complexity of O(1) on average.
         * Then it retrieves the floor entry from the TreeMap associated with the key, which takes O(log N) time.
         */
        Map<String, TreeMap<Integer, String>> map;

        public TimeMap2() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            //map.putIfAbsent(key, new TreeMap<>());
            //map.get(key).put(timestamp,value);
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            Map.Entry<Integer, String> entry = map.get(key).floorEntry(timestamp);
            if (entry == null) return "";
            return entry.getValue();
        }
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */

    class TimeMap3 {
        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap3() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            TreeMap<Integer, String> values = map.get(key);
            values.put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            TreeMap<Integer, String> values = map.get(key);
            Integer prevTimestamp = values.floorKey(timestamp);
            if (prevTimestamp == null) {
                return "";
            }
            return values.get(prevTimestamp);
        }
    }


}
