package cortes.luis.hill.climbing;

import cortes.luis.Algorithm;

import java.util.*;

public class HillClimbing implements Algorithm {
    private int restarts;
    private int dimension;
    private Queen[] initBoard;
    private long elapsedTime = 0;

    public HillClimbing(int dimension, int restarts) {
        this.restarts = restarts;
        this.dimension= dimension;
        this.initBoard = BoardUtil.generateQueens(this.dimension);
    }

    @Override
    public Stack<State> solve() {
        Stack<State> tries = new Stack<>();
        int finalHeurustic = -1;
        long start = System.currentTimeMillis();
        while (restarts > 0 && finalHeurustic != 0) {
            try {
                State answer = hillClimbing();
                finalHeurustic = answer.getHeurustic();
                tries.push(answer);
                this.initBoard = BoardUtil.generateQueens(this.dimension);
                restarts--;
            } catch (CloneNotSupportedException ex) {ex.printStackTrace();}
        }
        this.elapsedTime = System.currentTimeMillis() - start;
        return tries;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    //function HILL-CLIMBING(problem) returns a state that is a local maximum
    private State hillClimbing() throws CloneNotSupportedException {
        //current ←MAKE-NODE(problem.INITIAL-STATE)
        State current = new State(initBoard);
        // loop do
        while (true) {
            PriorityQueue<State> neighbors = generateSuccessors(current);
            State neighbor = neighbors.poll();
            // if neighbor.VALUE >= current.VALUE then return current.STATE
            if (neighbor.getHeurustic() >= current.getHeurustic())
                return current;
            // current←neighbor
            current = neighbor;
        }
    }

    private PriorityQueue<State> generateSuccessors(State currentState) throws CloneNotSupportedException {
        PriorityQueue<State> successors = new PriorityQueue<>(new StateComparator());

        // Iterate through every column on board
        for (int col = 0; col < currentState.getBoard().length; col++) {
            Queen currentQueen = currentState.getBoard()[col];
            for (int row = 0; row < currentState.getBoard().length; row++) {
                // Do not generate a duplicate state
                if (currentQueen.getRow() == row) {
                    continue;
                }
                // Generate a successor state. Move current queen out of its current row to new row;
                Queen[] tempBoard = currentState.getBoard();
                Queen successor = currentQueen.clone();
                successor.setRow(row);

                // Replace current queen position with successor queen
                tempBoard[col] = successor.clone();
                successors.add(new State(tempBoard));
            }
        }
        return successors;
    }
}
