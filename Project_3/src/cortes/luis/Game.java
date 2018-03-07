package cortes.luis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Move> moves;
    private Board board;
    private Random random;
    private int timeLimit;

    public Game() {
        initializeGame();
        displayBoard();
        askForSeconds();
        makeFirstMove();
        playGame();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.random = new Random();
        this.moves = new ArrayList<>();
    }

    private void playGame() {
        boolean keepPlaying = true;
        while (keepPlaying) {
            Move playerMove = playerTurn();
            board.makeMove(playerMove);

            displayBoard();
            displayMove(playerMove);
            System.out.println();

            // Computer move
            MinMax search = new MinMax(timeLimit);
            Move bestMove = search.alphaBeta(board);

            board.makeMove(bestMove.getRow(), bestMove.getCol(), bestMove.getType());
            displayBoard();
            displayMove(bestMove);
            System.out.println();

            int computer = board.evaluate(CellState.COMPUTER);
            int user = board.evaluate(CellState.USER);

            if (computer == 4 || user == -4)
                keepPlaying = false;

            computer = board.evaluate(CellState.COMPUTER);
            user = board.evaluate(CellState.USER);

            if (computer == 4 || user == -4)
                keepPlaying = false;
        }
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
                Move computerTurn = new Move(row, col, CellState.COMPUTER);

                board.makeMove(computerTurn);
//                moves.add(new Move(row, col, CellState.COMPUTER));

                displayBoard();
                displayMove(computerTurn);
                System.out.println();

                break;
            } else if (choice == 'b')
                break;
            System.out.println("Try again!");
        }
    }

    private Move playerTurn() {
        Scanner kb = new Scanner(System.in);

        boolean again = true;
        char rowChoice;
        int colChoice;
        do {
            // player makes move
            System.out.println("Enter Move");
            System.out.print("Enter row (a - h): ");
            rowChoice = kb.nextLine().charAt(0);

            System.out.print("Enter col (1 - 8): ");
            colChoice = kb.nextInt();

            // check row
            int offset = 48;
            if ((rowChoice - offset) < '0' || (rowChoice - offset) > '8' || colChoice < 1 || colChoice > 8) {
                System.out.println("Try again!");
                continue;
            }

            int row = rowChoice - 'a';
            int col = colChoice -1;

            if (board.getCell(row, col) != CellState.EMPTY) {
                System.out.println("Try again");
                continue;
            }

            return new Move(row, col, CellState.USER);


        } while(again);
        return null;
    }

    private void askForSeconds() {
        Scanner kb = new Scanner(System.in);
        while(true) {
            System.out.print("Time for me to think (sec): ");
            int choice =  kb.nextInt();

            if (choice > 30 || choice < 0) {
                System.out.println("Try again!");
                continue;
            }
            this.timeLimit = choice;
            break;
        }
    }

    private boolean verifyMove(int row, int col) {
        // Verify move is within board range
        if (row < 0 || row > Board.SIZE-1 || col < 0 || col > Board.SIZE-1)
            return false;

        CellState cell = board.getCell(row, col);
        if (cell != CellState.EMPTY)
            return false;
        return true;
    }

    private void displayMove(Move move) {
        String word;

        if (move.getType() == CellState.COMPUTER)
            word = "Computer";
        else
            word = "Player";

        System.out.println(word+"'s move is: "+move.getRowMove()+(move.getCol()+1));
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
