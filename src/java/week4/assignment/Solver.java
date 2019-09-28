package week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Stack;

public class Solver {
    private LinkedList<Board> solution;
    private boolean twinSolvable;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        if (initial.isGoal()) {
            solution = new LinkedList<>();
            solution.add(initial);
        } else {
            SearchNode initNode = new SearchNode(initial, null, 0);
            SearchNode twinInitNode = new SearchNode(initial.twin(), null, 0);

            MinPQ<SearchNode> queue = new MinPQ<>();
            MinPQ<SearchNode> twinQueue = new MinPQ<>();

            queue.insert(initNode);
            twinQueue.insert(twinInitNode);

            while (!twinSolvable && solution == null) {

                SearchNode node = queue.delMin();
                for (Board board : node.board.neighbors()) {
                    if (node.previous == null || !node.previous.board.equals(board)) {
                        SearchNode child = new SearchNode(board, node, node.moves + 1);

                        if (child.board.isGoal()) {
                            Stack<Board> stack = new Stack<>();
                            stack.push(child.board);
                            while (child.previous != null) {
                                child = child.previous;
                                stack.push(child.board);
                            }
                            LinkedList<Board> result = new LinkedList<>();
                            while (!stack.isEmpty()) {
                                result.add(stack.pop());
                            }
                            solution = result;
                        }
                        queue.insert(child);
                    }
                }
                SearchNode twinNode = twinQueue.delMin();
                for (Board board : twinNode.board.neighbors()) {
                    if (twinNode.previous == null || !twinNode.previous.board.equals(board)) {
                        SearchNode child = new SearchNode(board, twinNode, node.moves + 1);

                        if (child.board.isGoal()) {
                            twinSolvable = true;
                        }
                        twinQueue.insert(child);
                    }
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
//        twinSolution = aSearch(initial.twin(), new MinPQ<>());
//        return twinSolution == null;
        return solution != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        return solution == null ? -1 : solution.size() - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return solution;
    }

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode previous;
        private final int moves;
        private int priority;

        public SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
        }

        @Override
        public int compareTo(SearchNode n) {
            return Integer.compare(priority, n.priority);
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
