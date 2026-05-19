package BinarySearch;

import java.util.HashMap;

public class SearchInRotatedSortedArray {
    
    public static void main(String[] args) {
        int[] a = new int[]{4,5,6,7,8,1,2,3};
        System.out.println(search(a,8));

        HashMap<String, int[]> map = new HashMap<>();
    }

    public static int search(int[] nums, int target) {
        int l = 0; int r = nums.length-1;
        while(l <= r) {
            int m = l + (r-l)/2;
            
            if(nums[m] == target)
                return m;
            if(nums[r] > nums[m]) 
                if(target > nums[m] && target <= nums[r])
                    l = m+1;
                else
                    r = m-1;
            else
                if(target < nums[m] && target >= nums[l])
                    r = m-1;
                else
                    l = m+1;
        }
        return -1;
    }
}
