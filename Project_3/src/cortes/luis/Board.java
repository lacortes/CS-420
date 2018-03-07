package cortes.luis;

public class Board {
    public static final int SIZE = 8;
    CellState[][] board;

    public Board(CellState[][] boardIn) {
        this.board = boardIn;
    }

    public Board() {
        this.board = new CellState[Board.SIZE][Board.SIZE];
        initBoard();
    }

    public CellState getCell(int row, int col) {
        return board[row][col];
    }

    public void makeMove(int row, int col, CellState cellState) {
        board[row][col] = cellState;
    }

    public void makeMove(Move move) {
        board[move.getRow()][move.getCol()] = move.getType();
    }

    public boolean isWinning(CellState cellType) {
        // Check if cell type has 4 in a row
        return checkForNInARow(4, cellType);
    }

    public CellState[][] getCopy() {
        CellState[][] copy = new CellState[Board.SIZE][Board.SIZE];
        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                copy[i][j] = this.board[i][j];
            }
        }
        return copy;
    }

    public int evaluate(CellState cellType) {
        int max = 0;
        if (checkForNInARow(4, cellType))
            max = 4;
        else if (checkForNInARow(3, cellType))
            max = 3;
        else if (checkForNInARow(2, cellType))
            max = 2;
        else if (checkForNInARow(1, cellType))
            max = 1;

        if (cellType == CellState.USER)
            return 0 - max;
        return max;
    }

    public int evalMove(int i, int j, CellState cellType) {
        CellState savedState = board[i][j];
        board[i][j] = cellType;  // Change cell to reflect cellType at position

        // get max count of same type cell in a row
        int[] numbers = new int[4];  // number of directions
        int max = 0;  // Assume max is at index 0

        numbers[0] = countLeft(cellType, i , j, 0);
        numbers[1] = countRight(cellType, i , j, 0);
        numbers[2] = countDown(cellType, i, j, 0);
        numbers[3] = countUp(cellType, i, j, 0);

        // Find max
        for (int index = 0; index < numbers.length; index++) {
            if (numbers[index] > max)
                max = index;
        }
        // undo change
        board[i][j] = savedState;
        return numbers[max];
    }

    public boolean isMovesLeft() {
        for (int i = 0; i<Board.SIZE; i++)
            for (int j = 0; j<Board.SIZE; j++)
                if (board[i][j] == CellState.EMPTY )
                    return true;
        return false;
    }

    private boolean checkForNInARow(int n, CellState cellType) {
        // Checks if celltype ( Computer, user) has n in a row
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board[i][j] == cellType) {
                    // Check cellType has n in a row
                    if (countUp(cellType, i, j, 0) == n ||
                            countDown(cellType, i, j, 0) == n ||
                            countLeft(cellType, i, j, 0) == n ||
                            countRight(cellType, i, j, 0) == n )
                        return true;
                }
            }
        }
        return false;
    }

    private int countUp(CellState type, int row, int col, int count) {
        // Move up board until different symbol found or out of bounds
        if (row < 0 || board[row][col] != type) return count;
        if (board[row][col] == type) count++;
        count = countUp(type, row -1, col, count);
        return count;
    }

    private int countDown(CellState type, int row, int col, int count) {
        // Move up board until different symbol found or out of bounds
        if (row > Board.SIZE -1 || board[row][col] != type) return count;
        if (board[row][col] == type) count++;
        count = countDown(type, row + 1, col, count);
        return count;
    }

    private int countLeft(CellState type, int row, int col, int count) {
        // Move left on board until different symbol found or out of bounds
        if (col < 0 || board[row][col] != type) return count;
        if (board[row][col] == type) count++;
        count = countLeft(type, row, col - 1, count);
        return count;
    }

    private int countRight(CellState type, int row, int col, int count) {
        // Move left on board until different symbol found or out of bounds
        if (col > Board.SIZE-1 || board[row][col] != type) return count;
        if (board[row][col] == type) count++;
        count = countRight(type, row, col + 1, count);
        return count;
    }

    private void initBoard() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                string.append(board[i][j]);
                string.append("  ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
