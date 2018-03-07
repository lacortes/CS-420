package cortes.luis;

public class MinMax {
    private long startTime;
    private long timeLimit;

    public MinMax(int seconds) {
        this.timeLimit = (seconds * 1000);  // Convert second to milliseconds
    }

    // function ALPHA-BETA-SEARCH(state) returns an action
    public Move alphaBeta(Board board) {
        startTime = System.currentTimeMillis();
        Board testBoard = new Board(board.getCopy());

        // v ←MAX-VALUE(state,−∞,+∞)
        int v = maxValue(testBoard, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Alpha-Beta: "+v);

        // return the action in ACTIONS(state) with value v
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                CellState cell = board.getCell(i, j);

                if (cell == CellState.EMPTY) {
                    // Determine if placing move here will yield same as v
                    int eval = board.evalMove(i, j, CellState.COMPUTER);
                    if (eval >= v) {
                        return new Move(i, j, CellState.COMPUTER);
                    }
                }
            }
        }
        return null;
    }

    // function MAX-VALUE(state,α, β) returns a utility value
    private int maxValue(Board board, int alpha, int beta) {
        // if TERMINAL-TEST(state) then return UTILITY(state)
        long elapsedTime = System.currentTimeMillis() - startTime;
        int evaluation = board.evaluate(CellState.COMPUTER);
        if (elapsedTime >= timeLimit) {
            return evaluation;
        }
        System.out.println("MAX EVALUATION: "+evaluation);
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
