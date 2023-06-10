package CodingInterviewPatterns.BinarySearch;

import java.util.*;

public class TimeBasedKeyValueStore {
    //https://leetcode.com/problems/time-based-key-value-store/description/
    //https://www.youtube.com/watch?v=fu2cD_6E8Hw&t=1004s


    class TimeMap {
        private Map<String, List<Pair<Integer, String>>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            List<Pair<Integer, String>> values = map.get(key);
            values.add(new Pair<>(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            List<Pair<Integer, String>> values = map.get(key);
            int index = binarySearch(values, timestamp);
            if (index == -1) {
                return "";
            }
            return values.get(index).getValue();
        }

        private int binarySearch(List<Pair<Integer, String>> values, int targetTimestamp) {
            int left = 0;
            int right = values.size() - 1;
            int result = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midTimestamp = values.get(mid).getKey();

                if (midTimestamp <= targetTimestamp) {
                    result = mid; //The right value so far
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return result;
        }

        private class Pair<K, V> {
            private final K key;
            private final V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }
    }

    class TimeMap2 {
        Map<String, TreeMap<Integer, String>> keyTimeMap;

        public TimeMap2() {
            keyTimeMap = new HashMap<>();

        }

        public void set(String key, String value, int timestamp) {
            if (!keyTimeMap.containsKey(key)) keyTimeMap.put(key, new TreeMap<>());
            keyTimeMap.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!keyTimeMap.containsKey(key)) return "";
            Map.Entry<Integer, String> entry = keyTimeMap.get(key).floorEntry(timestamp);
            if (entry != null) return entry.getValue();

            return "";
        }
    }

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
