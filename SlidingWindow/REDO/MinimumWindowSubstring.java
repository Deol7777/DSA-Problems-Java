package SlidingWindow.REDO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        int[] temp = new int[] { 10, 8, 7, 5, 2 };
        String s = "a";
        String t = "a";
        System.out.println((minWindow(s, t)));
    }

    public static String minWindow(String s, String t) {


        HashMap<Character, Integer> tMap = new HashMap<>();
        HashMap<Character, Integer> sMap = new HashMap<>();
        int start = Integer.MAX_VALUE;
        int finish = Integer.MIN_VALUE;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int found = 0;

        for (int i = 0, j = 0; j < s.length(); j++) {
            Character c = s.charAt(j);
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (sMap.get(c) <= tMap.get(c))
                    found++;
            }

            while (found == t.length()) {

                if( j-i + 1 < minLength) {
                    minLength = j-i + 1;
                    start = i;
                    finish = j;
                }
                Character toRem = s.charAt(i);

                if(tMap.containsKey(toRem)) {
                    sMap.put(toRem, sMap.get(toRem) - 1);
                    if(sMap.get(toRem) < tMap.get(toRem))
                        found--;
                }
                i++;
            }

        }

        return start == Integer.MAX_VALUE ? "" : s.substring(start, finish + 1);

    }
}
