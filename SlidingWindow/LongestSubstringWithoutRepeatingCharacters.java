package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcdbdefge";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;
        HashSet<Character> set = new HashSet<>();
        for(int i = 0, j = 0; j < s.length(); j++ ) {
            if(set.contains(s.charAt(j))) {
                while(s.charAt(i) != s.charAt(j)) {
                    set.remove(s.charAt(i));
                    i++;
                }
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            longest = Math.max(j-i+1, longest);
        }
        return longest;
    }
}
