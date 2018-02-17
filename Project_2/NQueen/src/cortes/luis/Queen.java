package cortes.luis;

public class Queen {
    private int row;
    private int col;

    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean inTrouble(Queen otherQueen) {
        // Check if same row or same column
        if (this.col == otherQueen.getCol() || this.row == otherQueen.getRow())
            return true;
        // Check if on same diagonal
        else if (Math.abs(otherQueen.getCol() - this.col)== Math.abs(otherQueen.getRow() - this.row))
            return true;
        else
            return false;
    }
}
