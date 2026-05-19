package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {

    public static void main(String[] args) {
        String s = "({{{{}}}))";
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        StringBuilder str = new StringBuilder();
        List<String> sol = new ArrayList<>();
        bt(str, n,sol,  0, 0);
        return sol;
    }

    private static void bt(StringBuilder str, int n, List<String> sol, int op, int cl) {

        if(str.length() == n*2) {
            sol.add(str.toString());
            return;
        }
        else {
            if( op < n) {
                str.append("(");
                bt(str, n, sol, op+1, cl );
                str.deleteCharAt(str.length()-1);
                //op--;
            }
            if (cl < op) {
                str.append(")");
                bt(str, n, sol, op, cl+1 );
                str.deleteCharAt(str.length()-1);
                //cl--;
            }
        }
    }
}
