import java.util.*;

public class Search {
    private Heurustic heurustic;
    public Search(Heurustic heurustic) {
        this.heurustic = heurustic;
    }

    public Stack<Puzzle> starTest(Puzzle problem) {
        Stack<Puzzle> solution = new Stack<>();
        Node node = new Node(problem, null, heurustic.function(problem));

        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        frontier.add(node);

        return solution;
    }

    public Stack<Puzzle> starSearch(Puzzle problem) {
        Stack<Puzzle> solution = new Stack<>();
        Node node = new Node(problem, null, heurustic.function(problem));

        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        frontier.add(node);

        HashMap<Integer, Node> explored = new HashMap<>();

        Node bestNode = null;
        while (!frontier.isEmpty()) {
            // Choose lowest cost Node in frontier
            bestNode = frontier.poll();

            // Goal Test
            if (goalTest(bestNode)) {
                System.out.println(bestNode.getState());
                System.out.println("Found!!");
                break;
            }

            // Add node state to explored
            explored.put(bestNode.getHash(), node);

            // For each action in the state
            List<Action> actions = bestNode.getState().getActions();
            for (Action action : actions) {
                Node child = childNode(bestNode, action.getActionType());

                // child state is not in explored set or frontier
                if (!explored.containsKey(child.getHash()) || !frontier.contains(child)) {
                    frontier.add(child);
                } else {
                    // Child is in frontier with higher path cost
                    Iterator<Node> nodes = frontier.iterator();
                    Node check;
                    while (nodes.hasNext()) {
                        check = nodes.next();
                        if (check.equals(child) && check.getPathCost() > child.getPathCost()) {
                            frontier.remove(check);
                            frontier.add(child);
                        }
                    }
                }
            }

        }
        while (bestNode != null) {
            solution.push(bestNode.getState());
            bestNode = bestNode.getParent();
        }
        return solution;
    }

    public Node childNode(Node parent, Action.Type action) {
        for (Action act : parent.getState().getActions()) {
            if (act.getActionType() == action) {
                Puzzle puzzle = new Puzzle(act.move());
                int heuristic = heurustic.function(puzzle);
                Node child = new Node(puzzle, parent, (parent.getPathCost()+1) + heuristic);
                return child;
            }
        }
        return null;
    }

    public boolean goalTest(Node node) {
        int[][] goal = {{0,1,2}, {3,4,5}, {6,7,8}};

        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal.length; j++) {
                if (node.getState().getValue(i, j) != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
