package REDO;

import java.util.Arrays;

public class LongestPalindromicSubstring {
    // Return the LONGEST substring of s that is a palindrome.
    // Substring = contiguous. If several are tied for longest, any one is fine.
    // 1 <= s.length() <= 1000, letters and digits only.

    public static void main(String[] args) {
        // "aba" is also valid here -- both are accepted
        test("leetcode example 1", "babad", "bab", "aba");
        test("leetcode example 2", "cbbd", "bb");

        test("single char", "a", "a");
        test("two same", "aa", "aa");
        test("two different", "ab", "a", "b");

        // no palindrome longer than 1: any single char is fine
        test("all distinct", "abcde", "a", "b", "c", "d", "e");

        test("whole string odd", "racecar", "racecar");
        test("whole string even", "abba", "abba");
        test("all same", "aaaa", "aaaa");

        // longest is even-length and sits in the middle -- odd-only expansion misses it
        test("even center", "cabbaf", "abba");

        // longest sits at the very end
        test("palindrome at end", "xyzracecar", "racecar");
        // and at the very start
        test("palindrome at start", "aabbaaxyz", "aabbaa");

        // a shorter palindrome appears first; must keep scanning
        test("longer one comes later", "aaxyzzyx", "xyzzyx");

        test("digits", "1221333", "1221");
    }

    // acceptable holds every answer that is correct (ties are allowed)
    static void test(String name, String s, String... acceptable) {
        String actual = new LongestPalindromicSubstring().longestPalindrome(s);
        boolean pass = Arrays.asList(acceptable).contains(actual);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "] \"" + s + "\"");
        if (!pass)
            System.out.println("  expected one of: " + Arrays.toString(acceptable) + "  actual: " + actual);
    }

    int maxLength = 1;
    int start = 0;
    int end = 0;

    public String longestPalindrome(String s) {

        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i - 1, i + 1, s);
        }
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i, i + 1, s);
        }
        return s.substring(start, end + 1);
    }

    private void checkPalindrome(int i, int j, String s) {
        while (i >= 0 && j <= s.length() - 1 && s.charAt(i) == s.charAt(j)) {
            if (j - i + 1 > maxLength) {
                start = i;
                end = j;
                maxLength = j - i + 1;
            }
            i--;
            j++;
        }
    }
}
