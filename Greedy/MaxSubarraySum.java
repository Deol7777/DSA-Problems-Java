public class MaxSubarraySum {


    public static void main(String[] args){

		int[] values = {-3};
        System.out.println(maxSubArray(values));

	
	}

    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int cur : nums) {
            if(sum + cur < 0) {
                sum = 0;

                //if the whole array is negative numbers
                max = Math.max(max, cur);
            }
            else{
                sum += cur;
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}