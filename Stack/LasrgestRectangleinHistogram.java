package Stack;

import java.util.Arrays;
import java.util.Stack;

public class LasrgestRectangleinHistogram {

        public static void main(String[] args) {
        int[] temp = new int[]{7,1,7,2,2,4};
        System.out.println(largestRectangleArea(temp));
    }
    
    public static int largestRectangleArea(int[] heights) {
        
        Stack<Integer> stack = new Stack<>();
        int[] rhsShorter = new int[heights.length];
        int[] lhsShorter = new int[heights.length];

        stack.add(0);
        for (int i = 1; i < heights.length; i++) {
            while(!stack.empty() && heights[stack.peek()] > heights[i]) 
                rhsShorter[stack.peek()] = i - stack.pop();
            stack.push(i);
        }
        System.out.println(Arrays.toString(rhsShorter));
        stack.clear();
        stack.push(heights.length-1);
        for (int i = heights.length-2; i >= 0; i--) {
            while(!stack.empty() && heights[stack.peek()] > heights[i]) 
                lhsShorter[stack.peek()] = stack.pop() - i;
            stack.push(i);
        }
        lhsShorter[0] = 1;
        rhsShorter[heights.length-1] = 1;
        System.out.println(Arrays.toString(lhsShorter));

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < lhsShorter.length; i++) {
            
            int r = rhsShorter[i] == 0 ? heights.length - i : rhsShorter[i];
            int l = lhsShorter[i] == 0 ? i+1 : lhsShorter[i];
            int area = (r+l-1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
