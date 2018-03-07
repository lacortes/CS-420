package cortes.luis;

public class MinMax {
    private long startTime;
    private long timeLimit;

    public MinMax(int seconds) {
        this.timeLimit = (seconds * 1000);  // Convert second to milliseconds
    }

    public int maxmin(Board inBoard, boolean isMax) {
        CellState currentPlayer;
        long elapsedTime = System.currentTimeMillis();
        Board board = new Board(inBoard.getCopy());

        if (isMax)
            currentPlayer = CellState.COMPUTER;
        else
            currentPlayer = CellState.USER;

        int score = board.evaluate(currentPlayer);

        if (elapsedTime >= timeLimit) {
            return board.evaluate(currentPlayer);
        }

        if (score == 4)
            return score;

        if (score == -4)
            return score;

        if (!board.isMovesLeft())
            return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (board.getCell(i, j) == CellState.EMPTY) {
                        // Make the move
                        board.makeMove(i, j, CellState.COMPUTER);

                        best = Integer.max(best,
                                maxmin(board, !isMax));

                        // Undo the move
                        board.makeMove(i, j, CellState.EMPTY);
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (board.getCell(i, j) == CellState.EMPTY) {
                        // Make the move
                        board.makeMove(i, j, CellState.USER);

                        best = Integer.max(best,
                                maxmin(board, !isMax));

                        // Undo the move
                        board.makeMove(i, j, CellState.EMPTY);
                    }
                }
            }
            return best;
        }
    }

        // function ALPHA-BETA-SEARCH(state) returns an action
    public Move alphaBeta(Board board) {
        Board testBoard = new Board(board.getCopy());

        // v ←MAX-VALUE(state,−∞,+∞)
        startTime = System.currentTimeMillis();
//        int v = maxValue(testBoard, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        System.out.println("Alpha-Beta: "+v);

        // return the action in ACTIONS(state) with value v
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                CellState cell = board.getCell(i, j);

                if (cell == CellState.EMPTY) {
                    testBoard.makeMove(i, j, CellState.COMPUTER);
                    int moveVal = maxmin(testBoard, true);
                    testBoard.makeMove(i, j, CellState.EMPTY);

                    if (moveVal > bestValue) {
                        bestRow = i;
                        bestCol = j;
                        bestValue = moveVal;
                    }
                }
            }
        }
        return new Move(bestRow, bestCol, CellState.COMPUTER);
    }

    // function MAX-VALUE(state,α, β) returns a utility value
    private int maxValue(Board board, int alpha, int beta) {
        // if TERMINAL-TEST(state) then return UTILITY(state)
        long elapsedTime = System.currentTimeMillis() - startTime;
        int evaluation = board.evaluate(CellState.COMPUTER);
        if (elapsedTime >= timeLimit) {
            return evaluation;
        }

        if (evaluation == 4) {
            return evaluation;
        }
        if (!board.isMovesLeft())
            return 0;
        //v ←−∞
        int v = Integer.MIN_VALUE;

        // for each a in ACTIONS(state) do
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                // Only place moves in empty cells
                if (board.getCell(i, j) == CellState.EMPTY) {
                    board.makeMove(i, j, CellState.COMPUTER);

                    // v ←MAX(v, MIN-VALUE(RESULT(s,a),α, β))
                    v = Integer.max(v, minValue(board, alpha, beta));

                    // if v ≥ β then return v
                    if ( v >= beta)
                        return v;
                    // α←MAX(α, v)
                    alpha = Integer.max(alpha, v);
                }
            }
        }
        //return v
        return v;
    }

    // function MIN-VALUE(state,α, β) returns a utility value
    private int minValue(Board board, int alpha, int beta) {
        // if TERMINAL-TEST(state) then return UTILITY(state)
        long elapsedTime = System.currentTimeMillis() - startTime;
        int evaluation = board.evaluate(CellState.USER);

        if (elapsedTime >= timeLimit)
            return evaluation;

        if (evaluation == -4) {
            return evaluation;
        }

        if (!board.isMovesLeft())
            return 0;

        // v ←+∞
        int v = Integer.MAX_VALUE;

        // for each a in ACTIONS(state) do
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getCell(i, j) == CellState.EMPTY) {
                    board.makeMove(i, j, CellState.USER);

                    // v ←MIN(v, MAX-VALUE(RESULT(s,a) ,α, β))
                    v = Integer.min(v, maxValue(board, alpha, beta));
                    // if v ≤ α then return v
                    if (v <= alpha) return v;
                    // β←MIN(β, v)
                    beta = Integer.min(beta, v);
                }
            }
        }
        // return v
        return v;
    }
}
