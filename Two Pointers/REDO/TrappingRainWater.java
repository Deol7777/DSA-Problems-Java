package REDO;

import java.util.Arrays;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] a = new int[] { 0,2,0,3,1,0,1,3,2,1 };
        System.out.println(trap(a));
    }

    public static int trap(int[] height) {
        int trapped = 0;
        int []rightMax = new int[height.length];
        int curRightMax = 0;
        for (int j = height.length-1; j > 0;  j--) {
            rightMax[j] = curRightMax;
            curRightMax = Math.max(curRightMax, height[j]);
        }

        int leftMaxCur = 0;
        for (int i = 0; i < height.length; i++) {
            
            //get the water for this pod and only add if +ve
            int waterHere = Math.min(leftMaxCur, rightMax[i]) - height[i]; 
            trapped += waterHere > 0 ? waterHere:0; 

            //only locally needed - so didn;t need to put in an array
            leftMaxCur = Math.max(leftMaxCur, height[i]);
        }


        return trapped;
    }
}
