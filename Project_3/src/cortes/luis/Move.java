package cortes.luis;

public class Move {
    private int row;
    private int col;
    private CellState type;

    public Move(int row, int col, CellState type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public CellState getType() {
        return this.type;
    }

    public char getRowMove() {
        char aStart = 'a';
        if (row == 0)
            return aStart;
        return (char) (aStart + row);
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                ", type=" + type +
                '}';
    }
}
