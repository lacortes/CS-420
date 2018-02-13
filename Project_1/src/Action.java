public abstract class Action {
    int[][] puzzle;
    int row;
    int col;
    Type actionType = null;
    enum Type {
        LEFT, RIGHT, UP, DOWN, NOTHING
    }

    public Action(int[][] puzzle, int row, int col) {
        this.puzzle = puzzle;
        this.row = row;
        this.col = col;
    }

    public abstract int[][] move();

    public Type getActionType() {
        return this.actionType;
    }
}
