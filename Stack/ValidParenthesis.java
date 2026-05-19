package Stack;

import java.util.List;
import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {
        String s = "({{{{}}}))";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char p = s.charAt(i);

            if( p == '(' || p == '{' || p =='[')
            {    
                stack.push(p);
            }
            else if( !stack.empty() && Math.abs(p - stack.peek()) < 3)
                stack.pop();
            else
                return false;
        }
        return stack.empty();

        

    }
}