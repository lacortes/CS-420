import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class PuzzleSolver {
    public static void main(String[] args) {
        if (args.length == 2) {
            String arg1 = args[0];
            String arg2 = args[1];

            if (arg1.equalsIgnoreCase("--file") || arg1.equalsIgnoreCase("-f")) {
                new PuzzleSolver(args[1]); // Flag
            } else {
                System.out.println("Usage: java PuzzleSolver [-f | --file <pathToPuzzlesFile.txt>]");
            }
        } else {
            commandLineUi();
        }
    }

    public static void commandLineUi() {
        Scanner kb = new Scanner(System.in);
        boolean again = false;
        do {
            System.out.println("+-----------------+");
            System.out.println("| 8-puzzle solver |");
            System.out.println("+-----------------+");
            System.out.println("(1) Generate random puzzle");
            System.out.println("(2) Enter custom 8-puzzle");
            System.out.println("(3) Quit");
            System.out.print("===> ");
            int input = kb.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Generating random puzzle ...");
                    break;
                case 2:
                    System.out.println("Custom Puzzle");
                    break;
                case 3:
                    break;
                default:
                    again = true;
                    break;
            }
        } while(again);
    }

    private List<Puzzle> puzzles = new LinkedList<>();

    public PuzzleSolver(String fileName) {
        readInPuzzles(fileName);
        System.out.print(puzzles.get(0));
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
