import java.util.Stack;

public class ValidParenthesisString {
    public static void main(String[] args) {
        System.out.println(checkValidString("(((**)"));

    }

    public static boolean checkValidString2(String s) {
        int lives = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(c);
            else if (c == '*')
                lives++;
            else {
                if (!stack.empty() && stack.peek() == '(')
                    stack.pop();
                else if (lives > 0)
                    lives--;
                else
                    return false;
            }
        }
        return stack.empty() || lives >= stack.size();
    }

    public static boolean checkValidString(String s) {
        int minLeft = 0;
        int maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minLeft++; maxLeft++;
            }
            else if( c == ')') {
                minLeft--;
                maxLeft--;
            }
            else {
                minLeft--;
                maxLeft++;
            }
            if(maxLeft < 0)
                return false;
            minLeft = Math.max(minLeft, 0);
        }
        return minLeft == 0;
    }


}

// My two cents on the reset of negative leftMin, basically there're two sources we decrease the values of leftMin:
// 1. when we meet the ')' 
// 2. encounter '*'. 
// If we have more than enough of ')' leftMax will become negative, and we will directly return false. However, if we don't return, and we get negative leftMin, which means we get more than enough '*' since we can transform the '*' to an empty string, this is how this -1 to 0 comes. 
// For eg,  
// (**
/* 
An intuitive explanation: As we progress through the string, our minimum and maximum counts of unmatched left parentheses (`leftmin` and `leftmax`) dynamically change. If the `leftmin` becomes negative, it indicates that we've encountered more right parentheses than the total number of corresponding left parentheses and asterisks seen so far. In such cases, we can revise the previous characters to include an empty space, utilizing the wildcard '*' as an optional left parenthesis. This gives the string another chance to remain valid. 

However, if the `leftmax` becomes negative, it signifies an irrecoverable situation. This occurs when, despite using all wildcards as left parentheses, the count of right parentheses exceeds the count of remaining unmatched left parentheses and asterisks. In essence, it means that the string cannot be balanced, rendering it invalid. This approach ensures that the string's validity is continuously monitored and maintained throughout the traversal.

 */