import java.util.Iterator;
import java.util.List;

public class Puzzle {
    private List<Integer> numbers;
    private int[][] puzzleBoard;
    private int puzzleSize;

    public Puzzle(List<Integer> numbers, int puzzleSize) {
        this.numbers = numbers;
        this.puzzleSize = puzzleSize;
        this.puzzleBoard = new int[this.puzzleSize][this.puzzleSize];

        this.createPuzzle(this.numbers.iterator());
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
    }

    public int[][] getPuzzle() {
        return this.puzzleBoard;
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

    private void createPuzzle(Iterator<Integer> numbers) {
        for (int i = 0; i < this.puzzleSize; i++) {
            for (int j = 0; j < this.puzzleSize; j++) {
                this.puzzleBoard[i][j] = numbers.next();
            }
        }
    }
}
