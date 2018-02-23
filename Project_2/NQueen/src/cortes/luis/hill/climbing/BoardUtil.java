package cortes.luis.hill.climbing;

import cortes.luis.genetic.Individual;

import java.util.Random;

public class BoardUtil {
    public static void displayBoard(Queen[] queens) {
        char[][] board = new char[queens.length][queens.length];
        StringBuilder boardPrint = new StringBuilder();

        for (int i = 0; i< board.length; i ++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < queens.length; i++) {
            int row = queens[i].getRow();
            int col = queens[i].getCol();
            board[row][col] = 'X';
        }

        for (int i = 0; i< board.length; i ++) {
            for (int j = 0; j < board.length; j++) {
                char symbol = board[i][j];
                boardPrint.append(" ");
                boardPrint.append(symbol);
                boardPrint.append(" ");
            }
            boardPrint.append("\n");
        }
        System.out.print(boardPrint);
    }

    public static Queen[] generateQueens(int dimensions) {
        Queen[] board = new Queen[dimensions]; // Hold 8 queens. One in each column. 8 X 8 board.
        Random rand = new Random();

        for (int i = 0; i < dimensions; i++) {
            board[i] = new Queen(rand.nextInt(dimensions), i);
        }
        return board;
    }

    public static void displayQueens(int[] locations) {
        char[][] board = new char[locations.length][locations.length];
        StringBuilder print = new StringBuilder();


        for (int i = 0; i< board.length; i ++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < locations.length; i++) {
            int row = locations[i];
            int col = i;
            board[row][col] = 'X';
        }

        for (int row = 0; row< board.length; row ++) {
            for (int col = 0; col < board.length; col++) {
                char symbol = board[row][col];
                print.append(" ");
                print.append(symbol);
                print.append(" ");
            }
            print.append("\n");
        }
        System.out.print(print);
    }
}
