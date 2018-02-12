public class Left extends Action {
    public Left(int[][] puzzle, int row, int col) {
        super(puzzle, row, col);
        this.actionType = Type.LEFT;
    }

    @Override
    public int[][] move() {
        int leftTileRow = this.row;
        int leftTileCol = this.col - 1;

        int temp = this.puzzle[leftTileRow][leftTileCol];
        this.puzzle[leftTileRow][leftTileCol] = this.puzzle[this.row][this.col];
        this.puzzle[this.row][this.col] = temp;

        return this.puzzle;
    }
}
