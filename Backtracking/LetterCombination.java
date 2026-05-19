import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombination {

    public static void main(String[] args) {

        System.out.println(letterCombinations("34"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> sol = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (digits.length() == 0)
            return sol;

        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtracking(digits, 0, sol, sb, map);
        return sol;
    }

    private static void backtracking(String digits, int idx, List<String> sol, StringBuilder sb,
            HashMap<Character, String> map) {
        if (idx == digits.length()) {
            sol.add(sb.toString());
            return;
        }
        String cur = map.get(digits.charAt(idx));
        for (Character c : cur.toCharArray()) {
            sb.append(c);
            backtracking(digits, idx + 1, sol, sb, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}