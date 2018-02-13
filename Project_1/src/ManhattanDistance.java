public class ManhattanDistance implements Heurustic{
    @Override
    public int function(Puzzle puzzle) {
        int manhattanDisance = 0;
        for (int i = 0; i < puzzle.getSize(); i++) {
            for (int j = 0; j < puzzle.getSize(); j++) {
                int currentTile = puzzle.getValue(i, j);
                if (currentTile != 0) {
                    int targetX = (currentTile-1) / puzzle.getSize();
                    int targetY = (currentTile) % puzzle.getSize();
                    int dx = i - targetX;
                    int dy = j - targetY;
                    manhattanDisance = Math.abs(dx) + Math.abs(dy);
                }
            }
        }
        return manhattanDisance;    }
}
