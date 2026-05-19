import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3};
        System.out.println(twoSum(a, 5));
    }

    public static int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int required;
        for (int i = 0; i < nums.length; i++) {
            required = target - nums[i];
            if (map.containsKey(required)) {
                int[] sol = {map.get(required), i};
                return sol;
            }
            map.put(nums[i],i);
        }
        int[] waste = new int[0];
        return waste;
    }
}
