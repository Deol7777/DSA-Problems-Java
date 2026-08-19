package REDO;

public class PalindromicSubstrings {
    // Count how many SUBSTRINGS of s are palindromes.
    // Substrings at different start/end positions count separately even if
    // they are the same text ("aaa" has three single "a" plus two "aa" plus "aaa").
    // 1 <= s.length() <= 1000, lowercase letters and digits.

    public static void main(String[] args) {
        test("leetcode example 1", "abc", 3);
        test("leetcode example 2", "aaa", 6);

        test("single char", "a", 1);
        test("two same", "aa", 3);
        test("two different", "ab", 2);

        // nothing but the single chars
        test("all distinct", "abcde", 5);

        // n * (n + 1) / 2 when every char is the same
        test("all same", "aaaa", 10);

        test("odd palindrome", "aba", 4);
        test("even palindrome", "abba", 6);

        // nested odd centers: c, cec, aceca, racecar
        test("racecar", "racecar", 10);

        // nested even center
        test("abccba", "abccba", 9);

        // mixes odd and even centers
        test("aabaa", "aabaa", 9);

        test("digits", "1221333", 12);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, String s, int expected) {
        int actual = new PalindromicSubstrings().countSubstrings(s);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "] \"" + s + "\"");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    int count = 0;

    public int countSubstrings(String s) {
        count += s.length();
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i - 1, i + 1, s);
        }
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i, i + 1, s);
        }
        return count;
    }

    private void checkPalindrome(int i, int j, String s) {
        while (i >= 0 && j <= s.length() - 1 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            count++;
        }
    }

}
