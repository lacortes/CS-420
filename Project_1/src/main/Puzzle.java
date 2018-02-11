package main;

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

        this.createPuzzleBoard(this.numbers.iterator());
    }

    private void createPuzzleBoard(Iterator<Integer> numbers) {
        for (int i = 0; i < this.puzzleSize; i++) {
            for (int j = 0; j < this.puzzleSize; j++) {
                this.puzzleBoard[i][j] = numbers.next();
            }
        }
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
}
