package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinimumStack {
    
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        System.out.println(minStack.getMin()); // return 0
        minStack.pop();
        System.out.println(minStack.top());    // return 2
        System.out.println(minStack.getMin()); // return 1
    }
    
    
    static class MinStack {

        private Stack<List<Integer>> stack;
        
        public MinStack() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            List<Integer> temp= new ArrayList<>();
            int oldMin = val;
            if(!stack.empty()) {
                oldMin = Math.min(stack.peek().get(1), oldMin);     
            }
            temp.add(val);
            temp.add(oldMin);
            stack.push(temp);
            
        }
        
        public void pop() {
            stack.pop();
        }
        
        public int top() {
            return stack.peek().get(0);
        }
        
        public int getMin() {
            return stack.peek().get(1);
        }
    }
}
