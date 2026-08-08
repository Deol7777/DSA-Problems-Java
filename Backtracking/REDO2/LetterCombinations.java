package REDO2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LetterCombinations {
    public static void main(String[] args) {
        test("23", "23", new String[] { "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf" });
        test("empty", "", new String[] {});
        test("2", "2", new String[] { "a", "b", "c" });
        // 7 and 9 are the 4-letter keys
        test("7", "7", new String[] { "p", "q", "r", "s" });
        test("9", "9", new String[] { "w", "x", "y", "z" });
        test("79", "79", new String[] {
                "pw", "px", "py", "pz", "qw", "qx", "qy", "qz",
                "rw", "rx", "ry", "rz", "sw", "sx", "sy", "sz" });
        // 3*3*3*3*3*4 = 972; listing all is not useful
        testCount("234567 count", "234567", 972);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, String digits, String[] expected) {
        List<String> actual = new LetterCombinations().letterCombinations(digits);
        List<String> a = canon(actual);
        List<String> e = new ArrayList<>(Arrays.asList(expected));
        Collections.sort(e);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    static void testCount(String name, String digits, int expected) {
        List<String> actual = new LetterCombinations().letterCombinations(digits);
        int distinct = canon(actual).stream().distinct().toArray().length;
        boolean pass = actual.size() == expected && distinct == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected " + expected + " strings, got " + actual.size()
                    + " (" + distinct + " distinct)");
    }

    // sorted List, NOT a Set: emission order is unspecified so sorting is fine, but
    // a Set would hide duplicate emissions -- those must fail.
    static List<String> canon(List<String> list) {
        List<String> out = new ArrayList<>(list);
        Collections.sort(out);
        return out;
    }

    // index 0 == digit '2', so look up with keypad[c - '2']
    char[][] keypad = {
            { 'a', 'b', 'c' }, // 2
            { 'd', 'e', 'f' }, // 3
            { 'g', 'h', 'i' }, // 4
            { 'j', 'k', 'l' }, // 5
            { 'm', 'n', 'o' }, // 6
            { 'p', 'q', 'r', 's' }, // 7
            { 't', 'u', 'v' }, // 8
            { 'w', 'x', 'y', 'z' } // 9
    };

    List<String> sol = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        letterCombinationsHelper(digits, 0);
        return sol;
    }

    private void letterCombinationsHelper(String digits, int idx) {
        if (sb.length() == digits.length()) {
            sol.add(sb.toString());
            return;
        }
        int keypadIdx = digits.charAt(idx) - 50;
        for (int i = 0; i < keypad[keypadIdx].length; i++) {
            sb.append(keypad[keypadIdx][i]);
            letterCombinationsHelper(digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}