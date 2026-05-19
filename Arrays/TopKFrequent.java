import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequent {
    public static void main(String[] args) {
        int[] a = {5,2,5,3,5,3,1,1,3};
        System.out.println(Arrays.toString(topKFrequent(a, 2)));
      }

      public static int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        //the upper where indeces are counts
        List<List<Integer>> upper = new ArrayList<>(Collections.nCopies(nums.length+1, null));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //the list at that index
            List<Integer> lower = upper.get(entry.getValue());
            if(lower != null) {
                lower.add(entry.getKey());
            }
            else {
                List<Integer> temp = new ArrayList<>();
                temp.add(entry.getKey());
                //remember to use set here and not add
                upper.set(entry.getValue(), temp);
            }

        }
        
        //transer eveything into sol array
        int[] sol = new int[k];
        int filled = 0;
        for(int i = upper.size()-1; i >= 0; i--) {
            if(upper.get(i) != null) {
                for( int item: upper.get(i)) {
                    sol[filled] = item;
                    filled++;
                }
            }
            if(filled >= k)
                break;
        }

        return sol;

      }
}
