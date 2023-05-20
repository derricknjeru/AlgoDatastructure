package CodingInterviewPatterns.HashTable;

import java.util.*;

public class MostPopularVideoCreator {

    public static List<List<String>> findHighestPopularity(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> popularityMap = new HashMap<>();
        Map<String, String> mostViewedVideoMap = new HashMap<>();

        // Step 1: Calculate the popularity of each creator and find the most viewed video
        for (int i = 0; i < creators.length; i++) {
            String creator = creators[i];
            String id = ids[i];
            int viewCount = views[i];

            // Calculate creator's popularity
            popularityMap.put(creator, popularityMap.getOrDefault(creator, 0) + viewCount);

            // Update most viewed video for the creator
            if (!mostViewedVideoMap.containsKey(creator)
                    || viewCount > viewsFromId(mostViewedVideoMap.get(creator))) {
                mostViewedVideoMap.put(creator, id);
            }
        }

        // Step 2: Find the maximum popularity
        int maxPopularity = Collections.max(popularityMap.values());

        // Step 3: Find creators with maximum popularity
        List<String> maxPopularityCreators = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : popularityMap.entrySet()) {
            if (entry.getValue() == maxPopularity) {
                maxPopularityCreators.add(entry.getKey());
            }
        }

        // Step 4: Construct the result array
        // String[][] result = new String[maxPopularityCreators.size()][2];
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < maxPopularityCreators.size(); i++) {
            String creator = maxPopularityCreators.get(i);
            List<String> res = new ArrayList<>();
            res.add(creator);
            res.add(mostViewedVideoMap.get(creator));
        }

        return result;
    }


    private static int viewsFromId(String id) {
        try {
            return Integer.parseInt(id.substring(1));
        } catch (NumberFormatException e) {
            return 0; // Return a default value when parsing fails
        }
    }


    public static void main(String[] args) {
        String[] creators = {"alice", "bob", "alice", "chris"};
        String[] ids = {"one", "two", "three", "four"};
        int[] views = {5, 10, 5, 4};

        List<List<String>> result = findHighestPopularity(creators, ids, views);

        System.out.println(result);
    }
}


