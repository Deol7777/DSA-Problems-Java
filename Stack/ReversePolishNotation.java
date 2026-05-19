package Stack;

import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] s = new String[] { "4","13","5","/","+" };
        System.out.println(evalRPN(s));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case ("+"):
                    stack.push(stack.pop() + stack.pop());
                    break;
                case ("*"):
                    stack.push(stack.pop() * stack.pop());
                    break;
                case ("-"):
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b-a);
                    break;
                case ("/"):
                    int c = stack.pop();
                    int d = stack.pop();
                    stack.push(d/c);
                    break;
                default:
                    stack.push(Integer.valueOf(s));

            }
        }
        return stack.pop();
    }
}
