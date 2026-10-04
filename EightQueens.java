// Java program to solve the 8-Queens Problem.
// In an 8-Queens Problem, no 2 Queens can be on the same row/column/diagonal in an 8x8 board.

class EightQueens {

    // Check whether a queen can be safely placed
    static boolean isSafe(int[][] board, int row, int column) {

        int boardSize = board.length;

        // Check the same column
        for (int previousRow = 0; previousRow < row; previousRow++) {
            if (board[previousRow][column] == 1) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int previousRow = row - 1, previousColumn = column - 1;
             previousRow >= 0 && previousColumn >= 0;
             previousRow--, previousColumn--) {

            if (board[previousRow][previousColumn] == 1) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int previousRow = row - 1, nextColumn = column + 1;
             previousRow >= 0 && nextColumn < boardSize;
             previousRow--, nextColumn++) {

            if (board[previousRow][nextColumn] == 1) {
                return false;
            }
        }

        // Queen can be safely placed
        return true;
    }

    // Place queens using backtracking
    static boolean placeQueens(int row, int[][] board) {

        int boardSize = board.length;

        // All queens have been successfully placed
        if (row == boardSize) {
            return true;
        }

        // Try placing the queen in every column
        for (int column = 0; column < boardSize; column++) {

            if (isSafe(board, row, column)) {

                // Place queen
                board[row][column] = 1;

                // Recursively place queen in next row
                if (placeQueens(row + 1, board)) {
                    return true;
                }

                // Backtrack if the placement does not work
                board[row][column] = 0;
            }
        }

        // No valid position found in this row
        return false;
    }

    // Create the board and find a solution
    static int[][] solveEightQueens() {

        int boardSize = 8;

        int[][] board = new int[boardSize][boardSize];

        placeQueens(0, board);

        return board;
    }

    // Display the chess board
    static void displayBoard(int[][] board) {

        System.out.println("8-Queens Solution:");
        System.out.println("------------------");

        for (int row = 0; row < board.length; row++) {

            for (int column = 0; column < board[row].length; column++) {

                if (board[row][column] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] solutionBoard = solveEightQueens();

        displayBoard(solutionBoard);
    }
}