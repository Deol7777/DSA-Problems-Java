import java.util.Arrays;
import java.util.HashSet;

public class LongestConsequtiveSequence {
      public static void main(String[] args) {
        int[] a = {0,3,2,5,4,6,1,1};
        System.out.println(longestConsecutive(a));
      }

      public static int longestConsecutive(int[] nums) {
        
        HashSet <Integer> set = new HashSet<>();
        int max = 1;
        
        for(int i: nums) set.add(i);

        for(int i: set) {
            int localMax = 1;
            if(set.contains((i+1)) && !set.contains(i-1)) {
                int j = i;
                while(set.contains(j+1)) {
                   localMax++;
                   j++; 
                }
            }
            max = Math.max(localMax, max);
        }
        return max;
      }

    
}
