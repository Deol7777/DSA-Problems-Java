package REDO;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {

        
        List<List<Integer>> temp = subsets(new int[]{1,2,3});
        for (List<Integer> inner : temp) {
            System.out.println(inner);
}
    }

    public static List<List<Integer>> subsets(int[] nums) {
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
        backtracking(nums, i+1, temp, sol);
        temp.add(nums[i]);
        backtracking(nums, i+1, temp, sol);
        temp.remove(temp.size()-1);
    }

}
