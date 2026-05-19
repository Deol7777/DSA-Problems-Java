import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Twitter {

    // table of who a person is following
    HashMap<Integer, HashSet<Integer>> followMap = new HashMap<>();
    HashMap<Integer, List<int[]>> tweetMap = new HashMap<>();
    int time;

    public Twitter() {
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        List<int[]> temp = tweetMap.getOrDefault(userId, new ArrayList<>());
        temp.add(new int[] { time, tweetId });
        if (temp.size() == 1)
            tweetMap.put(userId, temp);
        time++;
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0]).reversed());

        // insert users posts
        List<int[]> myTweets = tweetMap.get(userId);
        if (myTweets != null)
            for (int[] tweets : myTweets)
                maxHeap.add(tweets);

        // for all the people user follows
        HashSet<Integer> following = followMap.get(userId);
        if (following != null)
            for (Integer i : following) {
                List<int[]> otherTweets = tweetMap.get(i);
                if (otherTweets != null)
                    for (int[] tweets : otherTweets)
                        maxHeap.add(tweets);
            }

        List<Integer> sol = new ArrayList<>();
        int returned = 0;
        while (!maxHeap.isEmpty() && returned < 10) {
            sol.add(maxHeap.poll()[1]);
            returned++;
        }

        return sol;

    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> temp = followMap.getOrDefault(followerId, new HashSet<>());
        temp.add(followeeId);
        if (temp.size() == 1)
            followMap.put(followerId, temp);
    }

    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> temp = followMap.get(followerId);
        if (temp != null) {
            temp.remove(followeeId);
        }
    }
}
