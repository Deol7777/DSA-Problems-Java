public class JumpGame2 {
    
    public static void main(String[] args) {

        int[] values = { 2,4,1,1,1,1 };
        System.out.println(canJump(values));

    }


    public static int canJump(int[] nums) {

        if(nums[0] == 0)
            return 0;
        int start = 0;
        int newStart = 0;
        int jumps = 1;
        // index where we can jumpt till
        int end = nums[start];
        while (true) {
            if (end >= nums.length - 1)
                return jumps;
            jumps++;
            for (int i = end; i > start; i--) {
                // if at current index we can jump more than the current end, we update start
                // and end (next time we start from the start that gets the max end)
                int newEndFromHere = nums[i] + i;
                if (newEndFromHere > end) {
                    end = newEndFromHere;
                    newStart = i;
                }
            }
            start = newStart;
        }
    }
}
