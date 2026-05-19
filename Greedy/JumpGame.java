public class JumpGame {
    public static void main(String[] args) {

        int[] values = { 1, 2, 0, 1, 0 };
        System.out.println(canJump(values));

    }

    // It was a good attempt, but [3,0,8,2,0,0,1] made it not work because since
    // from 3 at the beginning we take 2, we miss the 8 and the end is not reachable
    // from 2
    //probably can use in JG2
    public static boolean canJump2(int[] nums) {

        int start = 0;
        boolean again = true;
        // index where we can jumpt till
        int end = nums[start];
        while (again) {
            if (end >= nums.length - 1)
                return true;
            again = false;
            for (int i = end; i > start; i--) {
                // if at current index we can jump more than the current end, we update start
                // and end
                // and break from loop
                int newEndFromHere = nums[i] + i;
                if (newEndFromHere > end) {
                    start = i;
                    end = newEndFromHere;
                    again = true;
                    break;
                }
            }
        }
        return false;
    }

    public static boolean canJump(int[] nums) {
        int idx = 0;
        //just to enter in the loop
        int stamina = 1;
        while(stamina > 0) {
            stamina--;
            stamina = Math.max(stamina, nums[idx]);
            if(stamina + idx >= nums.length-1)
                return true;
            idx++;
        }
        return false;
    }
}
