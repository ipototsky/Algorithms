package week4.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Board {
    private final int[][] tiles;
    private final int N;
    private int zeroRow;
    private int zeroColumn;
    private int manhattan = -1;
    private int hamming = -1;
    private int isGoal = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroColumn = j;
                }
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(tiles[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming == -1) {
            hamming = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int current = tiles[i][j];
                    if (current != 0) {
                        if (current != expected(i, j)) {
                            hamming++;
                        }
                    }
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan == -1) {
            manhattan = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int current = tiles[i][j];
                    if (current != 0) {
                        int horizontal = (current - 1) / N;
                        int vertical = (current - 1) % N;
                        manhattan += Math.abs(i - horizontal) + Math.abs(j - vertical);
                    }
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (isGoal == -1) {
            isGoal = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == N-1 && j == N-1 && tiles[i][j] == 0) continue;
                    if (tiles[i][j] != i * N + j + 1) {
                        isGoal = 0;
                        return false;
                    }
                }
            }
        }
        return isGoal == 1;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;

        return y.getClass() == this.getClass() && Arrays.deepEquals(tiles, ((Board) y).tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Collection<Board> queue = new ArrayList<>();
        if (zeroColumn > 0) {
            int[][] copy = copyOf(tiles);
            copy[zeroRow][zeroColumn-1] = tiles[zeroRow][zeroColumn];
            copy[zeroRow][zeroColumn] = tiles[zeroRow][zeroColumn-1];
            Board left = new Board(copy);
            queue.add(left);
        }
        if (zeroRow > 0) {
            int[][] copy = copyOf(tiles);
            copy[zeroRow][zeroColumn] = tiles[zeroRow-1][zeroColumn];
            copy[zeroRow-1][zeroColumn] = tiles[zeroRow][zeroColumn];
            Board up = new Board(copy);
            queue.add(up);
        }
        if (zeroColumn < N - 1) {
            int[][] copy = copyOf(tiles);
            copy[zeroRow][zeroColumn] = tiles[zeroRow][zeroColumn+1];
            copy[zeroRow][zeroColumn+1] = tiles[zeroRow][zeroColumn];
            Board right = new Board(copy);
            queue.add(right);
        }
        if (zeroRow < N - 1) {
            int[][] copy = copyOf(tiles);
            copy[zeroRow][zeroColumn] = tiles[zeroRow+1][zeroColumn];
            copy[zeroRow+1][zeroColumn] = tiles[zeroRow][zeroColumn];
            Board bottom = new Board(copy);
            queue.add(bottom);
        }
        return queue;
    }

//    public boolean isSolvable() {
//        if (N % 2 != 0) {
//            return inversions() % 2 == 0;
//        } else {
//            return (inversions() + zeroRow) % 2 != 0;
//        }
//    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copyOf(tiles);
        if (tiles[0][0] == 0) {
            // swap [1][0] [1][1]
            copy[1][0] = tiles[1][1];
            copy[1][1] = tiles[1][0];
        } else if (tiles[0][1] == 0) {
            //swap [0][0] [1][0]
            copy[0][0] = tiles[1][0];
            copy[1][0] = tiles[0][0];
        } else {
            //swap [0][0] [0][1]
            copy[0][0] = tiles[0][1];
            copy[0][1] = tiles[0][0];
        }
        return new Board(copy);
    }

    private int[][] copyOf(int[][] array) {
        int result[][] = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }

    private int expected(int i, int j) {
        return i * N + j + 1;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    }
}

