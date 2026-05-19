import java.util.ArrayList;
import java.util.List;

class WordSearch {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        char[][] board = {
                { 'A', 'B', 'C', 'D' },
                { 'S', 'A', 'A', 'T' },
                { 'A', 'C', 'A', 'E' }
        };
        System.out.println(exist(board, "BASAS"));
    }

    public static boolean exist(char[][] board, String word) {
        boolean[] sol = new boolean[] { false };
        boolean[][] traversed = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0))
                    backtracking(board, word, 1, i, j, sol, traversed);
            }
        }
        return sol[0];
    }

    private static void backtracking(char[][] board, String word, int idx, int r, int c, boolean[] sol,
            boolean[][] traversed) {

        // mark the current position as traversed so that we can't inlude in subsequent
        // searches
        traversed[r][c] = true;
        if (idx == word.length()) {
            sol[0] = true;
            return;
        }
        List<Integer> neighbours = findNeighbours(board, r, c, word.charAt(idx), traversed);
        for (int i = 0; i < neighbours.size(); i += 2) {
            backtracking(board, word, idx + 1, neighbours.get(i), neighbours.get(i + 1), sol, traversed);
        }
        traversed[r][c] = false;
    }

    // finds the neighbours with the next letter we are looking for
    private static List<Integer> findNeighbours(char[][] board, int r, int c, char target, boolean[][] traversed) {
        List<Integer> neighbours = new ArrayList<>();
        if (r > 0 && board[r - 1][c] == target && !traversed[r - 1][c]) {
            neighbours.add(r - 1);
            neighbours.add(c);
        }
        if (r < board.length - 1 && board[r + 1][c] == target && !traversed[r + 1][c]) {
            neighbours.add(r + 1);
            neighbours.add(c);
        }
        if (c > 0 && board[r][c - 1] == target && !traversed[r][c - 1]) {
            neighbours.add(r);
            neighbours.add(c - 1);
        }
        if (c < board[0].length - 1 && board[r][c + 1] == target && !traversed[r][c + 1]) {
            neighbours.add(r);
            neighbours.add(c + 1);
        }
        return neighbours;

    }
}