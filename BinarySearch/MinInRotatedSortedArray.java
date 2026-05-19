package BinarySearch;

public class MinInRotatedSortedArray {
    
    public static void main(String[] args) {
        int[] a = new int[]{25,10,23,4};
        System.out.println(findMin(a));
    }

    public static int findMin(int[] nums) {
        int l = 0; int r = nums.length-1;
        while(l < r) {
            int m = l + (r-l)/2;
            if(nums[m] > nums[r])
                l = m+1;
            else
                r = m;
        }
        return r;
    }
}
