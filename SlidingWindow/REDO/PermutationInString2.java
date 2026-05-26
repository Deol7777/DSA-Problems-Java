package SlidingWindow.REDO;

import java.util.HashSet;

public class PermutationInString2 {

    public boolean checkInclusion(String s1, String s2) {

        if(s2.length() < s1.length())
            return false;
        
        int[] s1arr = new int[26];
        int[] s2arr = new int[26];
        HashSet<Character> set = new HashSet<>();
        int matches = 0;

        for (int i = 0; i < s1.length(); i++) {
            s1arr[s1.charAt(i) - 97]++;
            set.add(s1.charAt(i));
            s2arr[s2.charAt(i) - 97]++;
        }

        for (int i = 0; i < 26; i++) {
            if( s1arr[i] != 0 && s1arr[i] == s2arr[i])
                matches++;
        }

        if (matches == set.size())
                return true;
        
        for (int i = s1.length(); i < s2.length(); i++) {

            if( s2arr[s2.charAt(i)-97] != 0 && s2arr[s2.charAt(i)-97] == s1arr[s2.charAt(i)-97])
                matches--;
            if( s2arr[s2.charAt(i-s1.length())-97] != 0 && s2arr[s2.charAt(i-s1.length())-97] == s1arr[s2.charAt(i-s1.length())-97])
                matches--;

            
            s2arr[s2.charAt(i)-97]++;
            s2arr[s2.charAt(i-s1.length())-97]--;

            if( s2arr[s2.charAt(i)-97] != 0 && s2arr[s2.charAt(i)-97] == s1arr[s2.charAt(i)-97])
                matches++;
            if( s2arr[s2.charAt(i-s1.length())-97] != 0 && s2arr[s2.charAt(i-s1.length())-97] == s1arr[s2.charAt(i-s1.length())-97])
                matches++;

            if (matches == set.size())
                return true;
        }        
        return false;
    }

    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();

        // Test 1: s1 is a permutation in s2
        boolean test1 = solution.checkInclusion("ab", "eidbaooo");
        System.out.println("Test 1 - checkInclusion('ab', 'eidbaooo'): " + test1 + " (Expected: true)");

        // Test 2: s1 is not a permutation in s2
        boolean test2 = solution.checkInclusion("ab", "eidboaoo");
        System.out.println("Test 2 - checkInclusion('ab', 'eidboaoo'): " + test2 + " (Expected: false)");

        // Test 3: s1 equals s2
        boolean test3 = solution.checkInclusion("abc", "abc");
        System.out.println("Test 3 - checkInclusion('abc', 'abc'): " + test3 + " (Expected: true)");

        // Test 4: s1 is longer than s2
        boolean test4 = solution.checkInclusion("abc", "ab");
        System.out.println("Test 4 - checkInclusion('abc', 'ab'): " + test4 + " (Expected: false)");

        // Test 5: single character match
        boolean test5 = solution.checkInclusion("a", "b");
        System.out.println("Test 5 - checkInclusion('a', 'b'): " + test5 + " (Expected: false)");

        // Test 6: single character in longer string
        boolean test6 = solution.checkInclusion("a", "ab");
        System.out.println("Test 6 - checkInclusion('a', 'ab'): " + test6 + " (Expected: true)");

        // Test 7: permutation at the end
        boolean test7 = solution.checkInclusion("ba", "abcba");
        System.out.println("Test 7 - checkInclusion('ba', 'abcba'): " + test7 + " (Expected: true)");

        // Test 8: repeated characters
        boolean test8 = solution.checkInclusion("aab", "aaab");
        System.out.println("Test 8 - checkInclusion('aab', 'aaab'): " + test8 + " (Expected: true)");

        // Test 9: no match with repeated characters
        boolean test9 = solution.checkInclusion("aab", "baa");
        System.out.println("Test 9 - checkInclusion('aab', 'baa'): " + test9 + " (Expected: false)");

        // Test 10: empty string
        boolean test10 = solution.checkInclusion("", "");
        System.out.println("Test 10 - checkInclusion('', ''): " + test10 + " (Expected: true)");
    }
}
