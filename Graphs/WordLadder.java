import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {

    public static void main(String[] args) {
        String[] list = { "hot","cog","dog","tot","hog","hop","pot","dot"};
        System.out.println(ladderLength("hot", "dog", Arrays.asList(list)));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // fill the map with empty lists
        for (String word : wordList) {
            map.put(word, new ArrayList<>());
        }

        for (int i = 0; i < wordList.size() - 1; i++) {
            String w1 = wordList.get(i);
            for (int j = i + 1; j < wordList.size(); j++) {
                String w2 = wordList.get(j);
                if (oneDifference(w1, w2)) {
                    map.get(w1).add(w2);
                    map.get(w2).add(w1);
                }
            }
        }


        //all the words with one letter difference to start-word are added
        List<String> startPoints = new ArrayList<>();
        for (String word : wordList) {
            if(oneDifference(beginWord, word))
                startPoints.add(word);
        }

        System.out.println(startPoints);

        int minPath = 0;
        int toReturn = Integer.MAX_VALUE;
        boolean foundAtStartPnt = false;
        boolean foundOnce = false;
        for(String start: startPoints) {
            foundAtStartPnt = false;
            queue.add(start);
            minPath = 0;
            while(!queue.isEmpty() && !foundAtStartPnt) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    if(cur.equals(endWord)) {
                        foundAtStartPnt = true;
                        foundOnce = true;
                        System.out.println("PAth from " + cur + "APth: " + start);
                        break;
                    }
                    visited.add(cur);
                    for (String neighbours: map.get(cur)) {
                        if(!visited.contains(neighbours))
                            queue.add(neighbours);
                    }
                }
                minPath++;
            }
            visited.clear();
            queue.clear();
            toReturn = Math.min(toReturn, minPath);
        }

        return foundOnce ? toReturn + 1 : 0;
    }

    private static boolean oneDifference(String w1, String w2) {
        int diff = 0;
        for (int j = 0; j < w1.length() && diff < 2; j++) {
            if (w1.charAt(j) != w2.charAt(j))
                diff++;
        }
        return diff < 2;
    }
}
