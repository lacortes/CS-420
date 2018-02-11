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

    public PuzzleSolver(String fileName) {
        readInPuzzles(fileName);
    }

    // Read in puzzle
    private void readInPuzzles(String puzzleFileName) {
        List<Puzzle> puzzles = new LinkedList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(puzzleFileName));
            Iterator<String> iter = strings.iterator();
            while (iter.hasNext()) {
                createPuzzle(iter.next());
            }
        } catch (IOException e) {
            System.out.println("Error reading File!!");
        }
    }

    private void createPuzzle(String puzzleSeq) {
        //  Process 
    }
}
