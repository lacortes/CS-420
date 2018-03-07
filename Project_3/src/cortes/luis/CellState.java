package cortes.luis;

public enum CellState {
    COMPUTER("X"), USER("O"), EMPTY("-");
    private final String symbol;

    private CellState(String text) {
        this.symbol = text;
    }


    @Override
    public String toString() {
        return this.symbol;
    }
}
