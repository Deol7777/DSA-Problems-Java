import java.util.Arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] a = new int[] { 1,7,2,5,4,7,3,6 };
        System.out.println(maxArea(a));
    }

    public static int maxArea(int[] heights) {

        int maxWater = 0;
        int i = 0; int j = heights.length - 1;
        while(i < j) {
            maxWater = Math.max(maxWater, Math.min(heights[i], heights[j]) * (j-i));
            if(heights[i] < heights[j])
                i++;
            else
                j--;
        }
        return maxWater;
    }
}
