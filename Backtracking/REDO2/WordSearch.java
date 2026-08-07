package REDO2;

public class WordSearch {
    public static void main(String[] args) {
        String[] grid = { "ABCE", "SFCS", "ADEE" };
        test("ABCCED", grid, "ABCCED", true);
        test("SEE", grid, "SEE", true);
        test("ABCB (reuse not allowed)", grid, "ABCB", false);

        test("single cell hit", new String[] { "A" }, "A", true);
        test("single cell miss", new String[] { "A" }, "B", false);
        // word longer than the whole board
        test("too long", new String[] { "AB", "CD" }, "ABCDA", false);
        // must snake across rows
        test("snake", new String[] { "ABCE", "SFES", "ADEE" }, "ABCESEEEFS", true);
    }

    // fresh instance + fresh board each call: solution may use instance fields and
    // may mark visited cells in place
    static void test(String name, String[] rows, String word, boolean expected) {
        boolean actual = new WordSearch().exist(toBoard(rows), word);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    static char[][] toBoard(String[] rows) {
        char[][] board = new char[rows.length][];
        for (int i = 0; i < rows.length; i++)
            board[i] = rows[i].toCharArray();
        return board;
    }

    int[][] directions = {
            { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (existHelper(board, i, j, 0, word))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, int i, int j, int count, String word) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(count))
            return false;
        if (count == word.length() - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = '#';
        for (int[] dir : directions) {
            if (existHelper(board, i + dir[0], j + dir[1], count + 1, word))
                return true;
        }
        board[i][j] = temp;
        return false;
    }
}
