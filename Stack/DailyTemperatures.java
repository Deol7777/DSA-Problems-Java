package Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temp = new int[]{30,38,30,36,35,40,28};
        System.out.println(Arrays.toString(dailyTemperatures(temp)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < temperatures.length-1; i++) {
            while(!stack.empty() && temperatures[i] > stack.peek()[0]) {
                int colderPos = stack.peek()[1];
                result[colderPos] = i - colderPos;
                stack.pop();
            }
            stack.push(new int[]{temperatures[i], i});
        }
        
        return result;

    }
}
