package cortes.luis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Move> moves;
    private Board board;
    private Random random;

    public Game() {
        initializeGame();
        displayBoard();
        makeFirstMove();
        playGame();
        displayBoard();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.random = new Random();
        this.moves = new ArrayList<>();
    }

    private void playGame() {
//        while(board.isRunning()) {
//
//            System.out.print("User move: ");
//            // User makes move
//        }
        board.makeMove(0, 7, CellState.COMPUTER);
        board.makeMove(0, 6, CellState.COMPUTER);
        board.makeMove(0, 5, CellState.COMPUTER);
        board.makeMove(3, 4, CellState.USER);
        board.makeMove(3, 5, CellState.USER);
        System.out.println("user: " +board.evaluate(CellState.USER));
        System.out.println("comp: " +board.evaluate(CellState.COMPUTER));

        int great = board.evalMove(0, 4, CellState.COMPUTER);
        System.out.println("EVAL: "+great);

        MinMax search = new MinMax(10);
        Move best = search.alphaBeta(board);
        System.out.println("Best: "+best);
    }

    private void checkStatus() {
        if (board.isWinning(CellState.COMPUTER))
            System.out.println(" Computer won! ");
        else if (board.isWinning(CellState.USER))
            System.out.println("User won! ");
        else
            System.out.println("Draw!");
    }

    private void makeFirstMove() {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Who goes first?\n\t(a) Computer\n\t(b) Player: ");
            char choice = kb.nextLine().charAt(0);

            if (choice == 'a') {
                int row = 3, col = 3;
                board.makeMove(row, col, CellState.COMPUTER);
                moves.add(new Move(row, col, CellState.COMPUTER));
                break;
            } else if (choice == 'b')
                break;
            System.out.println("Try again!");
        }
    }

    private void displayBoard() {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        Iterator<Move> movesIter = this.moves.iterator();

        System.out.print("  1  2  3  4  5  6  7  8");
        System.out.print("     ");
        System.out.println("Player vs. Opponent");

        for (int i = 0; i < Board.SIZE; i++) {
            System.out.print(letters[i]);
            for (int j = 0; j < Board.SIZE; j++) {
                System.out.print(" "+board.getCell(i, j)+" ");
            }

            if (movesIter.hasNext()) {
                Move move = movesIter.next();
                System.out.print("       ");
                System.out.print((i + 1)+"."+"  "+move.getRowMove()+(move.getCol()+1));
            }
            System.out.println();
        }
    }
}
