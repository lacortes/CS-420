package cortes.luis;

import java.util.Comparator;

public class StateComparator implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
        if (o1.getHeurustic() < o2.getHeurustic())
            return -1;
        else if (o1.getHeurustic() > o2.getHeurustic())
            return 1;
        else
            return 0;
    }
}
