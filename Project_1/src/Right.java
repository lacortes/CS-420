public class Right extends Action{
    public Right(int[][] puzzle, int row, int col) {
        super(puzzle, row, col);
        this.actionType = Type.RIGHT;
    }

    @Override
    public int[][] move() {
        int rightTileRow = this.row;
        int rightTileCol = this.col + 1;

        int temp = this.puzzle[rightTileRow][rightTileCol];
        this.puzzle[rightTileRow][rightTileCol] = this.puzzle[this.row][this.col];
        this.puzzle[this.row][this.col] = temp;

        return this.puzzle;
    }
}
