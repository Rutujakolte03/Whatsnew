public class NQueens {

    // Size of the chessboard (N x N)
    static int N;

    // Function to print the solution matrix
    public static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to check if it's safe to place a queen at board[row][col]
    public static boolean isSafe(int[][] board, int row, int col) {
        // Check column for another queen
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    // Backtracking function to solve N-Queens problem
    public static boolean solveNQueens(int[][] board, int row) {
        // If all queens are placed, return true
        if (row >= N) return true;

        // Try placing a queen in each column one by one
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place queen

                // Recur to place the rest of the queens
                if (solveNQueens(board, row + 1)) return true;

                // If placing queen doesn't lead to a solution, backtrack
                board[row][col] = 0;
            }
        }

        return false; // No place for queen in this configuration
    }

    public static void main(String[] args) {
        N = 4; // Change N as desired
        int[][] board = new int[N][N];

        // Manually place the first queen at a specific position
        int initialRow = 0;
        int initialCol = 1; // Modify this to place the first queen in a desired location
        board[initialRow][initialCol] = 1;

        // Start placing the remaining queens from the next row
        if (solveNQueens(board, initialRow + 1)) {
            System.out.println("Solution for " + N + "-Queens problem with the first queen placed manually:");
            printSolution(board);
        } else {
            System.out.println("No solution exists for " + N + "-Queens problem with the first queen placed manually.");
        }
    }
}
