package REDO2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        test("aab", "aab", new String[][] {
                { "a", "a", "b" },
                { "aa", "b" } });
        test("a", "a", new String[][] {
                { "a" } });
        test("ab (no multi-char palindromes)", "ab", new String[][] {
                { "a", "b" } });
        test("aba", "aba", new String[][] {
                { "a", "b", "a" },
                { "aba" } });
        test("aaa", "aaa", new String[][] {
                { "a", "a", "a" },
                { "a", "aa" },
                { "aa", "a" },
                { "aaa" } });
        test("abba", "abba", new String[][] {
                { "a", "b", "b", "a" },
                { "a", "bb", "a" },
                { "abba" } });
        // all-same string of length n -> 2^(n-1) partitions; listing all is not useful
        testCount("12 a's count", "aaaaaaaaaaaa", 2048);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, String s, String[][] expected) {
        List<List<String>> actual = new PalindromePartitioning().partition(s);
        List<String> a = canon(actual);
        List<String> e = new ArrayList<>();
        for (String[] part : expected)
            e.add(String.join("|", part));
        Collections.sort(e);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    static void testCount(String name, String s, int expected) {
        List<List<String>> actual = new PalindromePartitioning().partition(s);
        List<String> a = canon(actual);
        int distinct = a.stream().distinct().toArray().length;
        boolean pass = a.size() == expected && distinct == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected " + expected + " partitions, got " + a.size()
                    + " (" + distinct + " distinct)");
    }

    // flatten each partition to "a|aa|b" then sort: order within a partition
    // matters,
    // order of the partitions does not. Sorted List, NOT a Set -- a Set would hide
    // duplicate emissions, those must fail.
    static List<String> canon(List<List<String>> lists) {
        List<String> out = new ArrayList<>();
        for (List<String> part : lists)
            out.add(String.join("|", part));
        Collections.sort(out);
        return out;
    }

    List<List<String>> sol = new ArrayList<>();
    List<String> temp = new ArrayList<>();

    public List<List<String>> partition(String s) {
        partitionHelper(s, 0);
        return sol;
    }

    private void partitionHelper(String s, int idx) {
        if (idx >= s.length())
            sol.add(new ArrayList<>(temp));
        for (int j = idx; j < s.length(); j++) {
            if (checkPalindrome(idx, j, s)) {
                temp.add(s.substring(idx, j + 1));
                partitionHelper(s, j + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean checkPalindrome(int i, int j, String s) {
        for (int k = i, l = j; k < l; k++, l--) {
            if (!(s.charAt(k) == s.charAt(l)))
                return false;
        }
        return true;
    }
}
