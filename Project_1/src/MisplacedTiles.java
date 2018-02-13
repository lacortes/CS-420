public class MisplacedTiles implements Heurustic{
    @Override
    public int function(Puzzle puzzle) {
        int count = 0;
        for (int i = 0; i < puzzle.getSize(); i++) {
            for (int j = 0; j < puzzle.getSize(); j++) {
                int current = puzzle.getValue(i, j);
                if (count != current) {
                    count++;
                }
            }
        }
        return count;
    }
}
