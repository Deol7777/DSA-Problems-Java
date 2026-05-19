package SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        int[] temp = new int[]{10,8,7,5,2};
        String s = "a";
        String t = "a";
        System.out.println((minWindow(s, t)));
    }

    public static String minWindow(String s, String t) {
        if (t.isEmpty()) return "";
        HashMap<Character, Integer> forT = new HashMap<>();
        HashMap<Character, Integer> forS = new HashMap<>();
        int found = 0;
        int minLength = Integer.MAX_VALUE;
        int[] sol = new int[]{-1,-1};
        //count for t
        for (Character c : t.toCharArray()) {
            forT.put(c, forT.getOrDefault(c, 0) + 1);
        }

        int l = 0;
        for(int r = 0; r < s.length(); r++) {

            //only put in sMap the things that are in tMap
            if(forT.containsKey(s.charAt(r))) {
                forS.put(s.charAt(r), forS.getOrDefault(s.charAt(r), 0) + 1);
                if(forS.get(s.charAt(r)) <= forT.get(s.charAt(r))) {
                    found++;
                }
            }

            //loop to move l pointer ahead as much as we can
            while (found == t.length()) {

                if( r - l + 1 < minLength) {
                    minLength = r-l + 1;
                    sol[0] = l;
                    sol [1] = r;
                }

                Character toRemove = s.charAt(l);
                

                if( forT.containsKey(toRemove)) {
                    forS.put(toRemove, forS.get(toRemove) - 1);
                    if(forS.get(toRemove) < forT.get(toRemove))
                        found--;
                }
                l++;
            }
        }

        // int i = 0;
        // int j = 0;
        // while (true) {

        //     if (found == t.length()) {
        //         if ( j - i  <  minLength)  
        //         {
        //             sol[0] = i;
        //             sol[1] = j-1;
        //             minLength = j - i;
        //         }

        //         if(j - i == 1) {
        //             break;
        //         }
        //         i++;
        //         if(forT.get(s.charAt(i)) != null) {
        //             forS.put(s.charAt(i), forS.getOrDefault(s.charAt(i), 0) - 1);
        //             if(forS.get(s.charAt(i)) < forT.get(s.charAt(i))) {
        //                 found--;
        //             }
        //         }
        //     }
        //     else {
        //         if(j == s.length())
        //             break;
        //         if(forT.get(s.charAt(j)) != null) {
        //             forS.put(s.charAt(j), forS.getOrDefault(s.charAt(j), 0) + 1);
        //             if(forS.get(s.charAt(j)) <= forT.get(s.charAt(j))) {
        //                 found++;
        //             }
        //         }
        //         j++;
        //     }
        // }

        if(sol[1] == -1)
            return "";
        else {
            return s.substring(sol[0], sol[1]+1);
        }


    }
}
