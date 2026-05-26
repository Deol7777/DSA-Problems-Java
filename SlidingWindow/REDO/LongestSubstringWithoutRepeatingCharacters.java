package SlidingWindow.REDO;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    // public static int lengthOfLongestSubstring(String s) {
    //     int l = 0; int r = 1;
    //     int longest = 1;
    //     HashMap<Character, Integer> map = new HashMap<>();
    //     while(r < s.length()) {
    //         char cur = s.charAt(r);
    //         if(map.containsKey(cur)) {
    //             longest = Math.max(r-l, longest);
    //             l = map.get(cur) + 1;
    //         }
    //         map.put(cur, r);
    //         r++;
    //     }
    //     return longest;
    // }

    public static int lengthOfLongestSubstring(String s) {
        int l = 0; int r = 0;
        int longest = 1;
        HashSet<Character> set = new HashSet<>();
        while(r < s.length()) {
            char cur = s.charAt(r);
            if(set.contains(cur)) {
                longest = Math.max(r-l, longest);
                while (s.charAt(l) != cur) {
                    set.remove(s.charAt(l));
                    l++;
                }
                l++;
            }
            set.add(cur);
            r++;
        }
        return longest;
    }
}
