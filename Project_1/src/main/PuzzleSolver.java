package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class PuzzleSolver {
    public static void main(String[] args) {
        if (args.length > 0 && args.length < 2) {
            new PuzzleSolver(args[0]);
        } else {
            System.out.println("Usage: java PuzzleSolver <pathToPuzzlesFile.txt>");
        }
    }

    private List<Puzzle> puzzles = new LinkedList<>();

    public PuzzleSolver(String fileName) {
        readInPuzzles(fileName);
//        System.out.print(puzzles.get(0));
    }

    /**
     * This method reads in a puzzle from a simple text file and adds each puzzle to the list of Puzzles.
     * @param puzzleFileName
     */
    private void readInPuzzles(String puzzleFileName) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(puzzleFileName));
            Iterator<String> iter = strings.iterator();
            while (iter.hasNext()) {
                puzzles.add(createPuzzle(iter.next()));
            }
        } catch (IOException e) {
            System.out.println("Error reading File!!");
        }
    }

    /**
     * This method takes in a string puzzle sequence ("075648123") and returns it to as a Puzzle.
     * @param puzzleSeq A string representation of the puzzle.
     * @return Puzzle
     */
    private Puzzle createPuzzle(String puzzleSeq) {
        final int BOARD_SIZE = 3; // 3 x 3 board
        List<Integer> numSeq = new LinkedList<>();

        // Change string token into an integer
        for (int index = 0; index < puzzleSeq.length(); index++) {
            String token = puzzleSeq.substring(index, index + 1);
            int tokenInt = Integer.parseInt(token);
            numSeq.add(tokenInt);
        }
        return new Puzzle(numSeq, BOARD_SIZE);
    }
}
