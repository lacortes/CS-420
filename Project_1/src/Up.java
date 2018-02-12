public class Up extends Action {
    public Up(int[][] puzzle, int row, int col) {
        super(puzzle, row, col);
        this.actionType = Type.UP;
    }

    @Override
    public int[][] move() {
        int upTileRow = this.row - 1;
        int upTileCol = this.col;

        int temp = this.puzzle[upTileRow][upTileCol];
        this.puzzle[upTileRow][upTileCol] = this.puzzle[this.row][this.col];
        this.puzzle[this.row][this.col] = temp;

        return this.puzzle;
    }
}
