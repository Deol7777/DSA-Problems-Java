package REDO;

import java.util.Arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] a = new int[] { 1,7,2,5,4,7,3,6 };
        System.out.println(maxArea(a));
    }

    public static int maxArea(int[] heights) {

        int maxArea = 0;
        int l = 0; int r = heights.length-1;
        while (l < r) {
            maxArea = Math.max(maxArea,  (r-l) * Math.min(heights[l], heights[r]));
            if(heights[l] < heights[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }
}

