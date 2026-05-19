import java.util.ArrayList;
import java.util.List;

public class Nqueens {
    public static void main(String[] args) {

        System.out.println(solveNQueens(5));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> sol = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtracking(board, 0, sol);
        return sol;
    }

    private static void backtracking(char[][] board, int r, List<List<String>> sol) {
        if (r == board.length) {
            addToSolution(board, sol);
        }

        //for a row get the valid columns (can be a list of ints)
        List<Integer> validCols = getvalidPositions(board, r);
        for (Integer col : validCols) {
            board[r][col] = 'Q';
            backtracking(board, r + 1, sol);
            board[r][col] = '.';
        }
    }

    //check the columns in a row
    private static List<Integer> getvalidPositions(char[][] board, int r) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < board.length; j++) {
            if (checkPosition(board, r, j))
                list.add(j);
        }
        return list;
    }

    //only check above positions becoz we are going down from above
    private static boolean checkPosition(char[][] board, int r, int c) {

        //above in a column
        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] == 'Q') {
               return false;
            }
        }

        //diagonal above left
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //diagonal above right
        for (int i = r - 1, j = c + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;

    }

    private static void addToSolution(char[][] board, List<List<String>> sol) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
            }
            temp.add(sb.toString());
        }
        sol.add(temp);
    }
}
