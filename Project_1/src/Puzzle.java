import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Puzzle {
    private final int ACTIONS_NUM = 4;
    private List<Integer> numbers;
    private List<Action> actions;
    private int[][] puzzleBoard;
    private int puzzleSize;
    private int emptyRow;
    private int emptyCol;

    public Puzzle(List<Integer> numbers, int puzzleSize) {
        this.numbers = numbers;
        this.puzzleSize = puzzleSize;
        this.puzzleBoard = new int[this.puzzleSize][this.puzzleSize];

        this.createPuzzle(this.numbers.iterator());
        this.updateEmptyTileLocation();
        this.actions = this.determineMovements();
    }

    public void setPuzzle(int[][] incomingPuzzle) throws Exception {
        // Verify puzzle dimensions are correct
        if (incomingPuzzle.length == this.puzzleSize) {
            for (int i = 0; i < this.puzzleSize; i++) {
                if (incomingPuzzle[i].length != this.puzzleSize) {
                    throw new Exception("Incoming Puzzle not of correct dimension!");
                }
            }
        } else {
            throw new Exception("Incoming Puzzle not of correct dimension!");
        }
        this.puzzleBoard = incomingPuzzle;
        this.updateEmptyTileLocation();
        this.actions = this.determineMovements();
    }

    public int[][] getPuzzle() {
        return this.getCopy(this.puzzleBoard);
    }

    public List<Action> getActions() {
        return this.actions;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.puzzleSize; i++) {
            for (int j = 0; j < this.puzzleSize; j++) {
                string.append(puzzleBoard[i][j]);
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * This method will determine what actions/movements are possible with the empty tile.
     * @return List A list of actions that are able to be performed on the current puzzle state.
     */
    private List<Action> determineMovements() {
        List<Action> actions = new ArrayList<>(this.ACTIONS_NUM);
        if (this.emptyCol > 0) { // Can move Left
            actions.add(new Left(this.getCopy(this.puzzleBoard), this.emptyRow, this.emptyCol));
        }
        if (this.emptyCol < this.puzzleSize -1) {  // Can move Right
            actions.add(new Right(this.getCopy(this.puzzleBoard), this.emptyRow, this.emptyCol));
        }
        if (this.emptyRow > 0) {  // Can move Up
            actions.add(new Up(this.getCopy(this.puzzleBoard), this.emptyRow, this.emptyCol));
        }
        if (this.emptyRow < this.puzzleSize-1) {  // Can move down
            actions.add(new Down(this.getCopy(this.puzzleBoard), this.emptyRow, this.emptyCol));
        }
        return actions;
    }

    /**
     * This method will run through the 2-D array of the board representation to determine
     * location of the 'empty' tile (0)
     */
    private void updateEmptyTileLocation() {
        this.emptyRow = -1;
        this.emptyCol = -1;
        for (int i = 0; i < this.puzzleSize; i++) {
            for (int j = 0; j < this.puzzleSize; j++) {
                // Found empty tile
                if (this.puzzleBoard[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
            }
        }
    }

    /**
     * This method will place the corresponding value to a 2-D array representation of the puzzle
     * @param numbers A sequence of numbers representing the puzzle.
     */
    private void createPuzzle(Iterator<Integer> numbers) {
        for (int i = 0; i < this.puzzleSize; i++) {
            for (int j = 0; j < this.puzzleSize; j++) {
                this.puzzleBoard[i][j] = numbers.next();
            }
        }
    }

    private int[][] getCopy(int[][] board) {
        int size = board.length;
        int[][] copy = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }
}
