import java.util.Arrays;

public class NQueens {

    // Method to solve N Queens problem using backtracking
    public static void solveNQueens(int n) {
        int[][] board = new int[n][n];
        if (solveNQueensUtil(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Solution does not exist.");
        }
    }

    // Helper method to solve N Queens problem recursively
    private static boolean solveNQueensUtil(int[][] board, int col) {
        int n = board.length;
        if (col >= n) {
            return true; // All queens are placed successfully
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place queen at position (i, col)

                // Recur to place rest of the queens
                if (solveNQueensUtil(board, col + 1)) {
                    return true;
                }

                // If placing queen at (i, col) doesn't lead to a solution, backtrack
                board[i][col] = 0;
            }
        }

        return false; // If queen cannot be placed in any row of this column
    }

    // Helper method to check if a queen can be placed at board[row][col]
    private static boolean isSafe(int[][] board, int row, int col) {
        int n = board.length;

        // Check this row on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true; // If no queen is placed in the left side
    }

    // Helper method to print the board
    private static void printBoard(int[][] board) {
        int n = board.length;
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Main method to test the N Queens problem
    public static void main(String[] args) {
        int n = 4; // Size of the chessboard
        solveNQueens(n);
    }
}
