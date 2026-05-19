import java.util.HashMap;
import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                { '1', '2', '5', '.', '3', '.', '.', '.', '.' },
                { '4', '.', '.', '5', '.', '.', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '.', '3' },
                { '5', '.', '.', '.', '6', '.', '.', '.', '4' },
                { '.', '.', '.', '8', '.', '3', '.', '.', '5' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '.', '.', '.', '.', '.', '2', '.', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '8' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {

        HashSet<Character> set = new HashSet<>();

        // horizontal checking
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                set.add(board[i][j]);
            }
            set.clear();
        }

        // vertical checking
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '.')
                    continue;
                if (set.contains(board[j][i]))
                    return false;
                set.add(board[j][i]);
            }
            set.clear();
        }

        // check the boxes
        int startRow = 0;
        int startColumn = 0;
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                for (int i = startRow; i < startRow + 3; i++) {
                    for (int j = startColumn; j < startColumn + 3; j++) {
                        if (board[i][j] == '.')
                            continue;
                        if (set.contains(board[i][j]))
                            return false;
                        set.add(board[i][j]);
                    }
                }
                startColumn += 3;
                set.clear();
            }
            startRow+=3;
            startColumn=0;
        }
        return true;
    }
}
