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
                List<Puzzle> puzzles =  readInPuzzles(arg2);
                Puzzle puzzle = puzzles.get(0);
                System.out.println(puzzle);
                List<Action> actions =  puzzle.getActions();
                System.out.println(actions.size());

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
            kb.nextLine();

            switch (input) {
                case 1:
                    System.out.println("Generating random puzzle ...");
                    break;
                case 2:
                    System.out.println("Custom Puzzle");
                    customPuzzle();
                    break;
                case 3:
                    break;
                default:
                    again = true;
                    break;
            }
        } while(again);
    }

    public static void customPuzzle() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter new Puzzle. Ex: 274351680");
        System.out.print("==> ");
        String puzzleSeq = kb.nextLine();

        Puzzle puzzle = createPuzzle(puzzleSeq);
        System.out.println(puzzle);

        List<Action> puzzleActions = puzzle.getActions();

        // For testing purposes
        for (Action action : puzzleActions) {
            System.out.println();
            System.out.println(action.getActionType());
            System.out.println(displayPuzzle(action.move()));
        }
    }

    public static String displayPuzzle(int[][] puzzleBoard) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                string.append(puzzleBoard[i][j]);
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * This method reads in a puzzle from a simple text file and adds each puzzle to the list of Puzzles.
     * @param puzzleFileName
     */
    public static List<Puzzle> readInPuzzles(String puzzleFileName) {
        try {
            List<Puzzle> puzzles = new LinkedList<>();
            List<String> strings = Files.readAllLines(Paths.get(puzzleFileName));
            Iterator<String> iter = strings.iterator();

            while (iter.hasNext()) {
                Puzzle puzzle = createPuzzle(iter.next());
                puzzles.add(puzzle);
            }
            return puzzles;
        } catch (IOException e) {
            System.out.println("Error reading File!!");
        }
        return null;
    }


    /**
     * This method takes in a string puzzle sequence ("075648123") and returns it to as a Puzzle.
     * @param puzzleSeq A string representation of the puzzle.
     * @return Puzzle
     */
    public static Puzzle createPuzzle(String puzzleSeq) {
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
