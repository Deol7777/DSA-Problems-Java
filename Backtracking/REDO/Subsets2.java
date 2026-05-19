package REDO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {

        
        List<List<Integer>> temp = subsets(new int[]{1,2,3});
        for (List<Integer> inner : temp) {
            System.out.println(inner);
}
    }

    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> sol = new ArrayList<>();
        backtracking(nums, 0, temp, sol);
        return sol;
    }

    private static void backtracking(int[] nums, int i, List<Integer> temp, List<List<Integer>> sol) {
        if ( i == nums.length) {
            sol.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[i]);
        backtracking(nums, i+1, temp, sol);
        temp.remove(temp.size()-1);
        if( i + 1 < nums.length && nums[i+1] == nums[i])
            backtracking(nums, i+2, temp, sol);
        else
            backtracking(nums, i+1, temp, sol);
    }

}
