import java.util.ArrayList;
import java.util.List;

public class PalindromePartioning {
        public static void main(String[] args) {

        
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        
        List<List<String>> sol = new ArrayList<>();
        List<Integer> curPart = new ArrayList<>();
        curPart.add(0);
        backtracking(s, sol, curPart);
        return sol;

    }

    private static void backtracking(String str, List<List<String>> sol, List<Integer> curPart) {
        int cur = curPart.getLast();
        if(cur == str.length()) {
            addToSolution(str, sol, curPart);
            return;
        }
        for (int i = cur+1; i <= str.length(); i++) {
            if(isPalindrome(str.substring(cur,i))) {
                curPart.add(i);
                // if(i < str.length() && isPalindrome(str.substring(i, str.length())) && i != str.length()-1) {
                //     curPart.add(str.length());
                //     //addToSolution(str, sol, curPart);
                //     curPart.remove(curPart.size()-1);
                // }
                
                backtracking(str, sol, curPart);
                curPart.remove(curPart.size()-1);

            }
        }
    }
    private static void addToSolution(String str, List<List<String>> sol, List<Integer> curPart) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < curPart.size()-1; i++) {
            temp.add(str.substring(curPart.get(i), curPart.get(i+1)));
        }
        sol.add(temp);

    }
    private static boolean isPalindrome(String s) {
        for(int i = 0, j = s.length()-1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
}
