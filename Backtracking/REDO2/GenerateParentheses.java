package REDO2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        test("n=1", 1, new String[] { "()" });
        test("n=2", 2, new String[] { "(())", "()()" });
        test("n=3", 3, new String[] { "((()))", "(()())", "(())()", "()(())", "()()()" });
        test("n=4", 4, new String[] { "(((())))", "((()()))", "((())())", "((()))()", "(()(()))",
                "(()()())", "(()())()", "(())(())", "(())()()", "()((()))",
                "()(()())", "()(())()", "()()(())", "()()()()" });
        // 8 pairs -> Catalan(8) = 1430; count-only check, listing all is not useful
        testCount("n=8 count", 8, 1430);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int n, String[] expected) {
        List<String> actual = new GenerateParentheses().generateParenthesis(n);
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

    static void testCount(String name, int n, int expected) {
        List<String> actual = new GenerateParentheses().generateParenthesis(n);
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

    List<String> sol = new ArrayList<>();
    StringBuilder temp = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        generateParenthesisHelper(n, 0, 0);
        return sol;
    }

    private void generateParenthesisHelper(int n, int open, int close) {
        if (temp.length() == 2 * n) {
            sol.add(new String(temp));
            return;
        }
        // if open remain
        if (open < n) {
            temp.append('(');
            generateParenthesisHelper(n, open + 1, close);
            temp.deleteCharAt(temp.length() - 1);
        }
        // if close remain
        if (close < open) {
            temp.append(')');
            generateParenthesisHelper(n, open, close + 1);
            temp.deleteCharAt(temp.length() - 1);
        }

    }

}
