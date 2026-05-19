import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartitionLabels {
        
    public static void main(String[] args) {

        System.out.println(partitionLabels("xyxxyzbzbbisl"));

    }
    
    public static List<Integer> partitionLabels(String s) {
        List<Integer> sol = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        int end = -1; int size = 0;
        
        //get the last index of each character in string
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, map.get(c));
            size++;
            //means no other chars uptil here will be encountered in future, so mark it as substring
            if( i == end) {
                sol.add(size);
                size = 0;
            }
        }
        return sol;

    }
}
