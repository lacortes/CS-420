package cortes.luis.hill.climbing;

import java.util.Arrays;

public class State {
    private Queen[] board;
    private int heurustic;

    public State(Queen[] board) {
        this.board = board;
        this.heurustic = this.getHeurustic(board);
    }

    public Queen[] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public int getHeurustic() {
        return heurustic;
    }

    public void setBoard(Queen[] board) {
        this.board = board;
    }

    public void setHeurustic(int heurustic) {
        this.heurustic = heurustic;
    }

    @Override
    public String toString() {
        return "State{" +
                "board=" + Arrays.toString(board) +
                ", heurustic=" + heurustic +
                '}';
    }

    private int getHeurustic(Queen[] board) {
        int value = 0;
        for (int current = 0; current < board.length; current++) {
            for (int next = current+1; next < board.length; next++) {
                Queen currentQueen = board[current];
                Queen nextQueen = board[next];

                // Found a conflicting pair
                if (currentQueen.inTrouble(nextQueen))
                    value++;
            }
        }
        return value;
    }
}

