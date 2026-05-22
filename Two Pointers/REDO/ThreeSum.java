package REDO;

import java.util.*;

public class ThreeSum {
    public static void main(String [] args) {
        ThreeSum solver = new ThreeSum();

        int[] a1 = {-1, 0, 1, 2, -1, -4};
        int[] a2 = {0, 0, 0, 0};
        int[] a3 = {};

        List<List<Integer>> r1 = solver.threeSum(a1);
        List<List<Integer>> r2 = solver.threeSum(a2);
        List<List<Integer>> r3 = solver.threeSum(a3);

        System.out.println("Input: " + Arrays.toString(a1) + " -> " + String.valueOf(r1));
        System.out.println("Input: " + Arrays.toString(a2) + " -> " + String.valueOf(r2));
        System.out.println("Input: " + Arrays.toString(a3) + " -> " + String.valueOf(r3));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // implementation intentionally left blank for testing
        List<List<Integer>> sol = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            if(i > 0 && nums[i-1] == nums[i])
                continue;
            if(nums[i] > 0)
                break;
            int j = i+1; int k = nums.length-1;
            while(j < k) {
                int cur = nums[i] + nums[j] + nums[k];
                if (cur < 0)
                    j++;
                else if (cur > 0)
                    k--;
                else {
                    sol.add(List.of(nums[i], nums[j], nums[k]));
                    while(j < k && nums[j+1] == nums[j])
                        j++;
                    j++;
                }
            }
        }

        return sol;
    }
}
