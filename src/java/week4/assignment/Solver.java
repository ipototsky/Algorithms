package week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Stack;

public class Solver {
    private LinkedList<Board> solution;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        if (initial.isGoal()) {
            solution = new LinkedList<>();
            solution.add(initial);
        } else {
            SearchNode initNode = new SearchNode(initial, null, 0, false);
            SearchNode twinInitNode = new SearchNode(initial.twin(), null, 11, true);

            MinPQ<SearchNode> queue = new MinPQ<>();

            queue.insert(initNode);
            queue.insert(twinInitNode);

            while (!queue.min().board.isGoal()) {

                SearchNode node = queue.delMin();
                    for (Board board : node.board.neighbors()) {
                    if (node.previous == null || !node.previous.board.equals(board)) {
                        SearchNode child = new SearchNode(board, node, node.moves + 1, node.twin);

                        if (child.board.isGoal()) {
                            if (!child.twin) {
                                buildSolution(child);
                            }
                            return;
                        }
                        queue.insert(child);
                    }
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        return isSolvable() ? solution.size() - 1 : -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return solution;
    }

    private void buildSolution(SearchNode goal) {
        Stack<Board> stack = new Stack<>();
        stack.push(goal.board);
        while (goal.previous != null) {
            goal = goal.previous;
            stack.push(goal.board);
        }
        solution = new LinkedList<>();
        while (!stack.isEmpty()) {
            solution.add(stack.pop());
        }
    }

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode previous;
        private final int moves;
        private int manhattanPriority;
        private boolean twin;

        public SearchNode(Board board, SearchNode previous, int moves, boolean twin) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            this.manhattanPriority = board.manhattan() + moves;
            this.twin = twin;
        }

        @Override
        public int compareTo(SearchNode n) {
            return Integer.compare(manhattanPriority, n.manhattanPriority);
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
