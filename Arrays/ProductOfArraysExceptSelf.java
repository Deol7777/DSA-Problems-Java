import java.util.Arrays;

public class ProductOfArraysExceptSelf {
    
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(a)));
      }

      public static int[] productExceptSelf(int[] nums) {
        
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        prefix[0] = 1;suffix[nums.length-1] = 1;

        for (int i = 1, j=nums.length-2; i < nums.length; i++, j--) {
            prefix[i] = nums[i-1] * prefix[i-1];
            suffix[j] = nums[j+1] * suffix[j+1];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = prefix[i] * suffix[i];
        }

        return nums;
      }


}
