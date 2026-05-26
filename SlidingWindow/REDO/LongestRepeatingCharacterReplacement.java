package SlidingWindow.REDO;

import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {
    
    public static void main(String[] args) {
        String s = "ABAB";
        System.out.println(characterReplacement(s, 2));
    }



        public static int characterReplacement(String s, int k) {

        int l = 0;
        int maxFrequency = 0;
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int r = 0; r < s.length(); r++) {
            char cur = s.charAt(r);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            maxFrequency = Math.max(maxFrequency, map.get(cur));
            if( r - l - maxFrequency >= k) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            }
            maxLength = Math.max(r-l, maxLength);
        }
        return maxLength+1;
        
    }
}
