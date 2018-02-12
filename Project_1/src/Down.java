public class Down extends Action {
    public Down(int[][] puzzle, int row, int col) {
        super(puzzle, row, col);
        this.actionType = Type.DOWN;
    }

    @Override
    public int[][] move() {
        int downTileRow = this.row + 1;
        int downTileCol = this.col;

        int temp = this.puzzle[downTileRow][downTileCol];
        this.puzzle[downTileRow][downTileCol] = this.puzzle[this.row][this.col];
        this.puzzle[this.row][this.col] = temp;

        return this.puzzle;
    }
}
