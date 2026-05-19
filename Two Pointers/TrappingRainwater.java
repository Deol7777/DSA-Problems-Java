import java.util.Arrays;

public class TrappingRainwater {
    public static void main(String[] args) {
        int[] a = new int[] { 0,2,0,3,1,0,1,3,2,1 };
        System.out.println(trap(a));
    }

    public static int trap(int[] height) {
        int [] rmax = new int[height.length];
        rmax[rmax.length-1] = Integer.MIN_VALUE;
        for (int i = height.length-2; i > 0; i--) {
            rmax[i] = Math.max(rmax[i+1], height[i+1]);
        }
        System.out.println(Arrays.toString(rmax));
        int trapped = 0;
        int lMax = height[0];
        for(int i = 1; i < height.length-1; i++) {
            int water = Math.min(lMax, rmax[i]) - height[i];
            if(water > 0) trapped+=water;
            lMax = Math.max(lMax, height[i]);
        }

        return trapped;
    }
}
