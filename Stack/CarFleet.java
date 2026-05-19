package Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class CarFleet {
    
        public static void main(String[] args) {
        int[] pos = new int[]{0,4,2};
        int[] speed = new int[]{2,1,3};

        System.out.println(carFleet(10, pos, speed));
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        Stack<Double> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < position.length; i++) {
            map.put(position[i], speed[i]);
        }

        Arrays.sort(position);

        for (int i = position.length-1; i >= 0; i--) {
            
            double time = (double)(target - position[i])/map.get(position[i]);
            if(stack.empty()) {
                stack.push(time);
                continue;
            }
            if (time > stack.peek()) {
                stack.push(time);
            }
        }
        return stack.size();

    }
}
