public class Node {
    private boolean visited;
    private Node parent;
    private Puzzle state;
    private int pathCost;
    private Action.Type actionType;

    public Node(Puzzle state, Node parent, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.pathCost = pathCost;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean flag) {
        this.visited = flag;
    }

    public Node getParent() {
        return this.parent;
    }

    public Puzzle getState() {
        return this.state;
    }

    public int getPathCost() {
        return this.pathCost;
    }

    public int getHash() {
        int hashValue = 0;
        int index = 1;
        for(int i = 0; i < state.getSize(); i++)
        {
            for (int j = 0; j < state.getSize(); j++)
            {
                hashValue += Math.pow(-1.0, index)*Math.pow(((state.getValue(i, j)+ 1)), index);
                index++;
            }
        }
        return hashValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Node)) {
            return false;
        }

        Node nodeCheck = (Node)obj;

        for (int i = 0; i < nodeCheck.getState().getSize(); i++) {
            for (int j = 0; j < nodeCheck.getState().getSize(); j++) {
                if (this.getState().getValue(i, j) != nodeCheck.getState().getValue(i,j)) {
                   return false;
                }
            }
        }
        return true;
    }
}
