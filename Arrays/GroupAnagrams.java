import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
        
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            List<String> list = map.get(sorted);
            
            if(list != null) {
                list.add(strs[i]);
            }
            else{
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(sorted, temp);

            }
        }
        List<List<String>> sol = new ArrayList<>(map.values());
        return sol;
    }
}
