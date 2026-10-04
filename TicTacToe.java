//Making a TicTacToe game using Java.
//Make sure you have a partner to play it with :)

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        char player = 'X';
        int moves = 0;

        while (true) {

            // Display the board
            
            System.out.println( board[0][0] + " | " + board[0][1] + " | " + board[0][2] );
            System.out.println("---------");
            System.out.println( board[1][0] + " | " + board[1][1] + " | " + board[1][2] );
            System.out.println("---------");
            System.out.println( board[2][0] + " | " + board[2][1] + " | " + board[2][2] );
            

            // Ask for input
            System.out.print("Player " + player + ", Enter row (0-2): ");
            int row = sc.nextInt();

            System.out.print("Enter column (0-2): ");
            int column = sc.nextInt();

            // Check if position is valid
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            // Check if cell is already occupied
            if (board[row][column] != ' ') {
                System.out.println("Invalid move as cell is occupied, try again!");
                continue;
            }

            // Put X or O in the selected position
            board[row][column] = player;
            moves++;

            // Check rows
            boolean win = false;

            for (int i = 0; i < 3; i++) {
                if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                    win = true;
                }
            }

            // Check columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player) {
                    win = true;
                }
            }

            // Check diagonals
            if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
                win = true;
            }

            if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
                win = true;
            }

            // If player wins
            if (win) {

                System.out.println();

                
                System.out.println( board[0][0] + " | " + board[0][1] + " | " + board[0][2] );
                System.out.println("---------");
                System.out.println( board[1][0] + " | " + board[1][1] + " | " + board[1][2] );
                System.out.println("---------");
                System.out.println( board[2][0] + " | " + board[2][1] + " | " + board[2][2] );
                

                System.out.println("Player " + player + " wins!");
                break;
            }

            // If board is full
            if (moves == 9) {

                System.out.println();
                System.out.println("It's a draw!");
                break;
            }

            // Change player
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
        }

        sc.close();
    }
}