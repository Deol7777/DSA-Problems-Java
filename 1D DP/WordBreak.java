import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static void main(String args[]) {
        String s = "neetcodes";
        String[] wordDict = new String[]{"neet","code"};
        System.out.println(wordBreak(s, Arrays.asList(wordDict)));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length()];
        HashSet<String> set = new HashSet<>();
        HashSet<Integer> lenghtSet = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
            lenghtSet.add(word.length());
        }
        int i = 0;
        while( i < s.length()) {
            for (Integer wordLen : lenghtSet) {
                int j = i + wordLen - 1;
                if(j >= s.length())
                    continue;
                if(set.contains(s.substring(i, j+1)))
                    dp[j] = 1;
            }
            while(i < s.length() && dp[i] != 1)
                i++;
            i++;
        }
        return dp[dp.length-1] == 1 ? true : false;
        
    }
}
