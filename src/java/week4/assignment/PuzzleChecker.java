package week4.assignment; /******************************************************************************
 *  Compilation:  javac-algs4 MyPuzzleChecker.java
 *  Execution:    java-algs4 PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java-algs4 PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PuzzleChecker {

//    public static void main(String[] args) {
//
//        // header
//        StdOut.printf("%-25s %7s %8s\n", "filename", "moves", "time");
//        StdOut.println("------------------------------------------");
//
//        // for each command-line argument
//        for (String filename : args) {
//            // read in the board specified in the filename
//            In in = new In(filename);
//            int n = in.readInt();
//            int[][] blocks = new int[n][n];
//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++)
//                    blocks[i][j] = in.readInt();
//            Board initial = new Board(blocks);
//
//            // check if puzzle is solvable; if so, solve it print out number of moves
//            if (initial.isSolvable()) {
//                Stopwatch timer = new Stopwatch();
//                Solver solver = new Solver(initial);
//                int moves = solver.moves();
//                double time = timer.elapsedTime();
//                StdOut.printf("%-25s %7d %8.2f\n", filename, moves, time);
//            }
//
//            // if not, print that it is unsolvable
//            else {
//                StdOut.printf("%-25s   unsolvable\n", filename);
//            }
//        }
//    }

    public static void main(String[] args) {

        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);

            if (!solver.isSolvable()) {
                StdOut.println("No solution possible");
//                StdOut.println(initial.isSolvable());
            } else {
                StdOut.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    StdOut.println(board);
            }

//            StdOut.println(filename + ": " + solver.moves());
        }
    }
}
