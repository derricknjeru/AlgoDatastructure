package CodingInterviewPatterns.Heaps;

import java.util.*;

public class DesignTwitter {
    //https://leetcode.com/problems/design-twitter
    //

    class Twitter {
        int count;
        HashMap<Integer, List<int[]>> tweetMap;
        HashMap<Integer, HashSet<Integer>> followerMap;

        public Twitter() {
            count = 0;
            tweetMap = new HashMap<>();
            followerMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!tweetMap.containsKey(userId)) {
                tweetMap.put(userId, new ArrayList<>());
            }

            List<int[]> tweets = tweetMap.get(userId);
            tweets.add(new int[]{count, tweetId});
            ++count;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

            if (!followerMap.containsKey(userId)) {
                followerMap.put(userId, new HashSet<>());
            }

            followerMap.get(userId).add(userId);

            Set<Integer> followers = followerMap.get(userId);
            for (int followeeId : followers) {
                List<int[]> tweets = tweetMap.get(followeeId);
                if (tweets != null) {
                    int tweetIndex = tweets.size() - 1;
                    int[] tweet = tweets.get(tweetIndex);
                    pq.offer(new int[]{tweet[0], tweet[1], followeeId, tweetIndex - 1});
                }
            }

            while (!pq.isEmpty() && res.size() < 10) {
                int[] data = pq.poll();
                res.add(data[1]);
                if (data[3] >= 0) {
                    List<int[]> tweets = tweetMap.get(data[2]);
                    int[] tweet = tweets.get(data[3]);
                    pq.offer(new int[]{tweet[0], tweet[1], data[2], data[3] - 1});
                }
            }

            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (!followerMap.containsKey(followerId)) {
                followerMap.put(followerId, new HashSet<>());
            }

            followerMap.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerMap.containsKey(followerId)) {
                followerMap.get(followerId).remove(followeeId);
            }
        }
    }

}
